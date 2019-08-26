package org.mzj.demo.javacv;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.junit.Test;

public class FrameGrabberTest {
	@Test
	public void test视频截图() {
		try {
			String filePath = "D:/Material/video/1a.mp4";
			FFmpegFrameGrabber grabber = FFmpegFrameGrabber.createDefault(filePath);
			grabber.start();
			
			String rotate = grabber.getVideoMetadata("rotate");
			int frameLen = grabber.getLengthInFrames();
			System.out.println("frameLen=" + frameLen);
			List<Integer> frames = Arrays.asList(new Integer[]{frameLen/5});//要截屏的帧位置
			Frame f;
			for (int i = 0; i < frameLen; i++) {//一帧一帧的扫描，速度慢
				f = grabber.grabImage();//类似游标的用法
				if (!frames.contains(i)) continue;
				if (null != rotate && rotate.length() > 1) {//旋转
					OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
					IplImage src = converter.convert(f);
					IplImage img = IplImage.create(src.height(), src.width(), src.depth(), src.nChannels());
					opencv_core.cvTranspose(src, img);
					opencv_core.cvFlip(img, img, Integer.valueOf(rotate));
					f = converter.convert(img);
				}
				
				String formatName = "png";
				String outputFilename = "D:/Material/video/2a.png";
				BufferedImage im = new Java2DFrameConverter().getBufferedImage(f);
				ImageIO.write(im, formatName, new File(outputFilename));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}