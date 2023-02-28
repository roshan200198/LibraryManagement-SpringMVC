package com.app.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.tree.RowMapper;

import org.springframework.lang.Nullable;

import com.app.Pojo.UserTable;

public class RowMapperImplShowUser implements org.springframework.jdbc.core.RowMapper<UserTable> {

	
	public UserTable mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserTable lib = new UserTable();
			
		 //lib.setId(rs.getInt("id"));
//		 lib.setUserId(rs.getInt("userId"));
//		 lib.setUserName(rs.getString("userName"));
		 lib.setBookId(rs.getString("bookId"));
		 lib.setBook(rs.getString("book"));
		 lib.setBookNumbers(rs.getInt("issueBookCount"));
		
		
		return lib;
	}
	
}
