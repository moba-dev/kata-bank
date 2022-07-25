package org.oxiane.kata.dto;

import java.time.LocalDateTime;

public class StatmentDTO {

	private String accountId;

	private String type;

	private double amount;

	private LocalDateTime creationDate;

	private double balance;

	public StatmentDTO(String accountId, String type, double amount, double balance) {
		this.accountId = accountId;
		this.type = type;
		this.amount = amount;
		this.creationDate = LocalDateTime.now();
		this.balance = balance;
	}

	public StatmentDTO(AccountDTO accountDTO, String type, double amount) {
		this(accountDTO.getId(), type, amount, accountDTO.getBalance());
	}



	public String getAccountId() {
		return accountId;
	}

	public String getType() {
		return type;
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


}
