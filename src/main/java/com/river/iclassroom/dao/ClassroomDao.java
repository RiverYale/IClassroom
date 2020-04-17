package com.river.iclassroom.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.river.iclassroom.model.Classroom;

@Repository
public class ClassroomDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private RowMapper<Classroom> classroomMapper = new BeanPropertyRowMapper<Classroom>(Classroom.class);

	public int create(String id, String area, String building, String number, String type) {
		String SQL = "insert into Classroom values (?, ?, ?, ?, ?)";
		int res = jdbcTemplate.update(SQL, id, area, building, number, type);
		return res;
	}

	public int delete(String id) {
		String SQL = "delete from Classroom where id = ?";
		int res = jdbcTemplate.update(SQL, id);
		return res;
	}

	public int update(String id, String area, String building, String number, String type) {
		String SQL = "update Classroom set area = ?, building = ?, number = ?, type = ? where id = ?";
		int res = jdbcTemplate.update(SQL, area, building, number, type, id);
		return res;
	}

	public List<Classroom> queryAvaiClassroom(int year, int month, int date, int startCourse, int endCourse) {
		Date reserveDate = Date.valueOf(year+"-"+month+"-"+date);
		String SQL = "select * from Classroom where id not in (select classroom from Reservation where reserveDate=? and endCourse>=? and startCourse<=? and result=1) order by id";
		List<Classroom> res = jdbcTemplate.query(SQL, classroomMapper, reserveDate, startCourse, endCourse);
		return res;
	}

	public List<Classroom> getClassroomList() {
		String SQL = "select * from Classroom order by id";
		List<Classroom> res = jdbcTemplate.query(SQL, classroomMapper);
		return res;
	}
}