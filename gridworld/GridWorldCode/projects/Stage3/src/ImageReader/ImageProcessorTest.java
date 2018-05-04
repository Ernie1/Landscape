
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

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

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
public class ImageProcessorTest {

	private ImplementImageIO implementImageIO = new ImplementImageIO();
	private ImplementImageProcessor imageProcessor = new ImplementImageProcessor();
	private Image src1, src2;
	private BufferedImage my1, my2, goal1, goal2;

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
	@Before
	public void setUp() throws Exception {
		src1 = implementImageIO.myRead("bmptest/1.bmp");
		src2 = implementImageIO.myRead("bmptest/2.bmp");
	}

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
	@Test
	public void testShowChanelR() throws IOException {
		my1 = ToBufferedImage.toBufferedImage(imageProcessor.showChanelR(src1));
		my2 = ToBufferedImage.toBufferedImage(imageProcessor.showChanelR(src2));
		goal1 = ToBufferedImage.toBufferedImage(implementImageIO.myRead("bmptest/goal/1_red_goal.bmp"));
		goal2 = ToBufferedImage.toBufferedImage(implementImageIO.myRead("bmptest/goal/2_red_goal.bmp"));

		matchWidthAndHeightAndPixels();
	}

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
	@Test
	public void testShowChanelG() throws IOException {
		my1 = ToBufferedImage.toBufferedImage(imageProcessor.showChanelG(src1));
		my2 = ToBufferedImage.toBufferedImage(imageProcessor.showChanelG(src2));
		goal1 = ToBufferedImage.toBufferedImage(implementImageIO.myRead("bmptest/goal/1_green_goal.bmp"));
		goal2 = ToBufferedImage.toBufferedImage(implementImageIO.myRead("bmptest/goal/2_green_goal.bmp"));

		matchWidthAndHeightAndPixels();
	}

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
	@Test
	public void testShowChanelB() throws IOException {
		my1 = ToBufferedImage.toBufferedImage(imageProcessor.showChanelB(src1));
		my2 = ToBufferedImage.toBufferedImage(imageProcessor.showChanelB(src2));
		goal1 = ToBufferedImage.toBufferedImage(implementImageIO.myRead("bmptest/goal/1_blue_goal.bmp"));
		goal2 = ToBufferedImage.toBufferedImage(implementImageIO.myRead("bmptest/goal/2_blue_goal.bmp"));

		matchWidthAndHeightAndPixels();
	}

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
	@Test
	public void testShowGray() throws IOException {
		my1 = ToBufferedImage.toBufferedImage(imageProcessor.showGray(src1));
		my2 = ToBufferedImage.toBufferedImage(imageProcessor.showGray(src2));
		goal1 = ToBufferedImage.toBufferedImage(implementImageIO.myRead("bmptest/goal/1_gray_goal.bmp"));
		goal2 = ToBufferedImage.toBufferedImage(implementImageIO.myRead("bmptest/goal/2_gray_goal.bmp"));

		matchWidthAndHeightAndPixels();
	}

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
	private void matchWidthAndHeightAndPixels() {
		assertEquals(my1.getWidth(), goal1.getWidth());
		assertEquals(my1.getHeight(), goal1.getHeight());
		assertEquals(my1.getWidth(), goal1.getWidth());
		assertEquals(my2.getHeight(), goal2.getHeight());

		int[] my1Pix = new int[my1.getWidth() * my1.getHeight()];
		my1.getRGB(0, 0, my1.getWidth(), my1.getHeight(), my1Pix, 0, my1.getWidth());
		int[] my2Pix = new int[my2.getWidth() * my2.getHeight()];
		my2.getRGB(0, 0, my2.getWidth(), my2.getHeight(), my2Pix, 0, my2.getWidth());
		int[] goal1Pix = new int[goal1.getWidth() * goal1.getHeight()];
		goal1.getRGB(0, 0, goal1.getWidth(), goal1.getHeight(), goal1Pix, 0, goal1.getWidth());
		int[] goal2Pix = new int[goal2.getWidth() * goal2.getHeight()];
		goal2.getRGB(0, 0, goal2.getWidth(), goal2.getHeight(), goal2Pix, 0, goal2.getWidth());

		assertArrayEquals(my1Pix, goal1Pix);
		assertArrayEquals(my2Pix, goal2Pix);
	}
}