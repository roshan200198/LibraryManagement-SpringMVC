package com.app.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.app.Pojo.LibraryBean;



public class RowMapperSearchAvailableBookImpl implements RowMapper<LibraryBean> {
	
	
	
	public LibraryBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		 LibraryBean lib = new LibraryBean();
		
//		 lib.setUserId(rs.getInt("UserId"));
		 lib.setBookId(rs.getString("BookId"));
		 lib.setBookName(rs.getString("BookName"));
		 lib.setBookStatus(rs.getString("BookStatus"));
		 lib.setBookNumbers(rs.getInt("BookNumbers"));
		
		return lib;
	}
	
}
