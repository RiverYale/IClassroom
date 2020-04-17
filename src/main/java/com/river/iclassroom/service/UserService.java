package com.river.iclassroom.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.river.iclassroom.dao.*;
import com.river.iclassroom.model.*;

@Service
public class UserService {
	@Autowired
	private ClassroomDao classroomDao;
	@Autowired
	private ReservationDao reservationDao;
	@Autowired
	private BorrowDao borrowDao;
	
	public List<Classroom> queryClassroom(int year, int month, int date, int startCourse, int endCourse) {
		List<Classroom> res = classroomDao.queryAvaiClassroom(year, month, date, startCourse, endCourse);
		return res;
	}
	
	public boolean submit(JSONObject form) {
		int year = form.getIntValue("year");
		int month = form.getIntValue("month");
		int date = form.getIntValue("date");
		String id = String.format("%020d", SnowFlake.nextId());
		
		Reservation reservation = new Reservation();
		reservation.setId(id);
		reservation.setClassroom(form.getString("classroom"));
		reservation.setReserveDate(Date.valueOf(year+"-"+month+"-"+date));
		reservation.setStartCourse(form.getIntValue("startCourse"));
		reservation.setEndCourse(form.getIntValue("endCourse"));
		reservation.setBorrowA(form.getString("borrowA"));
		reservation.setBorrowAId(form.getString("borrowAId"));
		reservation.setBorrowB(form.getString("borrowB"));
		reservation.setBorrowBId(form.getString("borrowBId"));
		reservation.setBorrowC(form.getString("borrowC"));
		reservation.setBorrowCId(form.getString("borrowCId"));
		reservation.setPhoneA(form.getString("phoneA"));
		reservation.setPeopleNum(form.getIntValue("peopleNum"));
		reservation.setUsage(form.getString("usage"));
		
		JSONArray equip = form.getJSONArray("borrow");
		reservationDao.create(reservation);
		for(int i=0;i<equip.size();i++) {
			JSONObject obj = equip.getJSONObject(i);
			borrowDao.create(id, obj.getString("objectName"), obj.getIntValue("objectNum"));
		}
		return true;
	}
	
	public List<UserRecord> getUserRecord(String borrowA, String borrowAId) {
		List<UserRecord> res = reservationDao.getUserRecordList(borrowA, borrowAId);
		return res;
	}
	
	public boolean cancelReservation(String id) {
		int res = reservationDao.delete(id);
		return (res == 1);
	}
}
