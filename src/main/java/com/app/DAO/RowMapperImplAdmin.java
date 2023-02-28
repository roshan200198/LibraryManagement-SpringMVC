package com.app.DAO;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import com.app.Pojo.Admin;

public class RowMapperImplAdmin implements RowMapper<Admin>{

	@Autowired
	Admin admin;

	public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
		admin.setName(rs.getString(2));
		admin.setMailId(rs.getString(3));
		admin.setContact(rs.getString(4));
		admin.setPassword(rs.getString(5));
		
		return admin;
	}

}
