package com.river.iclassroom.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.river.iclassroom.model.Admin;

@Repository
public class AdminDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private RowMapper<Admin> adminMapper = new BeanPropertyRowMapper<Admin>(Admin.class);

	public int create(String username, String password) {
		String SQL = "insert into Admin (username, password) values (?, ?)";
		int res = jdbcTemplate.update(SQL, username, password);
		return res;
	}

	public Admin getAdmin(String username) {
		String SQL = "select * from Admin where username = ?";
		Admin res = null;
		try {
			res = jdbcTemplate.queryForObject(SQL, adminMapper, username);
		}catch(EmptyResultDataAccessException e) {
			System.out.printf("no result for 'getAdmin(%s)'\n", username);
		}
		return res;
	}

	public int delete(String username) {
		String SQL = "delete from Admin where username = ?";
		int res = jdbcTemplate.update(SQL, username);
		return res;
	}

	public int update(String username, String password){
		String SQL = "update Admin set password = ? where username = ?";
		int res = jdbcTemplate.update(SQL, password, username);
		return res;
	}
}