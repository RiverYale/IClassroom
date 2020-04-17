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

import com.river.iclassroom.model.AdminRecord;
import com.river.iclassroom.service.AdminService;
import com.river.iclassroom.service.MainService;
import com.river.iclassroom.service.ReqsService;

@Controller
@ResponseBody
@RequestMapping("/reqs")
public class ReqsController {
	@Autowired
	private ReqsService reqsService;
	@Autowired
	private MainService mainService;
	@Autowired
	private AdminService adminService;

	@RequestMapping("/init")
	public Map<String, Object> countUnsolved(HttpSession session) {
		Map<String, Object> map = new HashMap<>();
		String username = (String) session.getAttribute("username");
		map.put("unsolved", mainService.countUnsolved());
		map.put("nickname", adminService.getNickname(username));
		return map;
	}

	@PostMapping("/get")
	public Map<String, Object> getReqs(String type) {
		Map<String, Object> map = new HashMap<>();
		List<AdminRecord> res = reqsService.getReqs(type);
		map.put("res", res);
		return map;
	}

	@PostMapping("/handle")
	public Map<String, Object> handleReqs(String id, int result, String remarks) {
		Map<String, Object> map = new HashMap<>();
		String info[] = {"result参数错误", "处理成功", "处理失败，预约不存在或已被取消", "处理失败，预约内容与已同意的预约冲突", "错误，预约已处理"};
		int res = reqsService.handleReqs(id, result, remarks);
		map.put("res", res);
		map.put("info", info[res]);
		return map;
	}
}