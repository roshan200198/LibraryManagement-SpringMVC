package com.app.Pojo;

import org.springframework.stereotype.Component;

@Component("admin")
public class Admin {

	private String name;
	private String mailId;
	private String contact;
	private String password;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	@Override
	public String toString() {
		return "Admin [name=" + name + ", mailId=" + mailId + ", contact=" + contact + ", password=" + password + "]";
	}
	
}
