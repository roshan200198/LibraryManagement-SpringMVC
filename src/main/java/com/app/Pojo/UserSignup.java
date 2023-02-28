package com.app.Pojo;

import org.springframework.stereotype.Component;

@Component
public class UserSignup {

	private String mailId;
	private String contactNo;
	private String password;
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "UserSignup [mailId=" + mailId + ", contactNo=" + contactNo + ", Password=" + password + "]";
	}
	public UserSignup() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
