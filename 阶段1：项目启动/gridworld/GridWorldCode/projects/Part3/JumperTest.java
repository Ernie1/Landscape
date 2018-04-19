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
 * @author Cay Horstmann
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 */

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import info.gridworld.actor.*;
import info.gridworld.grid.Location;

public class JumperTest {

	@Before
	public void setUp() throws Exception
	{
		
	}
	
	/**
	 * a. What will a jumper do if the location in front of it is empty, but the location two cells in front contains a flower or a rock? <br />
	 * if rock, turn, if flower, move Jumper and remove the flower
	 */
	@Test
	public void testA() {
		ActorWorld world = new ActorWorld();
        Jumper alice = new Jumper();
        Flower flower = new Flower();
        Rock rock = new Rock();
        world.add(new Location(6, 6), alice);
        world.add(new Location(4, 6), flower);
        world.add(new Location(2, 6), rock);
        alice.act();
        assertNull(flower.getGrid());
        alice.act();
        assertEquals(alice.getLocation(), new Location(4, 6));
        assertEquals(alice.getDirection(), Location.NORTHEAST);
	}
	
	/**
	 * b. What will a jumper do if the location two cells in front of the jumper is out of the grid? <br />
	 * turn
	 */
	@Test
	public void testB() {
		ActorWorld world = new ActorWorld();
        Jumper alice = new Jumper();
        world.add(new Location(1, 6), alice);
        alice.act();
        assertEquals(alice.getLocation(), new Location(1, 6));
        assertEquals(alice.getDirection(), Location.NORTHEAST);
	}
	
	/**
	 * c. What will a jumper do if it is facing an edge of the grid? <br />
	 * turn
	 */
	@Test
	public void testC() {
		ActorWorld world = new ActorWorld();
        Jumper alice = new Jumper();
        world.add(new Location(0, 6), alice);
        alice.act();
        assertEquals(alice.getLocation(), new Location(0, 6));
        assertEquals(alice.getDirection(), Location.NORTHEAST);
	}
	
	/**
	 * d. What will a jumper do if another actor (not a flower or a rock) is in the cell that is two cells in front of the jumper? <br />
	 * turn
	 */
	@Test
	public void testD() {
		ActorWorld world = new ActorWorld();
        Jumper alice = new Jumper();
        world.add(new Location(6, 6), alice);
        world.add(new Location(4, 6), new Actor());
        alice.act();
        assertEquals(alice.getLocation(), new Location(6, 6));
        assertEquals(alice.getDirection(), Location.NORTHEAST);
	}
	
	/**
	 * e. What will a jumper do if it encounters another jumper in its path? <br />
	 * turn
	 */
	@Test
	public void testE() {
		ActorWorld world = new ActorWorld();
        Jumper alice = new Jumper();
        Jumper bob = new Jumper();
        bob.setDirection(Location.SOUTH);
        world.add(new Location(6, 6), alice);
        world.add(new Location(4, 6), bob);
        alice.act();
        bob.act();
        assertEquals(alice.getLocation(), new Location(6, 6));
        assertEquals(bob.getLocation(), new Location(4, 6));
        assertEquals(alice.getDirection(), Location.NORTHEAST);
        assertEquals(bob.getDirection(), Location.SOUTHWEST);
	}
	
	/**
	 * f. EXTRA TEST: Can jumper “jumps” over other instance of Actor besides rocks and flowers? <br />
	 * yes
	 */
	@Test
	public void testF() {
		ActorWorld world = new ActorWorld();
        Jumper alice = new Jumper();
        Jumper bob = new Jumper();
        bob.setDirection(Location.SOUTH);
        world.add(new Location(6, 6), alice);
        world.add(new Location(5, 6), bob);
        world.add(new Location(3, 6), new Actor());
        alice.act();
        bob.act();
        assertEquals(alice.getLocation(), new Location(4, 6));
        alice.act();
        assertEquals(alice.getLocation(), new Location(2, 6));
	}

}