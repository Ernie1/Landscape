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

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * This class runs a world that contains BlusterCritterRunner. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class BlusterCritterRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        world.add(new Location(7, 8), new Rock());
        world.add(new Location(3, 3), new Rock());
        Critter a = new Critter();
        a.setColor(Color.WHITE);
        Critter b = new Critter();
        b.setColor(Color.WHITE);
        Critter c = new Critter();
        c.setColor(Color.WHITE);
        Critter d = new Critter();
        d.setColor(Color.WHITE);
        world.add(new Location(2, 8), a);
        world.add(new Location(5, 5), b);
        world.add(new Location(1, 5), c);
        world.add(new Location(7, 2), d);
        BlusterCritter alice = new BlusterCritter(3);
        alice.setColor(new Color(127, 127, 127));
        BlusterCritter bob = new BlusterCritter(2);
        bob.setColor(new Color(127, 127, 127));
        world.add(new Location(4, 4), alice);
        world.add(new Location(5, 8), bob);
        world.show();
    }
}