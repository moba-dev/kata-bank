package org.oxiane.kata.exceptions;
public class AccountNotFoundException extends AccountException {


	private static final long serialVersionUID = -6005308896727152038L;


	public AccountNotFoundException(String accountId, String message) {
		super(accountId, message);
	}

}