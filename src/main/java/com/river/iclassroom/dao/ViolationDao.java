package com.river.iclassroom.dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.river.iclassroom.model.ViolationRecord;

@Repository
public class ViolationDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private RowMapper<ViolationRecord> violationRecordMapper = new BeanPropertyRowMapper<ViolationRecord>(ViolationRecord.class);

	public int create(String reservationId, int num, String behavior, Timestamp timestamp) {
		String SQL = "insert into Violation values (?, ?, ?, ?)";
		int res = jdbcTemplate.update(SQL, reservationId, num, behavior, timestamp);
		return res;
	}

	public int delete(String reservationId) {
		String SQL = "delete from Violation where reservationId = ?";
		int res = jdbcTemplate.update(SQL, reservationId);
		return res;
	}

	public int update(String reservationId, String behavior) {
		String SQL = "update Violation set behavior = ? where reservationId = ?";
		int res = jdbcTemplate.update(SQL, behavior, reservationId);
		return res;
	}

	public List<ViolationRecord> queryViolationRecords(String classroom) {
		String SQL = "select * from ViolationRecord where classroom = ? order by timestamp desc";
		List<ViolationRecord> res = jdbcTemplate.query(SQL, violationRecordMapper, classroom);
		return res;
	}
}