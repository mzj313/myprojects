package org.mzj.test;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MySessionListener implements HttpSessionListener {
	public static Map<String,HttpSession> sessions = new HashMap<String,HttpSession>();

	public void sessionCreated(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		System.out.println("添加session:" + session.getId());
		sessions.put(session.getId(), session);
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		System.out.println("移除session:" + session.getId());
		sessions.remove(session.getId());
	}

}
