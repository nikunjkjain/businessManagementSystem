package com.webmaven.bean;

import java.util.Date;

public class Customer {

	private int id;
	private String name;
	private long mobileNo;
	private long altContactNo;
	private String email;
	private String companyName;
	private String billingAddress;
	private int status;
	private String updatedBy = "";
	private Date updatedOn = null;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public long getAltContactNo() {
		return altContactNo;
	}
	public void setAltContactNo(long altContactNo) {
		this.altContactNo = altContactNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getBillingAddress() {
		return billingAddress;
	}
	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
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
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", mobileNo=" + mobileNo + ", altContactNo=" + altContactNo
				+ ", email=" + email + ", companyName=" + companyName + ", billingAddress=" + billingAddress
				+ ", status=" + status + ", updatedBy=" + updatedBy + ", updatedOn=" + updatedOn + "]";
	}

}
