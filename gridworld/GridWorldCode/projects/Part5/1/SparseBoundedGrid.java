/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2002-2006 College Entrance Examination Board 
 * (http://www.collegeboard.com).
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
 * @author Alyce Brady
 * @author APCS Development Committee
 * @author Cay Horstmann
 */

//import info.gridworld.grid.Grid;
import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;
import java.util.*;

/**
 * A <code>SparseBoundedGrid</code> is a rectangular grid with a finite number of
 * rows and columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class SparseBoundedGrid<E> extends AbstractGrid<E>
{
    private ArrayList<LinkedList<OccupantInCol>> occupantArray; // the array storing the grid elements
    private int rowNum;
    private int colNum;
    
    /**
     * Constructs an empty bounded grid with the given dimensions.
     * (Precondition: <code>rows > 0</code> and <code>cols > 0</code>.)
     * @param rows number of rows in SparseBoundedGrid
     * @param cols number of columns in SparseBoundedGrid
     */
    public SparseBoundedGrid(int rows, int cols)
    {
        if (rows <= 0)
            throw new IllegalArgumentException("rows <= 0");
        if (cols <= 0)
            throw new IllegalArgumentException("cols <= 0");
        rowNum = rows;
        colNum = cols;
        occupantArray = new ArrayList<LinkedList<OccupantInCol>>(rowNum);
        for(int i = 0; i < rowNum; ++i)
        {
        	occupantArray.add(new LinkedList<OccupantInCol>());
        }
    }

    public int getNumRows()
    {
        return rowNum;
    }

    public int getNumCols()
    {
        return colNum;
    }

    public boolean isValid(Location loc)
    {
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }

    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> theLocations = new ArrayList<Location>();
        // Look at all grid locations.
        for(int i = 0; i < occupantArray.size(); ++i)
        {
        	for(OccupantInCol o : occupantArray.get(i))
        	{
        		theLocations.add(new Location(i, o.getCol()));
        	}
        }
        return theLocations;
    }

    public E get(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        Object ans = null;
        for(OccupantInCol o : occupantArray.get(loc.getRow()))
		{
        	if(o.getCol() == loc.getCol())
        	{
        		ans = o.getOccupant();
        	}
		}
        return (E) ans; // unavoidable warning
    }

    public E put(Location loc, E obj)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (obj == null)
            throw new NullPointerException("obj == null");

        // Add the object to the grid.
        E oldOccupant = remove(loc);
        occupantArray.get(loc.getRow()).add(new OccupantInCol(obj, loc.getCol()));
        return oldOccupant;
    }

    public E remove(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        
        // Remove the object from the grid.
        E r = get(loc);
        Iterator<OccupantInCol> iter = occupantArray.get(loc.getRow()).iterator();
        while(iter.hasNext())
        {
        	if(iter.next().getCol() == loc.getCol())
        	{
        		iter.remove();
        		break;
        	}
        }
        return r;
    }
}