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
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;

/**
 * A <code>BlusterCritter</code> removes any rocks in that list from the grid. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class BlusterCritter extends Critter
{
	 private int courage;
	 
	/**
     * Constructs a BlusterCritter with courage.
     */
    public BlusterCritter(int c)
    {
        super();
        courage = c;
    }
    
    /**
     * A BlusterCritter gets the actors
     * two steps of its current location.
     * @return a list of actors occupying these locations
     */
    public ArrayList<Actor> getActors()
    {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        Grid<Actor> gr = getGrid();
        Location loc = getLocation();
        for(int i = -2; i <= 2; ++i)
        {
        	for(int j = -2; j <= 2; ++j)
        	{
        		if(i == 0 && j == 0)
        		{
        			continue;
        		}
        		Location tem = new Location(loc.getRow() + i, loc.getCol() + j);
        		if(gr.isValid(tem))
        		{
        			Actor actor = gr.get(tem);
        			if(actor instanceof Critter)
        			{
        				actors.add(actor);
        			}
        		}
        	}
        }

        return actors;
    }
    
	/**
     * Randomly selects a neighbor and changes this critter's color to be the
     * same as that neighbor's. If there are no neighbors, no action is taken.
     */
    public void processActors(ArrayList<Actor> actors)
    {
        int n = actors.size();
        Color c = getColor();
        if (n >= courage)
        {
        	final double DARKENING_FACTOR = 0.05;
            int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
            int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
            int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));
            setColor(new Color(red, green, blue));
        }
        else
        {
        	int red = c.getRed();
            int green = c.getGreen();
            int blue = c.getBlue();
            if(red < 255) red++;
            if(green < 255) green++;
            if(blue < 255) blue++;
            setColor(new Color(red, green, blue));
        }
    }
}
