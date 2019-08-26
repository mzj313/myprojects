package org.mzj.demo.jave;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;

public class ConvertVideo {
	// 原始文件
	private String videoPath;
	// 目标文件
	private String targetPath;
	// 转换工具
	private String convertTool;

	public ConvertVideo(String videoPath, String targetPath, String convertTool) {
		this.videoPath = videoPath;
		this.targetPath = targetPath;
		this.convertTool = convertTool;
	}

	public synchronized void process() {
		int type = checkContentType();
		if (type == 0) {
			this.ffmpegTransVideo();
			this.ffmpegTransImage();
		} else if (type == 1) {
			this.mencoderTransVideo();
		}
	}

	/**
	 * 检查视频格式
	 * 
	 * @return
	 */
	public synchronized int checkContentType() {
		String type = videoPath.substring(videoPath.lastIndexOf(".") + 1, videoPath.length()).toLowerCase();
		// ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）
		if (type.equals("avi")) {
			return 0;
		} else if (type.equals("mpg")) {
			return 0;
		} else if (type.equals("wmv")) {
			return 0;
		} else if (type.equals("3gp")) {
			return 0;
		} else if (type.equals("mov")) {
			return 0;
		} else if (type.equals("mp4")) {
			return 0;
		} else if (type.equals("asf")) {
			return 0;
		} else if (type.equals("asx")) {
			return 0;
		} else if (type.equals("flv")) {
			return 0;
		}
		// 对ffmpeg无法解析的文件格式(wmv9，rm，rmvb等),
		// 可以先用别的工具（mencoder）转换为avi(ffmpeg能解析的)格式.
		else if (type.equals("wmv9")) {
			return 1;
		} else if (type.equals("rm")) {
			return 1;
		} else if (type.equals("rmvb")) {
			return 1;
		}
		return 9;
	}

	public synchronized static boolean checkfile(String path) {
		File file = new File(path);
		if (!file.isFile()) {
			return false;
		}
		return true;
	}

