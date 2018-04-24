package com.webmaven.bean;

import java.util.Date;

public class User {

	private int id;
	private String username="";
	private String password="";
	private String name="";
	private long mobileNo=0;
	private String email="";
	private String address="";
	private int status;
	private String updatedBy="";
	private Date updatedOn;
	private long alternateNo=0;
	private String comments="";
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	public long getAlternateNo() {
		return alternateNo;
	}
	public void setAlternateNo(long alternateNo) {
		this.alternateNo = alternateNo;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name + ", mobileNo="
				+ mobileNo + ", email=" + email + ", address=" + address + ", status=" + status + ", updatedBy="
				+ updatedBy + ", updatedOn=" + updatedOn + ", alternateNo=" + alternateNo + ", comments=" + comments
				+ "]";
	}

}
