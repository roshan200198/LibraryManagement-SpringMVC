package com.app.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.DAO.BookDao;
import com.app.Pojo.Admin;
import com.app.Pojo.LibraryBean;
import com.app.Pojo.UserSignup;
import com.app.Pojo.UserTable;
import com.app.Pojo.Users;

@Repository
public class BookDao {

	
	  @Autowired 
	  BCryptPasswordEncoder passwordEncoder; 
	
	@Autowired
	LibraryBean obj;
	@Autowired
	Admin admin;

	@Autowired
	JdbcConnection jdbcConn;

	@Autowired
	JdbcTemplate jdbcTemplate;

	public boolean addBook(LibraryBean obj) throws Exception {
		boolean status = false;

		String query = "insert into book(BookId, BookName,BookStatus, BookNumbers) values(?, ?, 'A', ?);";
		int x = jdbcTemplate.update(query, obj.getBookId(), obj.getBookName(), obj.getBookNumbers());

		if (x == 1) {
			status = true;
		} else
			status = false;

		return status;
	}
	
	public int getBookNumbers(String bookId) {
		
		String query = "select BookNumbers from book where BookId = ?;";
		int booknumbers = jdbcTemplate.queryForObject(query, Integer.class, bookId);
		System.out.println("BookNumber by BookId is: " + booknumbers);
		
		return booknumbers;
	}
	
public int getBookNumbersByBookName(String bookName) {
		
		String query = "select BookNumbers from book where BookName = ?;";
		int booknumbers = jdbcTemplate.queryForObject(query, Integer.class, bookName);
		System.out.println("BookNumber by BookId is: " + booknumbers);
		
		return booknumbers;
	}
	
	public int getBookNumbersByBookname(String bookName) {
		
		String query = "select BookNumbers from book where BookName = ?;";
		int booknumbers = jdbcTemplate.queryForObject(query, Integer.class, bookName);
		System.out.println("BookNumber by BookId is: " + booknumbers);
		
		return booknumbers;
	}
	
	public Integer getUserIdByUserName(String username) {
		Integer userId = 0;
		String query = "select userId from users where username = ?;";
		try {
			userId = jdbcTemplate.queryForObject(query, Integer.class, username);
			System.out.println("BookNumber by BookId is: " + userId);
		}catch(EmptyResultDataAccessException e) {
			System.out.println("No user found with username: " + username);
			e.printStackTrace();
		}
		
		
		return userId;
	}
	
public int getIssueBookNumbers(String username , String bookId) {
		
		String query = "select issueBookCount from usertable where userName = ? AND bookId = ?;";
		int booknumbers = jdbcTemplate.queryForObject(query, Integer.class,username, bookId);
		System.out.println("BookNumber by BookId is: " + booknumbers);
		
		return booknumbers;
	}
	
