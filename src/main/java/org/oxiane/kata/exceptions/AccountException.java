package org.oxiane.kata.exceptions;

public class AccountException extends Exception {

	private static final long serialVersionUID = -4838096677507173687L;

	private String accountId;

	public AccountException(String accountId, String message) {
		super(message);
		this.accountId = accountId;
	}

	public String getAccountId() {
		return accountId;
	}

}
