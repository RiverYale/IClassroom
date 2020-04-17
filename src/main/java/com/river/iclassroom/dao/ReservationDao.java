package com.river.iclassroom.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.river.iclassroom.model.AdminRecord;
import com.river.iclassroom.model.Reservation;
import com.river.iclassroom.model.UserRecord;

@Repository
public class ReservationDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private BorrowDao borrowDao;
	@Autowired
	private ViolationDao violationDao;

	private RowMapper<Reservation> reservationMapper = new BeanPropertyRowMapper<Reservation>(Reservation.class);
	private RowMapper<UserRecord> userRecordMapper = new BeanPropertyRowMapper<UserRecord>(UserRecord.class);
	private RowMapper<AdminRecord> adminRecordMapper = new BeanPropertyRowMapper<AdminRecord>(AdminRecord.class);

	public int create(Reservation r) {
		String SQL = "insert into Reservation values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now(), ?, ?)";
		int res = jdbcTemplate.update(SQL,
				r.getId(),
				r.getClassroom(),
				r.getReserveDate(),
				r.getStartCourse(),
				r.getEndCourse(),
				r.getBorrowA(),
				r.getBorrowAId(),
				r.getBorrowB(),
				r.getBorrowBId(),
				r.getBorrowC(),
				r.getBorrowCId(),
				r.getPhoneA(),
				r.getPeopleNum(),
				r.getUsage(),
				0,
				"",
				0);
		return res;
	}

	public int delete(String id) {
		borrowDao.delete(id);
		violationDao.delete(id);
		String SQL = "delete from Reservation where id = ?";
		int res = jdbcTemplate.update(SQL, id);
		return res;
	}

	public int updateResult(String id, int result, String remarks) {
		String SQL = "update Reservation set result = ?, remarks = ? where id = ?";
		int res = jdbcTemplate.update(SQL, result, remarks, id);
		return res;
	}

	public List<AdminRecord> getAdminRecordsByResult(int result) {
		return getAdminRecordsByResult(result, false);
	}

	public List<AdminRecord> getAdminRecordsByResult(int result, boolean exclude) {
		if(exclude) {
			String SQL = "select * from AdminRecord where result != ? order by timestamp desc";
			List<AdminRecord> res = jdbcTemplate.query(SQL, adminRecordMapper, result);
			return res;
		} else {
			String SQL = "select * from AdminRecord where result = ? order by timestamp desc";
			List<AdminRecord> res = jdbcTemplate.query(SQL, adminRecordMapper, result);
			return res;
		}
	}

	public List<AdminRecord> getAdminRecordsList() {
		String SQL = "select * from AdminRecord order by timestamp desc";
		List<AdminRecord> res = jdbcTemplate.query(SQL, adminRecordMapper);
		return res;
	}

	public int countUnsolved() {
		String SQL = "select count(*) from Reservation where result = 0";
		int res = jdbcTemplate.queryForObject(SQL, Integer.class);
		return res;
	}

	public List<UserRecord> getUserRecordList(String borrowA, String borrowAId) {
		String SQL = "select * from UserRecord where borrowA = ? and borrowAId = ? order by timestamp desc";
		List<UserRecord> res = jdbcTemplate.query(SQL, userRecordMapper, borrowA, borrowAId);
		return res;
	}

	public Reservation getReservation(String classroom, int year, int month, int date, int course) {
		Date reserveDate = Date.valueOf(year+"-"+month+"-"+date);
		String SQL = "select * from Reservation where classroom=? and result=1 and reservedate=? and ? between startcourse and endcourse";
		Reservation res = null;
		try {
			res = jdbcTemplate.queryForObject(SQL, reservationMapper, classroom, reserveDate, course);
		}catch(EmptyResultDataAccessException e) {
			System.out.printf("no result for 'getReservation(%s, %d, %d, %d, %d)'\n", classroom, year, month, date, course);
		}
		return res;
	}

	public Reservation getReservation(String id) {
		String SQL = "select * from Reservation where id = ?";
		Reservation res = null;
		try {
			res = jdbcTemplate.queryForObject(SQL, reservationMapper, id);
		}catch(EmptyResultDataAccessException e) {
			System.out.printf("no result for 'getReservation(%s)'\n", id);
		}
		return res;
	}

	public boolean isConflicted(String classroom, Date reserveDate, int startCourse, int endCourse) {
		String SQL = "select count(*) from Reservation where classroom=? and reservedate=? and endCourse>=? and startCourse<=? and result=1";
		int res = jdbcTemplate.queryForObject(SQL, Integer.class, classroom, reserveDate, startCourse, endCourse);
		return res > 0;
	}
}