package com.app.DAO;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

@Component("jdbcConn")
public class JdbcConnection {

	
	@Bean("dataSource")
	public DataSource mysqlDataSource() {
		
		String url = "jdbc:mysql://127.0.0.1:3306/roshan";
		String user = "root";
		String password = "root";
		
		DataSource dataSource = new DriverManagerDataSource(url,user,password);
		 
//		 dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//		 dataSource.setUrl(url);
//		 dataSource.setUsername("root");
//		 dataSource.setPassword(password);
		
		return dataSource;
		
	}
	
	JdbcTemplate jdbcTemplate = new JdbcTemplate(mysqlDataSource());
	
}
