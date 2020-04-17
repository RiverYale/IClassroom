package com.river.iclassroom.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.river.iclassroom.model.Borrow;
import com.river.iclassroom.model.Reservation;
import com.river.iclassroom.model.ViolationRecord;
import com.river.iclassroom.service.AdminService;
import com.river.iclassroom.service.MainService;

@Controller
@ResponseBody
@RequestMapping("/main")
public class MainController {
	@Autowired
	private MainService mainService;
	@Autowired
	private AdminService adminService;

	@RequestMapping("/init")
	public Map<String, Object> initMain(HttpSession session) {
		Map<String,Object> map = new HashMap<>();
		String username = (String) session.getAttribute("username");

		JSONArray res = mainService.getClassroomMenuArray();
		int unsolved = mainService.countUnsolved();
		String nickname = adminService.getNickname(username);
		map.put("buildings", res);
		map.put("unsolved", unsolved);
		map.put("nickname", nickname);
		return map;
	}

	@PostMapping("/reservation")
	public Map<String, Object> queryReservation(String classroom, int year, int month, int date, int course) {
		Map<String,Object> map = new HashMap<>();
		Reservation res = mainService.getReservation(classroom, year, month, date, course);
		List<Borrow> bor = mainService.queryBorrows(res);
		map.put("reservation", res);
		map.put("borrows", bor);
		return map;
	}

	@PostMapping("/violations")
	public Map<String, Object> queryViolation(String classroom) {
		Map<String,Object> map = new HashMap<>();
		List<ViolationRecord> res = mainService.queryViolationRecords(classroom);
		map.put("res", res);
		return map;
	}
}