	/**
	 * 使用mencoder转码
	 * 
	 * @param videoPath
	 *            源路径 -- 要转换的视频文件
	 * @param targetPath
	 *            目标路径 -- 转换后的视频flv
	 * @return 返回目标路径
	 */
	public synchronized boolean mencoderTransVideo() {
		List<String> command = new java.util.ArrayList<String>();
		command.add(convertTool);
		command.add(videoPath);
		// 音频采用mp3编码
		command.add("-oac");
		command.add("mp3lame");
		// 采用高质DivX视频编码，视频码率为112kbps
		command.add("-ovc");
		command.add("lavc");
		command.add("-lavcopts");
		command.add("vcodec=flv:vbitrate=500:mbd=2:mv0:trell:v4mv:cbp:last_pred=3:dia=-1:cmp=3:vb_strategy=1");
		command.add("-lameopts");
		command.add("abr:br=56");
		// 声音采样频率设置，现为22K
		command.add("-srate");
		command.add("22050");
		// -sws就是用来设置品质的，默认值为2
		command.add("-sws");
		command.add("3");
		// 宽度为208，高度自动调整保持比例；
		// -vf scale=-3:176宽度自动调整保持比例，高度为176；如果想保持原来的大小可以不要这个参数
		command.add("-vf");
		command.add("scale=512:-3");
		// 帧速率设置
		command.add("-ofps");
		command.add("18");
		/*
		 * mode=3:cbr:br=24单声道 音频码率为24kbps;-lameopts
		 * mode=0:cbr:br=24立体声，音频码率为24kbps; 还可设置音量，-lameopts
		 * mode=3:cbr:br=32:vol=1，设置范置为1~10，但不宜设得太高
		 */
		command.add("-lameopts");
		command.add("vbr=3:br=128");
		command.add("-o");
		command.add(targetPath);
		// 控制台显示执行的命令
		System.out.println("command: " + command.toString().replace(",", ""));
		try {
			ProcessBuilder builder = new ProcessBuilder();
			builder.command(command);
			Process process = builder.start();
			InputStream is = process.getErrorStream();
			InputStreamReader inputStreamReader = new InputStreamReader(is);
			BufferedReader inputBufferedReader = new BufferedReader(inputStreamReader);
			String line = null;
			StringBuilder stringBuilder = new StringBuilder();
			while ((line = inputBufferedReader.readLine()) != null) {
				stringBuilder.append(line);
			}
			inputBufferedReader.close();
			inputBufferedReader = null;
			inputStreamReader.close();
			inputStreamReader = null;
			is.close();
			is = null;
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean ffmpegTransVideo() {
		List<String> commend = new java.util.ArrayList<String>();
		commend.add(convertTool);
		commend.add("-i");
		commend.add(videoPath);
		commend.add("-y");
		commend.add("-ab");
		commend.add("64");
		commend.add("-ac");
		commend.add("2");
		commend.add("-ar");
		commend.add("22050");
		// 清晰度 -qscale 4 为最好可是文件大, -qscale 6就可以了
		commend.add("-qscale");
		commend.add("6");
		commend.add(targetPath);
		try {
			ProcessBuilder builder = new ProcessBuilder();
			builder.command(commend);
			Process process = builder.start();
			InputStream is = process.getErrorStream();
			InputStreamReader inputStreamReader = new InputStreamReader(is);
			BufferedReader inputBufferedReader = new BufferedReader(inputStreamReader);
			String line = null;
			StringBuilder stringBuilder = new StringBuilder();
			while ((line = inputBufferedReader.readLine()) != null) {
				stringBuilder.append(line);
			}
			inputBufferedReader.close();
			inputBufferedReader = null;
			inputStreamReader.close();
			inputStreamReader = null;
			is.close();
			is = null;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// 生成图片 参数String newfilename, String newimg
	public synchronized boolean ffmpegTransImage() {
		List<String> commend = new java.util.ArrayList<String>();
		commend.add(convertTool);
		commend.add("-i");
		commend.add(videoPath);
		commend.add("-y");
		commend.add("-f");
		commend.add("image2");
		commend.add("-ss");
		commend.add("10");
		commend.add("-t");
		commend.add("0.001");
		commend.add("-s");
		commend.add("640x480");
		commend.add(targetPath);
		try {
			ProcessBuilder builder = new ProcessBuilder();
			builder.command(commend);
			Process process = builder.start();
			InputStream is = process.getErrorStream();
			InputStreamReader inputStreamReader = new InputStreamReader(is);
			BufferedReader inputBufferedReader = new BufferedReader(inputStreamReader);
			String line = null;
			StringBuilder stringBuilder = new StringBuilder();
			while ((line = inputBufferedReader.readLine()) != null) {
				stringBuilder.append(line);
			}
			inputBufferedReader.close();
			inputBufferedReader = null;
			inputStreamReader.close();
			inputStreamReader = null;
			is.close();
			is = null;
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// ffmpeg视频剪辑：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）
	public synchronized boolean processFLVjianji(String startTime, String endTime) {

		if (!checkfile(videoPath)) {
			System.out.println(videoPath + " is not file");
			return false;
		}

		List<String> command = new ArrayList<String>();
		command.add(convertTool);
		command.add("-i");
		command.add(videoPath);
		command.add("-ab");
		command.add("56");
		command.add("-ar");
		command.add("22050");
		command.add("-qscale");
		command.add("8");
		command.add("-r");
		command.add("15");
		command.add("-s");
		command.add("600x500");
		command.add("-y");
		/**
		 * ffmpeg -i ./plutopr.mp4 -vcodec copy -acodec copy -ss 00:00:10 -to
		 * 00:00:15 ./cutout1.mp4 -y -ss time_off set the start time offset
		 * 设置从视频的哪个时间点开始截取，上文从视频的第10s开始截取 -to
		 * 截到视频的哪个时间点结束。上文到视频的第15s结束。截出的视频共5s.
		 */
		command.add("-ss");
		command.add(startTime);
		command.add("-to");
		command.add(endTime);
		command.add(targetPath);

		try {
			System.out.println("command: " + command.toString().replace(",", ""));
			ProcessBuilder builder = new ProcessBuilder();
			builder.command(command);
			Process process = builder.start();
			return true;
		} catch (Exception e) {
			System.err.println(e);
			return false;
		}
	}
	
	/**
	 * @param time, 秒数
	 * @return
	 */
	public static String timeConvert(int time) {
		long a = time % 60;
		String second;
		if (a < 10) {
			second = "0" + a;
		} else {
			second = String.valueOf(a);
		}
		long b = (time / 60) % 60;
		String minute;
		if (b < 10) {
			minute = "0" + b;
		} else {
			minute = String.valueOf(b);
		}
		long c = (time / 3600);
		String hour;
		if (c < 10) {
			hour = "0" + c;
		} else {
			hour = String.valueOf(c);
		}
		String tm = hour + ":" + minute + ":" + second;
		return tm;
	}
	
	/**
	 * @param source 例如：C:/Users/Administrator/Desktop/Video.avi
	 * @param target	例如：d:/zzz/fileName	(不需要文件后缀)
	 * @param convertTool 例如：C:/Users/Administrator/Desktop/rdt/WebRoot/WEB-INF/tools/ffmpeg.exe
	 * @param averTime	秒数, 平均时间
	 */
	public static void hjzggCut(String source, String target, String convertTool, final int averTime) {
		ConvertVideo video = null;
		final int fileTimeTot = Integer.valueOf(getVideoLength(source));
		final String fileSuffix = source.substring(source.lastIndexOf('.')-1);
		int cnt = 1;
		final int cutNum = fileTimeTot%averTime == 0 ? fileTimeTot/averTime : fileTimeTot/averTime+1;
		String beginTime = null, endTime = null;
		for(beginTime="00:00:00", endTime=timeConvert(averTime); cnt<=cutNum ; ++cnt) {
			System.out.println(beginTime + ", " + endTime + ", " + timeConvert(fileTimeTot));
			String targetPath = target + cnt + fileSuffix;
			video = new ConvertVideo(source, targetPath, convertTool);
			boolean bool = video.processFLVjianji(beginTime, endTime);
			System.out.println(">>>>>>" + targetPath + " is " + (bool ? "success" : "fail" + "!!!"));
			beginTime = endTime;
			endTime = averTime*(cnt+1) >= fileTimeTot ? timeConvert(fileTimeTot) : timeConvert(averTime*(cnt+1));
		}
	}
	
	public static String getVideoLength(String source,int aa){
		  String s=null;
		  File file = new File(source); 
		  Encoder encoder = new Encoder(); 
	        try { 
	             MultimediaInfo m = encoder.getInfo(file); 
	             long ls = m.getDuration()/1000;
	             ls= ls/aa;
	             long a=ls%60;
	             String a1;
	             if(a<10){
	            	 a1="0"+a;
	             }else{
	            	 a1=String.valueOf(a);
	             }
	             long b= (ls/60)%60;
	             String b1;
	             if(b<10){
	            	 b1="0"+b;
	             }else{
	            	 b1=String.valueOf(b);
	             }
	             long c=(ls/3600);
	             String c1;
	             if(c<10){
	            	 c1="0"+c;	 
	             }else{
	            	 c1=String.valueOf(c);
	             }
	             s=c1+":"+b1+":"+a1;
           } catch (Exception e) { 
	            e.printStackTrace(); 
	        }
			return s; 
	        
	  }
	 
	  public static String getVideoLength(String source){
		  File file = new File(source); 
		  long ls=0;
		  Encoder encoder = new Encoder(); 
	        try { 
	             MultimediaInfo m = encoder.getInfo(file); 
	             ls = m.getDuration()/1000;
           } catch (Exception e) { 
	            e.printStackTrace(); 
	        }
			return String.valueOf(ls);
	        
	  }
}
