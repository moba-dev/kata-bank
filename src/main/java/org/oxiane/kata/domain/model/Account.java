package org.oxiane.kata.domain.model;

import org.oxiane.kata.exceptions.InsufficientBalanceException;

public class Account {

	private String id;

	private double balance;


	public Account(String id) {
		this.id = id;
	}

	public Account(String id, double amount) {
		this(id);
		this.balance = amount;
	}


	public String getId() {
		return id;
	}

	public void deposit(double amount) {
		this.balance = this.balance + amount;
	}

	public void withdraw(double amount) throws InsufficientBalanceException {
		if (this.balance < amount) {
			throw new InsufficientBalanceException(this.id, "Insufficient balance for account id="+id);
		}

		this.balance = this.balance - amount;
	}

	public double balance() {
		return this.balance;
	}


}
