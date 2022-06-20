package org.oxiane.kata.model;

import java.time.LocalDate;

public class Statment {

	private String accountId;

	private StatmentType statmentType;

	private double amount;

	private LocalDate creationDate;

	private double balance;

	public Statment(String accountId, StatmentType statmentType, double amount, double balance) {
		this.accountId = accountId;
		this.statmentType = statmentType;
		this.amount = amount;
		this.creationDate = LocalDate.now();
		this.balance = balance;
	}

	public Statment(Account account, StatmentType statmentType, double amount) {
		this(account.getId(), statmentType, amount, account.balance());
	}



	public String getAccountId() {
		return accountId;
	}

	public StatmentType getStatmentType() {
		return statmentType;
	}

	public double getAmount() {
		return amount;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public double getBalance() {
		return balance;
	}

}
