package com.webmaven.bean;

public class Sales{
	
	private int id;
	private int customerId;
	private String salesDate;
	private double amount;
	private String comment;
	
	public Sales(int customerId, String salesDate) {
		this.customerId = customerId;
		this.salesDate = salesDate;
	}
	
	public Sales(int customerId, String salesDate, double amount, String comment) {
		this.customerId = customerId;
		this.salesDate = salesDate;
		this.amount = amount;
		this.comment = comment;
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

	@Override
	public String toString() {
		return "Sales [id=" + id + ", customerId=" + customerId + ", salesDate=" + salesDate + ", amount=" + amount + ", comment=" + comment
				+ "]";
	}

}
