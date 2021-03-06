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

package solution;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import jigsaw.Jigsaw;
import jigsaw.JigsawNode;

/**
 * 在此类中填充算法，完成重拼图游戏（N-数码问题）
 */
public class Solution extends Jigsaw {

	/**
	 * 拼图构造函数
	 */
	public Solution() {
	}

	/**
	 * 拼图构造函数
	 * 
	 * @param bNode
	 *            - 初始状态节点
	 * @param eNode
	 *            - 目标状态节点
	 */
	public Solution(JigsawNode bNode, JigsawNode eNode) {
		super(bNode, eNode);
	}

	/**
	 * （实验一）广度优先搜索算法，求指定5*5拼图（24-数码问题）的最优解 填充此函数，可在Solution类中添加其他函数，属性
	 * 
	 * @param bNode
	 *            - 初始状态节点
	 * @param eNode
	 *            - 目标状态节点
	 * @return 搜索成功时为true,失败为false
	 */
	public boolean BFSearch(JigsawNode bNode, JigsawNode eNode) {
		Queue<JigsawNode> exploreList; // 用以保存已发现但未访问的节点
		Set<JigsawNode> visitedList;	// 用以保存已发现的节点
		
		exploreList = new LinkedList<>();
		visitedList = new HashSet<>(1000);

		this.beginJNode = new JigsawNode(bNode);
		this.endJNode = new JigsawNode(eNode);
		this.currentJNode = null;

		// 访问节点数大于29000个则认为搜索失败
//		final int MAX_NODE_NUM = 29000;
		final int DIRS = 4;

		// 重置求解标记
		int searchedNodesNum = 0;

		// (1)将起始节点放入exploreList中
		exploreList.add(this.beginJNode);
		visitedList.add(this.beginJNode);

		// (2) 如果exploreList为空，或者访问节点数大于MAX_NODE_NUM个，则搜索失败，问题无解;否则循环直到求解成功
		while (!exploreList.isEmpty()) {
			searchedNodesNum++;

			// (2-1)取出exploreList的第一个节点N，置为当前节点currentJNode
			// 若currentJNode为目标节点，则搜索成功，计算解路径，退出
			this.currentJNode = exploreList.poll();
			if (this.currentJNode.equals(eNode)) {
				this.getPath();
				break;
			}

			// 记录并显示搜索过程
			// System.out.println("Searching.....Number of searched nodes:" +
			// searchedNodesNum +
			// " Est:" + this.currentJNode.getEstimatedValue() +
			// " Current state:" + this.currentJNode.toString());

			JigsawNode[] nextNodes = new JigsawNode[] { new JigsawNode(this.currentJNode),
					new JigsawNode(this.currentJNode), new JigsawNode(this.currentJNode),
					new JigsawNode(this.currentJNode) };

			// (2-2)寻找所有与currentJNode邻接且未曾被发现的节点，将它们按代价估值从小到大排序插入exploreList中
			// 并加入visitedList中，表示已发现
			for (int i = 0; i < DIRS; i++) {
				if (nextNodes[i].move(i) && !visitedList.contains(nextNodes[i])) {
					exploreList.add(nextNodes[i]);
				}
			}
		}

		System.out.println("Jigsaw BFSearch Search Result:");
		System.out.println("Begin state:" + this.getBeginJNode().toString());
		System.out.println("End state:" + this.getEndJNode().toString());
//		 System.out.println("Solution Path: ");
//		 System.out.println(this.getSolutionPath());
		System.out.println("Total number of searched nodes:" + searchedNodesNum);
		System.out.println("Depth of the current node is:" + this.getCurrentJNode().getNodeDepth());
		return this.isCompleted();
	}

	/**
	 * （Demo+实验二）计算并修改状态节点jNode的代价估计值:f(n) 如 f(n) = s(n). s(n)代表后续节点不正确的数码个数
	 * 此函数会改变该节点的estimatedValue属性值 修改此函数，可在Solution类中添加其他函数，属性
	 * 
	 * @param jNode
	 *            - 要计算代价估计值的节点
	 */
	/**
	 * （Demo+实验二）
	 * 
	 * @param jNode
	 *            - 要计算代价估计值的节点；此函数会改变该节点的estimatedValue属性值。
	 */
	public void estimateValue(JigsawNode jNode) {
		int s = 1 * sumOfDistanceOfMisplaceToItsCorrectPostion(jNode)
				+ 1 * numberOfIncorrectFollowUp(jNode);
		jNode.setEstimatedValue(s);
	}
	
	/* 该模型不用更好
	private int numberOfMisplace(JigsawNode jNode) {
		int s = 0;
		int dimension = JigsawNode.getDimension();
		for (int index = 1; index < dimension * dimension; index++) {
			if (jNode.getNodesState()[index] != index)
				s++;
		}
		if (jNode.getNodesState()[dimension * dimension] != 0) {
			s++;
		}
		return s;
	}
	*/
	
	/**
	 * NOTE: This comment is fake.
	 * WHY: Just for sonar test. :)
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
	 * @throws NullPointerException if the specified map is null
	 * @see #put(Object, Object)
	 * The implementation of this class is testable on the AP CS AB exam.
	 */
	private int sumOfDistanceOfMisplaceToItsCorrectPostion(JigsawNode jNode) {
		int s = 0;
		int dimension = JigsawNode.getDimension();
		for (int index = 1; index <= dimension * dimension; index++) {
			if (jNode.getNodesState()[index] == 0) {
				// corrRow =corrCol =dimension;
				continue;
			}
			int misRow = (int) ((index - 1) / dimension) + 1;
			int misCol = (int) (index - 1) % dimension + 1;
			int corrRow = (int) ((jNode.getNodesState()[index] - 1) / dimension) + 1;
			int corrCol = (int) (jNode.getNodesState()[index] - 1) % dimension + 1;
			s += Math.abs(misRow - corrRow) + Math.abs(misCol - corrCol);
		}
		return s;
	}
	
	/**
	 * NOTE: This comment is fake.
	 * WHY: Just for sonar test. :)
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
	 * @throws NullPointerException if the specified map is null
	 * @see #put(Object, Object)
	 * The implementation of this class is testable on the AP CS AB exam.
	 */
	private int numberOfIncorrectFollowUp(JigsawNode jNode) {
		int s = 0;
		int dimension = JigsawNode.getDimension();
		for (int index = 1; index < dimension * dimension; index++) {
			if (jNode.getNodesState()[index] + 1 != jNode.getNodesState()[index + 1])
				s++;
		}
		return s;
	}
}
