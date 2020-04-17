package com.river.iclassroom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.river.iclassroom.dao.AdminDao;
import com.river.iclassroom.model.Admin;

@Service
public class AdminService {
	@Autowired
	private AdminDao adminDao;
	
	public boolean login(String username, String password) {
		Admin admin = adminDao.getAdmin(username);
		if(admin==null) {
			return false;
		}
		return password.equals(admin.getPassword());
	}
	
	public String getNickname(String username) {
		Admin admin = adminDao.getAdmin(username);
		return admin.getNickname();
	}
}
