package com.river.iclassroom.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pages")
public class PagesController {
	@GetMapping("/login")
	public String getLoginPage(HttpServletRequest req) { 
		return "login"; 
	}
	
	@GetMapping("/main")
	public String getMainPage(ModelMap model) { 
		return "main"; 
	}
	
	@GetMapping("/reqs")
	public String getReqsPage(ModelMap model) { 
		return "reqs"; 
	}
}