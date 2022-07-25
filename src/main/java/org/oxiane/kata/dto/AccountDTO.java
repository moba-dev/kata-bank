package org.oxiane.kata.dto;

public class AccountDTO {

	private String id;

	private double balance;

	public AccountDTO() {
	}

	public AccountDTO(String id, double balance) {
		super();
		this.id = id;
		this.balance = balance;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}


}