	public int getIssueBookCountByBookname(String userName, String bookName) {
		
		int count = 0;
		String query = "select issueBookCount from usertable where userName = ? and book = ?;";
		
		try {
			count = jdbcTemplate.queryForObject(query, Integer.class, userName, bookName);
			System.out.println("checkUserByBookname count is---: " + count);
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return count;
	}
	
	
	public String getBookIdByBookName(String bookName) {
		String bookId = "";
		String query = "select BookId from book where BookName = ?;";
		
		try {
			bookId = jdbcTemplate.queryForObject(query, String.class,bookName);
			System.out.println("checkUserByBookname count is---: " + bookId);
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return bookId;
	}

	public boolean issueBook(LibraryBean obj, int count) throws SQLException, Exception {
		boolean status = false;
		String bookStatus;
		int x = 0, y = 0, remainingBooks;
		String query1 = "update roshan.book set BookStatus = 'U', BookNumbers = ? where BookId = ?;";
		String query2 = "update roshan.book set BookStatus = 'A', BookNumbers = ? where BookId = ?;";
		String query3 = "insert into roshan.usertable(userId,userName,book,bookId,issueBookCount) values(?,?,?,?,?);";
		
		
//		System.out.println("query4----: " + query4);
//		int numberOfBooksAvailable = getBookNumbers(obj.getBookId());
		remainingBooks = obj.getBookNumbers() - count;
		System.out.println("obj.getBookId() - count == " + remainingBooks);
		
		if(userExistInUsertable(obj.getUserName(), obj.getBookId())) {
			int getIssueBookCount = getIssueBookCountByBookname(obj.getUserName(), obj.getBookName());
			String query4 = "update usertable set issueBookCount ="+ (getIssueBookCount+ count) + " where book = "+"'" + obj.getBookName()+"' " + " and userId =" + obj.getUserId() + ";";
			
			System.out.println("query3---> "+ query3);
			if(remainingBooks == 0 ) {
					x = jdbcTemplate.update(query1 , remainingBooks, obj.getBookId());
					y = jdbcTemplate.update(query4);
				 
			} else {
					x = jdbcTemplate.update(query2 , remainingBooks, obj.getBookId());
					y = jdbcTemplate.update(query4);
				
			}
			
		} else {
				if(remainingBooks == 0 ) {
					x = jdbcTemplate.update(query1 , remainingBooks, obj.getBookId());
					y = jdbcTemplate.update(query3 , obj.getUserId(), obj.getUserName(),obj.getBookName(), obj.getBookId(), count);
				 
			} else {
					x = jdbcTemplate.update(query2 , remainingBooks, obj.getBookId());
					y = jdbcTemplate.update(query3 , obj.getUserId(), obj.getUserName(),obj.getBookName(), obj.getBookId(),count);
			}
			
		}
		// int x = jdbcTemplate.update(query,obj.getUserId(), obj.getBookId());
		System.out.println("value of x and y is: "+ x+"  "+y);

		if (x == 1 && y == 1) {
			status = true;
			System.out.println("Issue form True...");
		}

		else {
			status = false;
			System.out.println("Issue form false...");
		}

		return status;

	}
	
	
	public boolean issueBookForUserDropdown(LibraryBean obj, int count) {
		
		// obj has---> bookName, userName, userId
		boolean status = false;
		String bookStatus;
		obj.setBookId(getBookIdByBookName(obj.getBookName()));
		int x = 0, y = 0;
		String query1 = "update roshan.book set BookStatus = 'U', BookNumbers = ? where BookName = ?;";
		String query2 = "update roshan.book set BookStatus = 'A', BookNumbers = ? where BookName = ?;";
		String query3 = "insert into roshan.usertable(userId,userName,book,bookId,issueBookCount) values(?,?,?,?,?);";
	
		
//		System.out.println("query4----: " + query4);
		int numberOfBooksAvailable = getBookNumbersByBookname(obj.getBookName());
		int remainingBooks = numberOfBooksAvailable - count;
		System.out.println("numberOfBooksAvailable - count == " + remainingBooks);
		
		if(userExistInUsertable(obj.getUserName(), obj.getBookName())) {
			int getIssueBookCount = getIssueBookCountByBookname(obj.getUserName(), obj.getBookName());
			String query4 = "update usertable set issueBookCount ="+ (getIssueBookCount + count) + " where book = "+"'" + obj.getBookName()+"' " + " and userName =" +"'" +obj.getUserName() + "'" + ";";
			System.out.println("query3---> "+ query3);
			if(remainingBooks == 0 ) {
					x = jdbcTemplate.update(query1 ,remainingBooks, obj.getBookName());
					y = jdbcTemplate.update(query4);
				
			} else {
				
					x = jdbcTemplate.update(query2 , remainingBooks, obj.getBookName());
					y = jdbcTemplate.update(query4);
				
			}
			
		} else {
				if(remainingBooks == 0 ) {
					x = jdbcTemplate.update(query1 , remainingBooks, obj.getBookName());
					y = jdbcTemplate.update(query3 , obj.getUserId(), obj.getUserName(),obj.getBookName(), obj.getBookId(),count);
				
			} else {
				
					x = jdbcTemplate.update(query2 , remainingBooks, obj.getBookName());
					y = jdbcTemplate.update(query3 , obj.getUserId(), obj.getUserName(),obj.getBookName(), obj.getBookId(),count);
				
			}
			
		}
		// int x = jdbcTemplate.update(query,obj.getUserId(), obj.getBookId());
		System.out.println("value of x and y is: "+ x+"  "+y);

		if (x == 1 && y == 1) {
			status = true;
			System.out.println("Issue form True...");
		}

		else {
			status = false;
			System.out.println("Issue form false...");
		}

		return status;

	}

	public boolean returnBook(LibraryBean obj) throws Exception {
		boolean status = false;
		String query1 = "update roshan.book set UserId = null,UserName = null, BookStatus = 'A' where BookId = ?;";
		String query2 = "DELETE FROM roshan.usertable WHERE userId = ?;";
		int x = jdbcTemplate.update(query1, obj.getBookId());
		int y = jdbcTemplate.update(query2, obj.getUserId());
		if (x == 1 && y == 1) {
			System.out.println("return book done..");
			status = true;
		} else {
			System.out.println("return book not updated");
		}
			return status;
	}
	
	public boolean returnBookForUser(LibraryBean obj, int count) throws Exception {
		boolean status = false; int x = 0,y = 0;
		String query1 = "update roshan.book set BookNumbers = ?, BookStatus = 'A' where BookId = ?;";
		String query2 = "DELETE FROM usertable WHERE userName = ? AND bookId = ?";
		
		String query3 = "update roshan.book set BookNumbers = ?,BookStatus = 'A' where BookId = ?;";
		String query4 = "update usertable set issueBookCount = ? WHERE userName= ? AND bookId = ?;";
		
		
		int updatedAvailableBooks = getBookNumbers(obj.getBookId()) + count;
		int updatedIsuueBooks = getIssueBookNumbers(obj.getUserName(),obj.getBookId()) - count;
		
		if(userExistInUsertable(obj.getUserName(), obj.getBookName())) {
			if(getIssueBookNumbers(obj.getUserName(),obj.getBookId()) == count) {
				x = jdbcTemplate.update(query1, updatedAvailableBooks, obj.getBookId());
				y = jdbcTemplate.update(query2, obj.getUserName(),obj.getBookId());
			} else {
				x = jdbcTemplate.update(query3,updatedAvailableBooks, obj.getBookId());
				y = jdbcTemplate.update(query4,updatedIsuueBooks, obj.getUserName(), obj.getBookId());
			}
		}
		

		if (x == 1 && y == 1) {
			System.out.println("return book done..");
			status = true;
		} else {
			System.out.println("return book not updated");
		}
			return status;
	}
	
	public boolean userExistInUsertable(String username, String bookName) {
		boolean status = false;
		String query1 = "select issueBookCount from usertable where userName= ? and book = ?;";

		try {
			System.out.println("in userExistInUsertable try ");
			int x = jdbcTemplate.queryForObject(query1,Integer.class, username, bookName);
			System.out.println("value of x in checkSigninMail::  " + x);
			System.out.println("userExistInUsertable x value "+ x);

			if (x > 0) {
				status = true;
				System.out.println("x > 0 "+status);
			} else
				status = false;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("status value for userExistInUsertable "+ status);
		
		return status;
	}

	public boolean mailExist(String mailId) {
		boolean status = false;

		String query1 = "SELECT id FROM users WHERE username=?";

		try {
			int x = jdbcTemplate.queryForObject(query1, Integer.class, mailId);
			System.out.println("value of x in checkSigninMail::  " + x);

			if (x == 1) {
				status = true;
			} else
				status = false;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return status;
	}

	public boolean signup(@ModelAttribute Users users, byte[] imageBytes) {

		boolean status = false;
		String query1 = "insert into roshan.users(Name, username, MobNumber, password, role,enabled, photo) values(?,?,?,?,?,1,?);";
		int x = jdbcTemplate.update(query1, users.getName(), users.getUsername(), users.getMobNumber(),
				 passwordEncoder.encode (users.getPassword()), users.getRole(), imageBytes);
		
		System.out.println("encoded password:: " +  passwordEncoder.encode (users.getPassword()));

		if (x == 1) {
			status = true;
		} else
			status = false;

		return status;
	}
	
	public byte[] getImageByUsername(String username ) {
		
		String query = "SELECT photo FROM users WHERE username = ?";
		@SuppressWarnings("deprecation")
		byte[] imgData = jdbcTemplate.queryForObject(query,byte[].class, username );

		
		return imgData;
	}

	/*
	 * public boolean signin(String mailId, String password) {
	 * 
	 * boolean status = false;
	 * 
	 * String query1 =
	 * "SELECT id FROM roshan.adminsignup WHERE MailID = ? AND Password = ?;"; //
	 * RowMapper<LibraryBean> rowMapper = new RowMapperImpl(); try { int x =
	 * jdbcTemplate.queryForObject(query1, Integer.class, mailId, password);
	 * System.out.println("Value of x is::   " + x); if (x == 1) { status = true; }
	 * else status = false; } catch (Exception x) { x.printStackTrace(); }
	 * 
	 * return status; }
	 */

	/*
	 * public boolean checkSigninMail(String mailId) { boolean status = false;
	 * 
	 * String query1 = "SELECT id FROM adminsignup WHERE MailID=?";
	 * 
	 * try { int x = jdbcTemplate.queryForObject(query1, Integer.class, mailId);
	 * System.out.println("value of x in checkSigninMail::  " + x);
	 * 
	 * if (x == 1) { status = true; } else status = false; } catch (Exception ex) {
	 * ex.printStackTrace(); }
	 * 
	 * return status; }
	 */

	public List<UserTable> viewIssueUser() {
		boolean status = false;

		String query1 = "SELECT usertable.userId, usertable.book, usertable.bookId FROM usertable"
				+ " INNER JOIN book ON book.UserId = usertable.userId;";
		// RowMapper<LibraryBean> rowMapper = new RowMapperImpl();

		List<UserTable> list = jdbcTemplate.query(query1, new RowMapperImplShowUser());

		return list;
	}

	public List<LibraryBean> viewBookTable() {

		String query = "SELECT * FROM book;";

		List<LibraryBean> list = jdbcTemplate.query(query, new RowMapperImpl());
		System.out.println("we are in viewBooktable dao.");
//		return jdbcTemplate.queryForObject(query,new RowMapperImpl(), LibraryBean.class);

		return list;
	}
	
	public List<LibraryBean> viewAvailableBook() {

		String query = "SELECT BookId, BookName, BookStatus,BookNumbers FROM book where BookStatus = 'A';";

		List<LibraryBean> list = jdbcTemplate.query(query, new RowMapperAvailBookImpl());
		System.out.println("we are in viewBooktable dao.");
//		return jdbcTemplate.queryForObject(query,new RowMapperImpl(), LibraryBean.class);

		return list;
	}

	public boolean userSignup(UserSignup usersignup) {

		boolean status = false;
		String query1 = "insert into usersignup(MailId, ContactNo, Password) values(?,?,?);";
		int x = jdbcTemplate.update(query1, usersignup.getMailId(), usersignup.getContactNo(),
				usersignup.getPassword());

		if (x == 1) {
			status = true;
		} else
			status = false;

		return status;
	}

	public boolean checkUserSigninMail(String mailId) {
		boolean status = false;

		String query1 = "SELECT id FROM usersignup WHERE MailId=?";

		try {
			int x = jdbcTemplate.queryForObject(query1, Integer.class, mailId);
			System.out.println("value of x in checkSigninMail::  " + x);

			if (x == 1) {
				status = true;
			} else
				status = false;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return status;
	}

	public boolean usersignin(String mailId, String password) {
		boolean status = false;

		String query1 = "SELECT id FROM usersignup WHERE MailId = ? AND Password = ?;";
		// RowMapper<LibraryBean> rowMapper = new RowMapperImpl();
		try {
			int x = jdbcTemplate.queryForObject(query1, Integer.class, mailId, password);
			System.out.println("Value of x is::   " + x);
			if (x == 1) {
				status = true;
			} else
				status = false;
		} catch (Exception x) {
			x.printStackTrace();
		}

		return status;
	}

	
	public List<UserTable> viewUserPagination(String username,int start, int end) {

		String query1 = "select book,bookId,issueBookCount from usertable where userName = ? limit ?,?;";
//	  RowMapper<UserTable> rowMapper = new RowMapperImplShowUser();

		List<UserTable> list = jdbcTemplate.query(query1, new RowMapperImplShowUser(),username, start, end);
		System.out.println("after viewUserPagination: ================");
		System.out.println("Pagination test :: " + list);

		return list;

	}
	
	
	public List<UserTable> viewUserPaginationForAdmin(int start, int end) {

		String query1 = "select userId,UserName,bookId,book,issueBookCount from usertable limit ?,?;";
//	  RowMapper<UserTable> rowMapper = new RowMapperImplShowUser();

		List<UserTable> list = jdbcTemplate.query(query1, new RowMapperImplShowUserForAdmin(), start, end);
		System.out.println("after viewUserPagination: ================");
		System.out.println("Pagination test :: " + list);

		return list;

	}
	
	
	public double viewUserPaginationForAdminTotalRow() {

		String query1 = "select userId,UserName,bookId,book,issueBookCount from usertable;";
//	  RowMapper<UserTable> rowMapper = new RowMapperImplShowUser();

		List<UserTable> list = jdbcTemplate.query(query1, new RowMapperImplShowUserForAdmin());
		System.out.println("after viewUserPagination: ================");
		System.out.println("Pagination test :: " + list);
		int count = 0;
		for(UserTable user : list) {
			count++;
		}
		System.out.println("count is:::   "+count);

		return count;

	}
	 

	public List<Users> rowCount() {
		boolean status = false;

		String query1 = "SELECT count(*) as userId, book, bookId from usertable;";
		// RowMapper<LibraryBean> rowMapper = new RowMapperImpl();

		ArrayList<Users> list = (ArrayList<Users>) jdbcTemplate.query(query1, new RowMapperImplUsers());
		System.out.println("Count pagination:: " + list);

		for (Users record : list) {
			System.out.println("liist---> " + record.getUsername());
		}

		return list;
	}

	public List<LibraryBean> searchBook(String bookname) {
		String query = "select * from book where BookName LIKE ?";

		String bookname1 = "%" + bookname;
		String bookname2 = bookname + "%";
		String bookname3 = "%" + bookname + "%";

		List<LibraryBean> list = jdbcTemplate.query(query, new RowMapperImpl(), bookname3);

		return list;
	}
	
	
	public List<LibraryBean> searchAvailableBook(String bookname) {
		String query = "select BookId, BookName, BookStatus,BookNumbers from book where BookStatus = 'A' and BookName LIKE ?";

		String bookname1 = "%" + bookname;
		String bookname2 = bookname + "%";
		String bookname3 = "%" + bookname + "%";

		List<LibraryBean> list = jdbcTemplate.query(query, new RowMapperSearchAvailableBookImpl(), bookname3);

		return list;
	}

	public List<LibraryBean> getExcel() {

		
		/*
		 * List<LibraryBean> list = new ArrayList<LibraryBean>();
		 * 
		 * list.add(new LibraryBean(21,"JAVA", 201, "U")); list.add(new
		 * LibraryBean(22,"MS.NET", 202,"U")); System.out.println("We are in bookdao.");
		 * list.add(new LibraryBean(23,"OS", 203, "U"));
		 */
		 
		String query = "SELECT * FROM book;";
		  
		  List<LibraryBean> list = jdbcTemplate.query(query, new RowMapperImpl());
		  System.out.println("we are in getExcel dao."); // return
		  System.out.println("getexcel:: "+ list); //
		 //		  jdbcTemplate.queryForObject(query,new RowMapperImpl(), LibraryBean.class);
		 
		return list;

	}
	
	
	public List<LibraryBean> getExcelOfAvailableBooks() {

		/*
		 * List<LibraryBean> list = new ArrayList<LibraryBean>();
		 * 
		 * list.add(new LibraryBean(21,"JAVA", 201, "U")); list.add(new
		 * LibraryBean(22,"MS.NET", 202,"U")); System.out.println("We are in bookdao.");
		 * list.add(new LibraryBean(23,"OS", 203, "U"));
		 */

		String query = "select BookId, BookName,BookStatus,BookNumbers from book where BookStatus='A';";

		List<LibraryBean> list = jdbcTemplate.query(query, new RowMapperSearchAvailableBookImpl());
		System.out.println("we are in getExcel dao."); // return
		System.out.println("getexcel of available books:: " + list); //
		// jdbcTemplate.queryForObject(query,new RowMapperImpl(), LibraryBean.class);

		return list;

	}
	
	public List<Users> getUserByUsername(String username) {
		boolean flag=false;
		String query1 = "select * from users where username= ?;";
		List<Users> list =  jdbcTemplate.query(query1, new RowMapperImplUsers(), username);
		int str=list.size();
		System.out.println("size of list is:: "+ str);
		System.out.println("size of list:: "+ list);
		
//		if(str>0) 
//		{
//			flag = true;
//			}
//		else 
//		{
//			flag=false;
//		}
		return list;
	}

	public String signinByRole(String username) {
		String role = null;
		String query1 = "select role from users where username= ?;";
		role =  jdbcTemplate.queryForObject(query1,String.class, new RowMapperImplUsers(), username);
		
		System.out.println("role is "+ role);
		System.out.println("username is :: "+ username);
		
		return role;
	}

	

	/*
	 * public Users login_process(String username) { boolean flag=false; String
	 * query1 = "select * from users where username= ?;"; Users users = (Users)
	 * jdbcTemplate.query(query1, new RowMapperImplUsers(), username); //int
	 * str=list.size(); System.out.println("size of list is:: "+ str);
	 * System.out.println("size of list:: "+ list);
	 * 
	 * return users; }
	 */
	
	public boolean update_newpassword(String mailId, String password) {
		boolean flag = false;
		
		String query = "update users set password = ? where username = ?;";
		
		try {
			int x = jdbcTemplate.update(query, passwordEncoder.encode(password), mailId);
			
			if(x == 1) {
				flag = true;
			} 
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return flag;
	}
	
	public boolean getUserByMailId(String mailId) {
		boolean flag = false;
		int x = 0;
		
		String query = "select id from users where username = ?;";
		System.out.println("Before query x is: ");
		try {
			x = jdbcTemplate.queryForObject(query, Integer.class, mailId);
			System.out.println("value of x in checkSigninMail::  " + x);

			if (x == 1) 
				flag = true;
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("x is: "+ x);
		return flag;
	}

}
