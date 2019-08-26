package org.mzj.demo.jave;

public class JaveTest {

	public void 裁剪() {
		final String source = "D:/Material/video/1c.mp4";
		final String target = "D:/Material/video/2c";
		final int averTime = 5;
		final String convertTool = "ffmpeg.exe";
		System.out.println(">>>转换工具路径：" + convertTool);
		ConvertVideo.hjzggCut(source, target, convertTool, averTime);
	}
}
