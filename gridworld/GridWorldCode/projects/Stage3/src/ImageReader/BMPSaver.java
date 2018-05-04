//http://www.360doc.com/content/18/0429/08/55038824_749607448.shtml
//将BufferedImage保存为BMP格式的文件,可以下载SUN公司的JAI,调用里面的API;或者自己编写类,按照BMP的格式逐个写入.
//
//下面的类实现了该功能,主类为BMPSaver.

import java.awt.image.*;
import java.io.*;
import java.io.IOException;
import java.io.OutputStream;

/**//**
	 * Title: BMP文件的头结构
	 *
	 * Description: BMP文件的头结构固定是14个字节，其定义如下：
	 * 
	 * byte[2] bfType; 指定文件类型，必须是0x424D，即字符串“BM”，也就是说所有.bmp文件的头两个字节都是“BM“ byte[4]
	 * bfSize; 指定文件大小，包括这14个字节 byte[2] bfReserved1; 保留字 byte[2] bfReserved2; 保留字
	 * byte[4] bfOffBits; 为从文件头到实际的位图数据的偏移字节数
	 */

class BMPFileHeader {

	// Header data
	private byte[] data = new byte[14];

	public byte[] getData() {
		return this.data;
	}

	// BMP file size
	private int size;

	public int getSize() {
		return this.size;
	}

	private int offset;

	public int getOffset() {
		return this.offset;
	}

	BMPFileHeader(int size, int offset) {
		this.size = size;
		this.offset = offset;

		data[0] = 'B';
		data[1] = 'M';

		int value = size;
		data[2] = (byte) value;
		value = value >>> 8;
		data[3] = (byte) value;
		value = value >>> 8;
		data[4] = (byte) value;
		value = value >>> 8;
		data[5] = (byte) value;

		value = offset;
		data[10] = (byte) value;
		value = value >>> 8;
		data[11] = (byte) value;
		value = value >>> 8;
		data[12] = (byte) value;
		value = value >>> 8;
		data[13] = (byte) value;
	}

}

/**//**
	 * Title: BMP文件内容的头结构
	 *
	 * Description: BMP文件内容的头结构固定是40个字节，其定义如下：
	 * 
	 * byte[4] biSize; 指定这个结构的长度，为40 byte[4] biWidth; 指定图象的宽度，单位是象素 byte[4]
	 * biHeight; 指定图象的高度，单位是象素 byte[2] biPlanes; 必须是1，不用考虑 byte[2] biBitCount;
	 * 指定表示颜色时要用到的位数，常用的值为1(黑白二色图), 4(16色图), 8(256色), 24(真彩色图) byte[4]
	 * biCompression; 指定位图是否压缩 byte[4] biSizeImage; 指定实际的位图数据占用的字节数 byte[4]
	 * biXPelsPerMeter; 指定目标设备的水平分辨率，单位是每米的象素个数 byte[4] biYPelsPerMeter;
	 * 指定目标设备的垂直分辨率，单位是每米的象素个数 byte[4] biClrUsed;
	 * 指定本图象实际用到的颜色数，如果该值为零，则用到的颜色数为2biBitCount byte[4] biClrImportant;
	 * 指定本图象中重要的颜色数，如果该值为零，则认为所有的颜色都是重要的
	 * 
	 */

class BMPInfoHeader {

	private byte[] data = new byte[40];

	public byte[] getData() {
		return this.data;
	}

	private int width;

	public int getWidth() {
		return this.width;
	}

	private int height;

	public int getHeight() {
		return this.height;
	}

	public int bitCount;

	public int getBitCount() {
		return this.bitCount;
	}

	public BMPInfoHeader(int width, int height, int bitCount) {
		this.width = width;
		this.height = height;
		this.bitCount = bitCount;

		data[0] = 40;

		int value = width;
		data[4] = (byte) value;
		value = value >>> 8;
		data[5] = (byte) value;
		value = value >>> 8;
		data[6] = (byte) value;
		value = value >>> 8;
		data[7] = (byte) value;

		value = height;
		data[8] = (byte) value;
		value = value >>> 8;
		data[9] = (byte) value;
		value = value >>> 8;
		data[10] = (byte) value;
		value = value >>> 8;
		data[11] = (byte) value;

		data[12] = 1;

		data[14] = (byte) bitCount;

		value = width * height * 3;
		if (width % 4 != 0)
			value += (width % 4) * height;
		data[20] = (byte) value;
		value = value >>> 8;
		data[21] = (byte) value;
		value = value >>> 8;
		data[22] = (byte) value;
		value = value >>> 8;
		data[23] = (byte) value;
	}

}

// 仿照com.sun.image.codec.jpeg.JPEGImageEncoder写的接口类BMPEncoder。

interface BMPEncoder {

	public void encode(BufferedImage bi) throws IOException;

	public static final int BIT_COUNT_BLACKWHITE = 1;
	public static final int BIT_COUNT_16COLORS = 4;
	public static final int BIT_COUNT_256COLORS = 8;
	public static final int BIT_COUNT_TRUECOLORS = 24;

}

// BMPEncoder接口的实现BMPEncoderImpl。

class BMPEncoderImpl implements BMPEncoder {

	private OutputStream out;

	public BMPEncoderImpl(OutputStream out) {
		this.out = out;
	}

	public void encode(BufferedImage bi) throws IOException {
		int width = bi.getWidth();
		int height = bi.getHeight();

		boolean needBlank = (width % 4 != 0);

		int size = width * height * 3;
		if (needBlank) {
			size += (width % 4) * height;
		}

		BMPFileHeader fileHeader = new BMPFileHeader(size, 54);
		BMPInfoHeader infoHeader = new BMPInfoHeader(width, height, BIT_COUNT_TRUECOLORS);

		byte[] rgbs = new byte[3];
		byte[] blank = new byte[width % 4];

		out.write(fileHeader.getData());
		out.write(infoHeader.getData());

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

}

// 一个工厂类BMPCodec。

class BMPCodec {

	public static BMPEncoder createBMPEncoder(OutputStream dest) {
		return new BMPEncoderImpl(dest);
	}

}

public class BMPSaver {
	public BMPSaver(FileOutputStream out, BufferedImage image) throws Exception {
		BMPCodec.createBMPEncoder(out).encode(image);
	}
};
