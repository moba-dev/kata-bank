package org.oxiane.kata;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.oxiane.kata.ds.ArrayListAccountRepositoryImpl;
import org.oxiane.kata.ds.ArrayListStatmentRepositoryImpl;
import org.oxiane.kata.exceptions.AccountAlreadyExistException;
import org.oxiane.kata.exceptions.InsufficientBalanceException;
import org.oxiane.kata.model.Account;
import org.oxiane.kata.repository.AccountRepository;
import org.oxiane.kata.repository.StatmentRepository;
import org.oxiane.kata.service.AccountService;
import org.oxiane.kata.service.AccountServiceImpl;

public class AccountServiceTest {

	static final String DEFAULT_ACCOUNT_ID = "abcd-efgh";
	static final double DEFAULT_BALANCE = 1000d;
	
	private AccountService accountService;


	@BeforeEach
	public void intialize() {
		StatmentRepository statmentRepository = new ArrayListStatmentRepositoryImpl();
		AccountRepository accountRepository = new ArrayListAccountRepositoryImpl();

		this.accountService = new AccountServiceImpl(accountRepository, statmentRepository);
	}

	@Test
	public void should_create_default_account_with_default_balance() throws AccountAlreadyExistException {

		// Given
		Account acc = new Account(DEFAULT_ACCOUNT_ID, DEFAULT_BALANCE);

		// When
		boolean value = this.accountService.create(acc);

		// Then
		assertThat(acc.balance(), equalTo(DEFAULT_BALANCE));
		assertThat(value, is(true));

	}


	@Test
	public void should_throw_AccountAlreadyExistException_when_creating_account_twice() 
			throws InsufficientBalanceException, AccountAlreadyExistException {

		// Given default account 
		Account account = new Account(DEFAULT_ACCOUNT_ID);
		this.accountService.create(account);

		// When creating twice
		// Then
		assertThrows(AccountAlreadyExistException.class, () -> this.accountService.create(account));
	}

}
