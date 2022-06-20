package org.oxiane.kata.exceptions;

public class InsufficientBalanceException extends AccountException {


	private static final long serialVersionUID = 5551910077943101042L;

	private String accountId;

	public InsufficientBalanceException(String accountId, String message) {
		super(accountId, message);
		this.accountId = accountId;
	}

	public String getAccountId() {
		return accountId;
	}

}
