package info.gridworld.maze;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
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
	public boolean isEnd = false;
	/**
	 * 记录树的节点的栈 ArrayList[0]是访问过的，ArrayList[>0]是未访问的，当没有>0的就要走并pop
	 */
	public Stack<ArrayList<Location>> crossLocation = new Stack<ArrayList<Location>>();

	private final int[] dir4 = { Location.NORTH, Location.EAST, Location.SOUTH, Location.WEST };
	private final int[] dir3 = { Location.LEFT, Location.AHEAD, Location.RIGHT };
	private int[] countDir3 = { 0, 0, 0 };

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
		if (gr == null)
			return null;
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
		if (gr == null)
			return false;
		if (stepCount == 0) {
			last = next = getLocation();
			setNext();
		}

		// for (int i = 0; i < 4; ++i) {
		// if (crossLocation.peek().get(i) == null)
		// System.out.println("null");
		// else {
		// System.out.print(crossLocation.peek().get(i).getCol());
		// System.out.print(",");
		// System.out.println(crossLocation.peek().get(i).getRow());
		// }
		// }
		// System.out.println();

		// 暂先按顺序
		for (int i = 1; i < 4; ++i) {
			if (crossLocation.peek().get(i) != null) {
				last = crossLocation.peek().get(0); // == getLocation()
				next = crossLocation.peek().get(i);
				crossLocation.peek().set(i, null);
				setNext();
				return true;
			}
		}

		crossLocation.pop();
		if (crossLocation.empty()) {
			return false;
		}

		last = getLocation();
		next = crossLocation.peek().get(0);
		return true;
	}

	private void setNext() {
		Grid<Actor> gr = getGrid();
		if(gr.get(next) instanceof Rock && gr.get(next).getColor().equals(Color.RED)) {
			isEnd = true;
			return;
		}
		ArrayList<Location> arrayList = new ArrayList<>();
		arrayList.add(next);
		crossLocation.push(arrayList);
		for (int j = 1; j < 4; ++j) {
			Location choice = next.getAdjacentLocation(getLocation().getDirectionToward(next) + dir3[j - 1]);
			if (gr.isValid(choice)) {
				if (!(gr.get(choice) instanceof Rock)) {
					crossLocation.peek().add(choice);
				} else if (gr.get(choice).getColor().equals(Color.RED)) {
					crossLocation.peek().add(choice);
					// 去掉其他方向
					for (int i = 1; i < j; ++i) {
						crossLocation.peek().set(i, null);
					}
					for (int i = j + 1; i < 4; ++i) {
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
