package org.mzj.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TemplateController {
	private static final Logger logger = LoggerFactory.getLogger(TemplateController.class);
	// http://localhost:8080/hello?userName=mzj
	@RequestMapping("hello")
	public String hello(HttpServletRequest request, Model model, String userName) {
		logger.info("userName=" + userName);
		model.addAttribute("userName", userName);
		return "hello";
	}
}
