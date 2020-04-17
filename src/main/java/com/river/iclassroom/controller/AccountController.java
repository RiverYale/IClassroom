package com.river.iclassroom.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.river.iclassroom.service.AdminService;

@Controller
@ResponseBody
@RequestMapping("/account")
public class AccountController{
	@Autowired
	private AdminService adminService;

	@PostMapping("/login")
	public Map<String, Object> login(HttpServletRequest req, String username, String password) {
		Map<String,Object> map = new HashMap<>();
		boolean res = adminService.login(username, password);
		if(res) {
			HttpSession session = req.getSession();
			session.setAttribute("username", username);
			map.put("nickname", adminService.getNickname(username));
		}
		map.put("res", res);
		return map;
	}

	@PostMapping("/logout")
	public void logout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.invalidate();
	}
}