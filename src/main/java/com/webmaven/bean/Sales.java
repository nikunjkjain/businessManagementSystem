package com.webmaven.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class Sales implements Serializable{
	
	private String srNo;
	private String rate;
	private String product;
	private String description;
	private String quantity;
	
	public String getSrNo() {
		return srNo;
	}
	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	
	/*private ArrayList<String> customers;

	public ArrayList<String> getCustomers() {
		return customers;
	}

	public void setCustomers(ArrayList<String> customers) {
		this.customers = customers;
	}*/
}
