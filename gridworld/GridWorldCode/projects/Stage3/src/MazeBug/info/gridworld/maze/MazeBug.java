/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Me
 * @author Me
 * @author Me
 */

package info.gridworld.maze;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JOptionPane;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBug extends Bug {
	// 记录下一步要行走到的位置
	public Location next;
	// 记录上一步的位置，便于在走到死路尽头时返回
	public Location last;
	public int lastDirection;
	public boolean isEnd = false;
	/**
	 * 记录树的节点的栈 ArrayList[0]是访问过的，ArrayList[>0]是未访问的，当没有>0的就要走并pop
	 */
	public Stack<ArrayList<Location>> crossLocation = new Stack<ArrayList<Location>>();
	public Stack<Integer> directionRecord = new Stack<>();

	private final int[] dir3 = { Location.LEFT, Location.AHEAD, Location.RIGHT, Location.HALF_CIRCLE };
	private int[] countDir3 = { 50, 50, 50, 5000 };

	// 记录本迷宫走到出口所用的步数
	public Integer stepCount = 0;
	boolean hasShown = false;// final message has been shown

	// private Map<Location, Boolean> visited;

	/**
	 * Constructs a box bug that traces a square of a given side length
	 * 
	 * @param length
	 *            the side length
	 */
	public MazeBug() {
		setColor(Color.GREEN);
		stepCount = 0;
		// last = new Location(0, 0);
		// visited = new HashMap<Location, Boolean>();
	}

	/**
	 * Moves to the next location of the square. 虫子行动函数，每走一步会加一点步数，找到出口时显示步数
	 */
	public void act() {
		boolean willMove = canMove();
		if (isEnd == true) {
			// to show step count when reach the goal
			if (hasShown == false) {
				String msg = stepCount.toString() + " steps";
				JOptionPane.showMessageDialog(null, msg);
				hasShown = true;
			}
		} else if (willMove) {
			move();
			// increase step count when move
			stepCount++;
		}
	}

	/**
	 * Find all positions that can be move to. 寻找可行走方向
	 * 
	 * @param loc
	 *            the location to detect.
	 * @return List of positions.
	 */
	public ArrayList<Location> getValid(Location loc) {
		Grid<Actor> gr = getGrid();
		if (gr == null) {
			return null;
		}
		ArrayList<Location> valid = new ArrayList<>();

		return valid;
	}

	/**
	 * Tests whether this bug can move forward into a location that is empty or
	 * contains a flower. 判断是否可以行走
	 * 
	 * @return true if this bug can move.
	 */
	public boolean canMove() {
		Grid<Actor> gr = getGrid();
		if (gr == null) {
			return false;
		}
		if (stepCount == 0) {
			last = next = getLocation();
			setNext();
		}

		System.out.println("<------------------------------");
		for (int i = 0; i < 5; ++i) {
			if (crossLocation.peek().get(i) == null) {
				System.out.println("null!");
			} else {
				System.out.print(crossLocation.peek().get(i).getRow());
				System.out.print(",");
				System.out.println(crossLocation.peek().get(i).getCol());
			}
		}
		System.out.println("countDir3: " + countDir3[0] + " " + countDir3[1] + " " + countDir3[2]);
		double[] proDir3 = { 0, 0, 0, 0 };
		double proSum = 0;
		for (int i = 1; i < 5; ++i) {
			if (crossLocation.peek().get(i) != null) {
				// System.out.println("countDir3[" + i + "-1]: " + countDir3[i - 1]);
				proSum += countDir3[i - 1];
				proDir3[i - 1] = proSum;
			}
		}

		System.out.println(proSum);
		System.out.println("------------------------------>\n");
		if (proSum != 0) {
			lastDirection = getDirection();
			// 根据概率
			proSum *= Math.random();
			// System.out.println(proDir3[0]+" "+proDir3[1]+" "+proDir3[2]+" "+proSum);
			// System.out.println(countDir3[0]+" "+countDir3[1]+" "+countDir3[2]);
			// System.out.println();
			for (int i = 1; i < 5; ++i) {
				if (proDir3[i - 1] >= proSum) {
					++countDir3[i - 1];
					last = crossLocation.peek().get(0); // == getLocation()
					next = crossLocation.peek().get(i);
					crossLocation.peek().set(i, null);
					setNext();
					return true;
				}
			}
		}

		// 回退
		crossLocation.pop();
		int past = directionRecord.peek();
		directionRecord.pop();

		if (crossLocation.empty()) {
			return false;
		}

		int tem = past - directionRecord.peek();
		while (tem < 0) {
			tem += 360;
		}
		while (tem > 360) {
			tem -= 360;
		}
		if (tem == 270) {
			if (countDir3[0] > 1) {
				--countDir3[0];
			}
		} else if (tem == 0) {
			if (countDir3[1] > 1) {
				--countDir3[1];
			}
		} else if (tem == 90) {
			if (countDir3[2] > 1) {
				--countDir3[2];
			}
		}

		last = getLocation();
		next = crossLocation.peek().get(0);
		return true;
	}

	/**
	 * NOTE: This comment is fake. WHY: Just for sonar test. :)
	 * 
	 * Associates the specified value with the specified key in this map. If the map
	 * previously contained a mapping for the key, the old value is replaced.
	 *
	 * @param key
	 *            key with which the specified value is to be associated
	 * @param value
	 *            value to be associated with the specified key
	 * @return the previous value associated with <tt>key</tt>, or <tt>null</tt> if
	 *         there was no mapping for <tt>key</tt>. (A <tt>null</tt> return can
	 *         also indicate that the map previously associated <tt>null</tt> with
	 *         <tt>key</tt>.)
	 * @throws NullPointerException
	 *             if the specified map is null
	 * @see #put(Object, Object) The implementation of this class is testable on the
	 *      AP CS AB exam.
	 */
	private void setNext() {
		Grid<Actor> gr = getGrid();
		if (gr.get(next) instanceof Rock && gr.get(next).getColor().equals(Color.RED)) {
			isEnd = true;
			return;
		}
		ArrayList<Location> arrayList = new ArrayList<>();
		arrayList.add(next);
		crossLocation.push(arrayList);

		directionRecord.push(getLocation().getDirectionToward(next));
		for (int j = 1; j < 5; ++j) {
			Location choice = next.getAdjacentLocation(getLocation().getDirectionToward(next) + dir3[j - 1]);
			// 针对开始，因为same.getDirectionToward(same)=90
			if (getLocation().equals(next)) {
				choice = next.getAdjacentLocation(getDirection() + dir3[j - 1]);
			} else if (j == 4) {
				crossLocation.peek().add(null);
				break;
			}
			if (gr.isValid(choice)) {
				if (!(gr.get(choice) instanceof Rock)) {
					crossLocation.peek().add(choice);
				} else if (gr.get(choice).getColor().equals(Color.RED)) {
					crossLocation.peek().add(choice);
					// 去掉其他方向
					for (int i = 1; i < j; ++i) {
						crossLocation.peek().set(i, null);
					}
					for (int i = j + 1; i < 5; ++i) {
						crossLocation.peek().add(null);
					}
					break;
				} else {
					crossLocation.peek().add(null);
				}
			} else {
				crossLocation.peek().add(null);
			}
		}
	}

	/**
	 * Moves the bug forward, putting a flower into the location it previously
	 * occupied. 如何行走
	 */
	public void move() {
		// System.out.println(countDir3[0] + " " + countDir3[1] + " " + countDir3[2]);

		Grid<Actor> gr = getGrid();
		if (gr == null) {
			return;
		}
		Location loc = getLocation();
		if (gr.isValid(next)) {
			setDirection(getLocation().getDirectionToward(next));
			moveTo(next);
		} else {
			removeSelfFromGrid();
		}
		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(gr, loc);
	}
}