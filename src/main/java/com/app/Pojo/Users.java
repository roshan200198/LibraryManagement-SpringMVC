package com.app.Pojo;

import java.sql.Blob;
import java.util.Arrays;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class Users {

	private String name;
	private String username;
	private int userId;
	private String mobNumber;
	private String password;
	private String role;
	//private byte[] photo;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getMobNumber() {
		return mobNumber;
	}
	public void setMobNumber(String mobNumber) {
		this.mobNumber = mobNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "Users [name=" + name + ", username=" + username + ", userId=" + userId + ", mobNumber=" + mobNumber
				+ ", password=" + password + ", role=" + role + "]";
	}
	public Users(String name, String username, int userId, String mobNumber, String password, String role) {
		super();
		this.name = name;
		this.username = username;
		this.userId = userId;
		this.mobNumber = mobNumber;
		this.password = password;
		this.role = role;
	}
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
