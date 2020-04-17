package com.river.iclassroom.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.river.iclassroom.model.Borrow;

@Repository
public class BorrowDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private RowMapper<Borrow> borrowMapper = new BeanPropertyRowMapper<Borrow>(Borrow.class);

	public int create(String id, String name, int num) {
		String SQL = "insert into Borrow values (?, ?, ?)";
		int res = jdbcTemplate.update(SQL, id, name, num);
		return res;
	}

	public int delete(String id, String name) {
		String SQL = "delete from Borrow where reservationid = ? and objectName = ?";
		int res = jdbcTemplate.update(SQL, id, name);
		return res;
	}

	public int delete(String id) {
		String SQL = "delete from Borrow where reservationid = ?";
		int res = jdbcTemplate.update(SQL, id);
		return res;
	}

	public int update(String id, String name, int num) {
		String SQL = "update Borrow set num = ? where reservationid = ? and objectName = ?";
		int res = jdbcTemplate.update(SQL, num, id, name);
		return res;
	}

	public List<Borrow> queryBorrows(String id) {
		String SQL = "select * from Borrow where reservationid = ?";
		List<Borrow> res = jdbcTemplate.query(SQL, borrowMapper, id);
		return res;
	}
}