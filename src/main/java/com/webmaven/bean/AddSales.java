package com.webmaven.bean;

import java.io.Serializable;

public class AddSales implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private double rate = 0;
	private int product = 0;
	private String description = "";
	private double quantity = 0;
	private int bags = 0;
	private double less = 0;
	private int customerId = 0;
	private String salesDate = "";
	private double totalAmount = 0;
	private String comment = "";
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public int getProduct() {
		return product;
	}
	public void setProduct(int product) {
		this.product = product;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public int getBags() {
		return bags;
	}
	public void setBags(int bags) {
		this.bags = bags;
	}
	public double getLess() {
		return less;
	}
	public void setLess(double less) {
		this.less = less;
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
	
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Override
	public String toString() {
		return "AddSales [id=" + id + ", rate=" + rate + ", product=" + product + ", description=" + description
				+ ", quantity=" + quantity + ", bags=" + bags + ", less=" + less + ", customerId=" + customerId
				+ ", salesDate=" + salesDate + ", totalAmount=" + totalAmount+ ", comment=" + comment + "]";
	}
	
	
	
}
