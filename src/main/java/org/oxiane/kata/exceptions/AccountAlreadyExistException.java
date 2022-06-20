package org.oxiane.kata.exceptions;

public class AccountAlreadyExistException extends AccountException {


	private static final long serialVersionUID = -2401925107463887441L;


	public AccountAlreadyExistException(String accountId, String message) {
		super(accountId, message);
	}


}
