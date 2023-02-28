package com.app.Pojo;

import org.springframework.stereotype.Component;

@Component
public class UserTable {

	private int id;
	private int userId;
	private String userName;
	private String book;
	private String bookId;
	private int bookNumbers;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
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
	public String getBook() {
		return book;
	}
	public void setBook(String book) {
		this.book = book;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public int getBookNumbers() {
		return bookNumbers;
	}
	public void setBookNumbers(int bookNumbers) {
		this.bookNumbers = bookNumbers;
	}
	@Override
	public String toString() {
		return "UserTable [id=" + id + ", userId=" + userId + ", userName=" + userName + ", book=" + book + ", bookId="
				+ bookId + ", bookNumbers=" + bookNumbers + "]";
	}
	public UserTable(int id, int userId, String userName, String book, String bookId, int bookNumbers) {
		super();
		this.id = id;
		this.userId = userId;
		this.userName = userName;
		this.book = book;
		this.bookId = bookId;
		this.bookNumbers = bookNumbers;
	}
	public UserTable() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
