
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
 * @author http://www.360doc.com/content/18/0429/08/55038824_749607448.shtml
 * 将BufferedImage保存为BMP格式的文件,可以下载SUN公司的JAI,调用里面的API;或者自己编写类,按照BMP的格式逐个写入.
 * 下面的类实现了该功能,主类为BMPSaver.
 */

import java.awt.image.*;
import java.io.*;
import java.io.IOException;
import java.io.OutputStream;

public class BMPSaver {
	
	private int width;
	private int height;
	private int size;
	private FileOutputStream out;
	private BufferedImage bi;
	
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
	public BMPSaver(FileOutputStream outputStream, BufferedImage bufferedImage) throws Exception {
		out = outputStream;
		bi = bufferedImage;
		width = bi.getWidth();
		height = bi.getHeight();
		encode();
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
	private byte[] BMPFileHeader() {
		byte[] data = new byte[14];

		data[0] = 'B';
		data[1] = 'M';

		intToByte(size, data, 2);
		int offset = 54;
		intToByte(offset, data, 10);

		return data;
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
	private byte[] BMPInfoHeader() {
		byte[] data = new byte[40];
		data[0] = 40;

		intToByte(width, data, 4);
		intToByte(height, data, 8);

		data[12] = 1;

		int bitCount = 24;
		data[14] = (byte) bitCount;

		intToByte(size, data, 20);
		return data;
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
	private void encode() throws IOException {

		size = width * height * 3;
		boolean needBlank = (width % 4 != 0);
		if (needBlank) {
			size += (width % 4) * height;
		}

		out.write(BMPFileHeader());
		out.write(BMPInfoHeader());

		byte[] rgbs = new byte[3];
		byte[] blank = new byte[width % 4];

		int index = 0;
		for (int y = height - 1; y >= 0; y--) {
			for (int x = 0; x < width; x++) {
				index += 3;

				int rgb = bi.getRGB(x, y);
				rgbs[0] = (byte) rgb;
				rgb = rgb >>> 8;
				rgbs[1] = (byte) rgb;
				rgb = rgb >>> 8;
				rgbs[2] = (byte) rgb;

				out.write(rgbs);

				if (needBlank && (index % (width * 3) == 0)) {
					out.write(blank);
				}
			}
		}
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
	private void intToByte(int src, byte[] des, int offset) {
		for (int i = 0; i < 4; ++i) {
			des[offset + i] = (byte) src;
			src >>>= 8;
		}
	}
}