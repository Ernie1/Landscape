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
 * A <code>QuickCrab</code> A QuickCrab moves to one of the two locations, 
 * randomly selected, that are two spaces to its right or left, 
 * if that location and the intervening location are both empty. 
 * Otherwise, a QuickCrab moves like a CrabCritter.
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class QuickCrab extends CrabCritter
{
    /**
     * @return list of empty locations immediately to the right and to the left
     */
    public ArrayList<Location> getMoveLocations()
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        Grid<Actor> gr = getGrid();
        Location loc = getLocation();
        int[] dirs =
            { Location.LEFT, Location.RIGHT };
        for (int dir : dirs)
        {
        	Location intervention = loc.getAdjacentLocation(getDirection() + dir);
        	if(gr.isValid(intervention) && gr.get(intervention) == null)
        	{
        		Location des = intervention.getAdjacentLocation(getDirection() + dir);
        		if(gr.isValid(des) && gr.get(des) == null)
        		{
        			locs.add(des);
        		}
        	}
        }
        if(locs.size() == 0)
        {
        	locs = super.getMoveLocations();
        }

        return locs;
    }
}
