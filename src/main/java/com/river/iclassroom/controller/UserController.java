package com.river.iclassroom.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.river.iclassroom.model.Classroom;
import com.river.iclassroom.model.UserRecord;
import com.river.iclassroom.service.UserService;

@Controller
@ResponseBody
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping("/query")
	public Map<String, Object> queryClassroom(int year, int month, int date, int startCourse, int endCourse) {
		Map<String,Object> map = new HashMap<>();
		List<Classroom> res = userService.queryClassroom(year, month, date, startCourse, endCourse);
		map.put("size", res.size());
		map.put("res", res);
		return map;
	}

	@PostMapping("/submit")
	public Map<String, Object> submit(String form){
		Map<String,Object> map = new HashMap<>();
		JSONObject obj = JSON.parseObject(form);
		boolean res = userService.submit(obj);
		map.put("res", res);
		return map;
	}

	@PostMapping("/record")
	public Map<String, Object> getUserRecord(String borrowA, String borrowAId) {
		Map<String,Object> map = new HashMap<>();
		List<UserRecord> res = userService.getUserRecord(borrowA, borrowAId);
		map.put("size", res.size());
		map.put("res", res);
		return map;
	}

	@PostMapping("/cancel")
	public Map<String, Object> cancelReservation(String id) {
		Map<String,Object> map = new HashMap<>();
		boolean res = userService.cancelReservation(id);
		map.put("res", res);
		return map;
	}
}