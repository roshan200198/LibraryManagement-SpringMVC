package com.app.DAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.app.Pojo.ImportBook;
import com.app.Pojo.LibraryBean;

@Component
public class ImportExcelService {
	
	@Autowired
	ImportBook book;
	
	@Autowired
	JdbcConnection jdbcConn;

	@Autowired
	JdbcTemplate jdbcTemplate;
	
//	String filePath="D:\\MyWork\\BookData.csv";

//    int batchSize=20;
	
	public boolean bookIdExistInBooktable(String bookId) throws SQLException, Exception {
		boolean status = false;
		String query = "select BookId from book where BookId = ?;";
		List<Map<String, Object>> result = jdbcTemplate.queryForList(query,bookId);
		int x = result.size();
		
		if(x == 1) {
			status = true;
		}else {
			status = false;
		}
		
		return status;
		
	}
	
	
	
	public boolean insertBookImport(String query, String bookId, String bookName, String bookStatus, int bookNumber) throws SQLException, Exception {
		boolean status = false;

		// int x = jdbcTemplate.update(query,obj.getUserId(), obj.getBookId());
//		String query = "insert into bookimport(UserId,BookId,BookName,BookStatus) values(?,?,?,?);";
		
		int y = jdbcTemplate.update(query, bookId, bookName, bookStatus, bookNumber);

		if (y == 1) {
			status = true;
			System.out.println("Successfully imported book from excel to database..");
		}

		else {
			status = false;
			System.out.println("insert query problem!!.. not imported excel sheet..");
		}

		return status;

	}
	
	public boolean updateBook(String query, String bookId, String bookName, String bookStatus, int bookNumber) throws SQLException, Exception {
		boolean status = false;
		
		int x = jdbcTemplate.update(query, bookId, bookName, bookStatus, bookNumber, bookId );
		
		if (x == 1) {
			status = true;
			System.out.println("Successfully updated imported book from excel to database..");
		}

		else {
			status = false;
			System.out.println("update query problem!!.. not imported excel sheet..");
		}
		
		return status;
	}
	
	
	

}
