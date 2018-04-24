
package com.webmaven.bean;

public class Balance {

	private int customerId = 0;
	private String name = "";
	private float debit = 0;
	private float credit = 0;
	private float balance = 0;
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getDebit() {
		return debit;
	}
	public void setDebit(float debit) {
		this.debit = debit;
	}
	public float getCredit() {
		return credit;
	}
	public void setCredit(float credit) {
		this.credit = credit;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	
	@Override
	public String toString() {
		return "Balance [customerId=" + customerId + ", name=" + name + ", debit=" + debit + ", credit=" + credit
				+ ", balance=" + balance + "]";
	}
	
	
	
}
