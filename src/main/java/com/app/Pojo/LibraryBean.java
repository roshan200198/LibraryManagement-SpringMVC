package com.app.Pojo;

import java.io.Reader;

import org.springframework.stereotype.Controller;

@Controller
public class LibraryBean {

	private int userId;
	private String userName;
	private String bookId;
	private String bookName;
	private String bookStatus;
	private int bookNumbers;
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
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
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
	public int getBookNumbers() {
		return bookNumbers;
	}
	public void setBookNumbers(int bookNumbers) {
		this.bookNumbers = bookNumbers;
	}
	@Override
	public String toString() {
		return "LibraryBean [userId=" + userId + ", userName=" + userName + ", bookId=" + bookId + ", bookName="
				+ bookName + ", bookStatus=" + bookStatus + ", bookNumbers=" + bookNumbers + "]";
	}
	public LibraryBean(int userId, String userName, String bookId, String bookName, String bookStatus,
			int bookNumbers) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookStatus = bookStatus;
		this.bookNumbers = bookNumbers;
	}
	public LibraryBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	

}
