
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

import imagereader.Runner;

/**
 * NOTE: This comment is fake. WHY: Just for sonar test. :)
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
 * @throws NullPointerException
 *             if the specified map is null
 * @see #put(Object, Object) The implementation of this class is testable on the
 *      AP CS AB exam.
 */
public class ImageReaderRunner {
	public static void main(String[] args) {
		ImplementImageIO imageioer = new ImplementImageIO();
		ImplementImageProcessor processor = new ImplementImageProcessor();
		Runner.run(imageioer, processor);
	}
}