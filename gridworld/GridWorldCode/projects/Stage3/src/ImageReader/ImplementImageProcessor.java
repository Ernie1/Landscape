
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

import java.awt.Image;
import java.awt.image.BufferedImage;

import imagereader.IImageProcessor;

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
public class ImplementImageProcessor implements IImageProcessor {

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
	@Override
	public Image showChanelR(Image sourceImage) {
		BufferedImage bufferedImage = ToBufferedImage.toBufferedImage(sourceImage);
		final int width = bufferedImage.getWidth();
		final int height = bufferedImage.getHeight();
		BufferedImage ChanelR = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		for (int w = 0; w < width; ++w) {
			for (int h = 0; h < height; ++h) {
				final int color = bufferedImage.getRGB(w, h);
				final int alpha = 0xff;
				final int r = (color >> 16) & 0xff;
				final int g = 0;
				final int b = 0;
				ChanelR.setRGB(w, h, rgb(alpha, r, g, b));
			}
		}
		return ChanelR;
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
	@Override
	public Image showChanelG(Image sourceImage) {
		BufferedImage bufferedImage = ToBufferedImage.toBufferedImage(sourceImage);
		final int width = bufferedImage.getWidth();
		final int height = bufferedImage.getHeight();
		BufferedImage ChanelG = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		for (int w = 0; w < width; ++w) {
			for (int h = 0; h < height; ++h) {
				final int color = bufferedImage.getRGB(w, h);
				final int alpha = 0xff;
				final int r = 0;
				final int g = (color >> 8) & 0xff;
				final int b = 0;
				ChanelG.setRGB(w, h, rgb(alpha, r, g, b));
			}
		}
		return ChanelG;
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
	@Override
	public Image showChanelB(Image sourceImage) {
		BufferedImage bufferedImage = ToBufferedImage.toBufferedImage(sourceImage);
		final int width = bufferedImage.getWidth();
		final int height = bufferedImage.getHeight();
		BufferedImage ChanelB = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		for (int w = 0; w < width; ++w) {
			for (int h = 0; h < height; ++h) {
				final int color = bufferedImage.getRGB(w, h);
				final int alpha = 0xff;
				final int r = 0;
				final int g = 0;
				final int b = color & 0xff;
				ChanelB.setRGB(w, h, rgb(alpha, r, g, b));
			}
		}
		return ChanelB;
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
	@Override
	public Image showGray(Image sourceImage) {
		BufferedImage bufferedImage = ToBufferedImage.toBufferedImage(sourceImage);
		final int width = bufferedImage.getWidth();
		final int height = bufferedImage.getHeight();
		BufferedImage Gray = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		for (int w = 0; w < width; ++w) {
			for (int h = 0; h < height; ++h) {
				final int color = bufferedImage.getRGB(w, h);
				final int alpha = 0xff;
				final int r = (color >> 16) & 0xff;
				final int g = (color >> 8) & 0xff;
				final int b = color & 0xff;
				int gray = (int) (0.299 * r + 0.587 * g + 0.114 * b);
				Gray.setRGB(w, h, rgb(alpha, gray, gray, gray));
			}
		}
		return Gray;
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
	int rgb(int a, int r, int g, int b) {
		return (a << 24) | (r << 16) | (g << 8) | b;
	}
}