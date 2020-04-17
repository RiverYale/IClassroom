package com.river.iclassroom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.river.iclassroom.dao.BorrowDao;
import com.river.iclassroom.dao.ClassroomDao;
import com.river.iclassroom.dao.ReservationDao;
import com.river.iclassroom.dao.ViolationDao;
import com.river.iclassroom.model.Borrow;
import com.river.iclassroom.model.Classroom;
import com.river.iclassroom.model.Reservation;
import com.river.iclassroom.model.ViolationRecord;

@Service
public class MainService {
	@Autowired
	private ClassroomDao classroomDao;
	@Autowired
	private ReservationDao reservationDao;
	@Autowired
	private BorrowDao borrowDao;
	@Autowired
	private ViolationDao violationDao;

	public JSONArray getClassroomMenuArray() {
		List<Classroom> classroomList = classroomDao.getClassroomList();
		JSONArray res = new JSONArray();
		for(int i=0;i<classroomList.size();i++) {
			String thisBuilding = classroomList.get(i).getBuilding();
			if(i==0 || !thisBuilding.equals(classroomList.get(i-1).getBuilding())) {
				JSONObject obj = new JSONObject();
				obj.put("name", thisBuilding);
				obj.put("classrooms", new JSONArray());
				res.add(obj);
			}
			JSONObject temp = (JSONObject) res.get(res.size()-1);
			JSONArray array = temp.getJSONArray("classrooms");
			JSONObject obj = new JSONObject();
			obj.put("number", classroomList.get(i).getNumber());
			obj.put("id", classroomList.get(i).getId());
			array.add(obj);
		}
		return res;
		/*构建的JSONArray示例：
		buildings: [
			{
				name: '一教A',
				classrooms: [
					{
						number: 'A505',
						id: '01001001'
					}
				]
			}
		],*/
	}

	public int countUnsolved() {
		return reservationDao.countUnsolved();
	}

	public Reservation getReservation(String classroom, int year, int month, int date, int course) {
		Reservation res = reservationDao.getReservation(classroom, year, month, date, course);
		return res;
	}

	public List<Borrow> queryBorrows(Reservation reservation) {
		String reservationId = "";
		if(reservation != null) {
			reservationId = reservation.getId();
		}
		List<Borrow> res = borrowDao.queryBorrows(reservationId);
		return res;
	}

	public List<ViolationRecord> queryViolationRecords(String classroom) {
		List<ViolationRecord> res = violationDao.queryViolationRecords(classroom);
		return res;
	}
}
