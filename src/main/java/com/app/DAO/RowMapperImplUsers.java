package com.app.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.app.Pojo.LibraryBean;
//import com.app.Pojo.UserTable;
import com.app.Pojo.Users;

public class RowMapperImplUsers implements RowMapper<Users> {

	public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
		Users users = new Users();
			
		 //lib.setId(rs.getInt("id"));
		users.setName(rs.getString("Name"));
		users.setUsername(rs.getString("username"));
		users.setUserId(rs.getInt("userId"));
		users.setMobNumber(rs.getString("MobNumber"));
		users.setPassword(rs.getString("password"));
		users.setRole(rs.getString("role"));
		
		return users;
	}

}
