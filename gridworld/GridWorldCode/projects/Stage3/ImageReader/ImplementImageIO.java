import imagereader.IImageIO;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImplementImageIO implements IImageIO {
	private int lastMark = 0;
	final int wordLen = 4, colorful = 24, three = 3, alpha = 0xff << 24;
	final int firstOff = 2, secondOff = 18, thirdOff = 22, fourthOff = 28, fifthOff = 54;
	private BufferedInputStream bufferedInputStream;
	
	@Override
	public Image myRead(String filePath) throws IOException {
		bufferedInputStream = new BufferedInputStream(new FileInputStream(new File(filePath)));
		final int size = readByte(firstOff, wordLen);
		final int width = readByte(secondOff, wordLen);
		final int height = readByte(thirdOff, wordLen);
		final int colorDeepth = readByte(fourthOff, 2);
		
		if(colorDeepth != 24) {
			bufferedInputStream.close();
			return null;
		}
		
		final int line = size / height;
		byte[] bitMapData = new byte[size];
		bufferedInputStream.read(tem, 0, fifthOff - lastMark);
		bufferedInputStream.read(bitMapData, 0, size);
		
		int[] pix = new int[width * height];
		
		for(int h = height - 1; h >= 0; --h) {
			for(int w = 0; w < width; ++w) {
				byte[] rgb = new byte[three];
				for(int i = 0; i < 3; ++i) {
					rgb[i] = bitMapData[(height - h - 1) * line + 3 * w + i];
				}
				pix[h * width + w] = alpha | ByteArrayToInt(rgb);
			}
		}
		
		MemoryImageSource memoryImageSource = new MemoryImageSource(width, height, pix, 0, width);
		Image image = Toolkit.getDefaultToolkit().createImage(memoryImageSource);
		bufferedInputStream.close();
		return image;
	}
	
	private byte[] tem = new byte[50];
	
	private int readByte(int off, int len) throws IOException {
		byte[] des = new byte[len];
		bufferedInputStream.read(tem, 0, off - lastMark);
		bufferedInputStream.read(des, 0, len);
		lastMark = off + len;
		return ByteArrayToInt(des);
	}
	
	private int ByteArrayToInt(byte[] byteArray) {
		int intOut = 0;
		for(int i = 0; i < byteArray.length; ++i) {
			intOut |= (byteArray[i] & 0xff) << (8 * i);
		}
		return intOut;
	}
	
//	png, jpg, gif
	@Override
	public Image myWrite(Image image, String filePath) throws IOException {
		BufferedImage bufferedImage = ToBufferedImage.toBufferedImage(image);
        ImageIO.write(bufferedImage, "gif", new File(filePath+".gif"));
        return image;
	}
	
//	private static String byte2hex(byte [] buffer){
//		String h = "";
//		
//		for(int i = 0; i < buffer.length; i++){
//			String temp = Integer.toHexString(buffer[i] & 0xFF);
//			if(temp.length() == 1){
//				temp = "0" + temp;
//			}
//			h = h + " "+ temp;
//		}
//		
//		return h;
//		
//	}

}