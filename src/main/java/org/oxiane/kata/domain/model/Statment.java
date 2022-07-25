package org.oxiane.kata.domain.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Statment {

	private String accountId;

	private StatmentType statmentType;

	private double amount;

	private LocalDateTime creationDate;

	private double balance;

	public Statment(String accountId, StatmentType statmentType, double amount, double balance) {
		this.accountId = accountId;
		this.statmentType = statmentType;
		this.amount = amount;
		this.creationDate = LocalDateTime.now();
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

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public double getBalance() {
		return balance;
	}


	public Statment updateCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
		return this;
	}

	public String print() {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("%-10s", this.getStatmentType().name())).append("\t|\t")
		.append(this.getAmount()) .append("\t|\t")
		.append(this.creationDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))).append("\t|\t")
		.append(this.getBalance()) 
		.append("\n");

		return sb.toString();
	}

	@Override
	public String toString() {
		return "Statment [statmentType=" + statmentType + ", amount=" + amount + ", creationDate=" + creationDate
				+ ", balance=" + balance + "]";
	}


	
}
