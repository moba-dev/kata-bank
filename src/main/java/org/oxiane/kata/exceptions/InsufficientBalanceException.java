package org.oxiane.kata.exceptions;

public class InsufficientBalanceException extends Exception {

	private static final long serialVersionUID = 2340211557301082698L;

	private String accountId;

	public InsufficientBalanceException(String accountId, String message) {
		super(message);
		this.accountId = accountId;
	}

	public String getAccountId() {
		return accountId;
	}

}
