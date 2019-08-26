package org.mzj.demo;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;

public class ProcessTest {

	@Test
	public void testEnv() {
		Map<String, String> map = System.getenv();
		for(Iterator<String> itr = map.keySet().iterator();itr.hasNext();){
            String key = itr.next();
            System.out.println(key + "=" + map.get(key));
        }
		Properties properties = System.getProperties();
		for(Iterator<Object> itr = properties.keySet().iterator();itr.hasNext();){
            Object key = itr.next();
            System.out.println(key + "=" + properties.get(key));
        }
	}
	
	@Test
	public void testProcess() {
		execCmd("cmd /c set java_home");
		execCmd("cmd /c set path=%java_home%\\bin;%path%;");
		execCmd("cmd /c set path");
		execCmd("cmd /c java -version");
		execCmd("cmd /c D:/Java/jdk1.8.0_101/bin/java.exe -version");
//		execCmd("ping www.baidu.com");
//		execCmd("cmd /c dir");
	}

	public static void execCmd(String cmd) {
		System.out.println(cmd);
		try {
			Process process = Runtime.getRuntime().exec(cmd.trim().split(" "));
			InputStream is = process.getInputStream();//取得命令结果的输出流  
			byte[] inBuffer = new byte[1024];
			int len = 0;
			while ((len = is.read(inBuffer)) > -1) {
                System.out.print(new String(inBuffer, 0, len, "GBK"));
			}
			process.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
