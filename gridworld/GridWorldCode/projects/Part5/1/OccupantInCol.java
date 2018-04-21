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

/**
 * A <code>OccupantInCol</code> is use as raw list node. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class OccupantInCol
{
    private Object occupant;
    private int col;
    
    /**
     * Constructs an empty OccupantInCol
     * @param occupant object
     * @param cols number of occupant
     */
    public OccupantInCol(Object o, int c)
    {
    	occupant = o;
    	col = c;
    }
    
    public Object getOccupant()
    {
      return occupant;
    }
    
    public void setOccupant(Object o)
    {
      occupant = o;
    }
    
    public int getCol()
    {
    	return col;
    }
}