package org.oxiane.kata.exceptions;

public class InsufficientBalanceException extends AccountException {


	private static final long serialVersionUID = -7216907366323879953L;


	public InsufficientBalanceException(String accountId, String message) {
		super(accountId, message);
	}


}
