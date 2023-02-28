package com.app.Pojo;

import org.springframework.stereotype.Component;

@Component
public class ImportBook {
	
	private int userId;
	private String userName;
	private int bookId;
	private String bookName;
	private String bookStatus;
	
	public double getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public double getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookStatus() {
		return bookStatus;
	}
	public void setBookStatus(String bookStatus) {
		this.bookStatus = bookStatus;
	}
	public ImportBook(int userId, String userName, int bookId, String bookName, String bookStatus) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookStatus = bookStatus;
	}
	public ImportBook() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
