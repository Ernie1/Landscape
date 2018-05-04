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

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.grid.UnboundedGrid;
import info.gridworld.actor.Rock;
import java.awt.Color;

/**
 * This class runs a world that contains maze bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class MazeBugRunner {
	public static void main(String[] args) {
		ActorWorld world = new ActorWorld();
		world.add(new Location(0, 0), new MazeBug());
		world.add(new Location(1, 1), new Rock());
		world.show();
	}
}