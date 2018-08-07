package com.webmaven.bean;

import java.util.Date;

public class SalesAndPayment{
	
	private int id;
	private int customerId;
	private String salesDate;
	private String reminder;
	private double amount;
	private String comment;
	private String type;
	private String mode;
	private int payment;
	private String updatedBy = "";
	private Date updatedOn = null;
	
	
	public SalesAndPayment() {
		
	}
	
	public SalesAndPayment(int customerId, String date) {
		this.customerId = customerId;
		this.salesDate = date;
	}
	
	public SalesAndPayment(int customerId, String date, String reminder, double amount, String comment, String type, String mode, int payment) {
		this.customerId = customerId;
		this.salesDate = date;
		this.reminder = reminder;
		this.amount = amount;
		this.comment = comment;
		this.type = type;
		this.mode = mode;
		this.payment = payment;
	}

	public SalesAndPayment(int id, int customerId, String date, String reminder, double amount, String comment, String type, String mode, int payment) {
		this.id = id;
		this.customerId = customerId;
		this.salesDate = date;
		this.reminder = reminder;
		this.amount = amount;
		this.comment = comment;
		this.type = type;
		this.mode = mode;
		this.payment = payment;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getSalesDate() {
		return salesDate;
	}

	public void setSalesDate(String salesDate) {
		this.salesDate = salesDate;
	}

	public String getReminder() {
		return reminder;
	}

	public void setReminder(String reminder) {
		this.reminder = reminder;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public int getPayment() {
		return payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
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
		return "SalesAndPayment [id=" + id + ", customerId=" + customerId + ", salesDate=" + salesDate + ", reminder=" + reminder + " , amount=" + amount
				+ ", comment=" + comment + ", type=" + type + ", mode=" + mode + ", payment=" + payment + ", updatedBy="
				+ updatedBy + ", updatedOn=" + updatedOn + "]";
	}
	
}
