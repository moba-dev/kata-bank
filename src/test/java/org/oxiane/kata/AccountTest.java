package org.oxiane.kata;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.oxiane.kata.exceptions.InsufficientBalanceException;
import org.oxiane.kata.model.Account;


public class AccountTest {

	final static String DEFAULT_ACCOUNT_ID = "abcd-efgh";
	private Account account;


	@BeforeEach
	public void initilize() {
		 account = new Account(DEFAULT_ACCOUNT_ID);
	}

	@Test
	public void desposit_1000_should_return_1000() {
		double amount = 1000.0;
		account.deposit(amount);
		assertThat(account.balance(), equalTo(amount));
	}

	@Test
	public void deposit_1000_and_withdraw_500_should_return_500() throws InsufficientBalanceException {
		account.deposit(1000.0);
		account.withdraw(500.0);
		assertThat(account.balance(), equalTo(500.0));
	}

	@Test
	public void deposit_1000_and_withdraw_1100_should_throw_error() throws InsufficientBalanceException {
		account.deposit(1000.0);
		assertThrows(InsufficientBalanceException.class, () -> account.withdraw(1100.0));
	}

}
