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
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 * @author Cay Horstmann
 */

import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.ArrayList;

/**
 * A <code>KingCrab</code> A KingCrab moves to one of the two locations, 
 * randomly selected, that are two spaces to its right or left, 
 * if that location and the intervening location are both empty. 
 * Otherwise, a KingCrab moves like a CrabCritter.
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class KingCrab extends CrabCritter
{
	/**
     * Processes the elements of <code>actors</code>. New actors may be added
     * to empty locations. Implemented to "eat" (i.e. remove) selected actors
     * that are not rocks or critters. Override this method in subclasses to
     * process actors in a different way. <br />
     * Postcondition: (1) The state of all actors in the grid other than this
     * critter and the elements of <code>actors</code> is unchanged. (2) The
     * location of this critter is unchanged.
     * @param actors the actors to be processed
     */
    public void processActors(ArrayList<Actor> actors)
    {
        Location loc = getLocation();
        Grid<Actor> gr = getGrid();
    	for (Actor a : actors)
        {
    		ArrayList<Location> locations = gr.getEmptyAdjacentLocations(a.getLocation());
    		Location des = null;
    		for(Location l : locations)
    		{
    			if(gr.getOccupiedAdjacentLocations(l).indexOf(loc) == -1){
    				des = l;
    				break;
    			}
    		}
    		if(des == null)
    		{
    			a.removeSelfFromGrid();
    		}
    		else
    		{
    			a.moveTo(des);
    		}
        }
    }
}
