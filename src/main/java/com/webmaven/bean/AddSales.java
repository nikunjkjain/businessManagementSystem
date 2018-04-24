package com.webmaven.bean;

import java.io.Serializable;
import java.util.Date;

public class AddSales implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id = 0;
	private double rate = 0;
	private int product = 0;
	private String description = "";
	private double quantity = 0;
	private int bags = 0;
	private double less = 0;
	private int customerId = 0;
	private String date = "";
	private double totalAmount = 0;
	private String comment = "";
	private String type;
	private String mode;
	private int payment; 
	private String updatedBy = "";
	private Date updatedOn = null;
	
	
	
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
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
		return "AddSales [id=" + id + ", rate=" + rate + ", product=" + product + ", description=" + description
				+ ", quantity=" + quantity + ", bags=" + bags + ", less=" + less + ", customerId=" + customerId
				+ ", date=" + date + ", totalAmount=" + totalAmount + ", comment=" + comment + ", type=" + type
				+ ", mode=" + mode + ", payment=" + payment + ", updatedBy=" + updatedBy + ", updatedOn=" + updatedOn
				+ "]";
	}	
	
}
