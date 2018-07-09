package com.webmaven.bean;

import java.util.Date;

public class SalesDetails{
	
	private int salesId;
	private int productId;
	private double quantity;
	private double rate;
	private double lessInQuantity;
	private String bags;
	private String description;
	private String updatedBy = "";
	private Date updatedOn = null;
	
	
	public SalesDetails() {
		
	}
	
	public SalesDetails(int salesId, AddSales addSales) {
		this.salesId = salesId;
		this.productId = addSales.getProduct();
		this.quantity = addSales.getQuantity();
		this.rate = addSales.getRate();
		this.lessInQuantity = addSales.getLess();
		this.bags = addSales.getBags();
		this.description = addSales.getDescription();
		this.updatedBy = addSales.getUpdatedBy();
	}
	
	public int getSalesId() {
		return salesId;
	}
	public void setSalesId(int salesId) {
		this.salesId = salesId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public double getLessInQuantity() {
		return lessInQuantity;
	}
	public void setLessInQuantity(double lessInQuantity) {
		this.lessInQuantity = lessInQuantity;
	}
	public String getBags() {
		return bags;
	}
	public void setBags(String bags) {
		this.bags = bags;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
		return "SalesDetails [salesId=" + salesId + ", productId=" + productId + ", quantity=" + quantity + ", rate="
				+ rate + ", lessInQuantity=" + lessInQuantity + ", bags=" + bags + ", description=" + description
				+ ", updatedBy=" + updatedBy + ", updatedOn=" + updatedOn + "]";
	}
	
}
