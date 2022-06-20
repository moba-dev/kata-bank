package org.oxiane.kata;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.oxiane.kata.ds.ArrayListAccountRepositoryImpl;
import org.oxiane.kata.ds.ArrayListStatmentRepositoryImpl;
import org.oxiane.kata.exceptions.AccountAlreadyExistException;
import org.oxiane.kata.exceptions.AccountNotFoundException;
import org.oxiane.kata.exceptions.InsufficientBalanceException;
import org.oxiane.kata.repository.AccountRepository;
import org.oxiane.kata.repository.StatmentRepository;
import org.oxiane.kata.service.AccountService;
import org.oxiane.kata.service.AccountServiceImpl;

public class BankTest {
	
	static final String DEFAULT_ACCOUNT_ID = "abcd-efgh";
	static final double DEFAULT_BALANCE = 1000d;

	Bank bank;



	@BeforeEach
	public void initilize() throws AccountAlreadyExistException {
		StatmentRepository statmentRepository = new ArrayListStatmentRepositoryImpl();
		AccountRepository accountRepository = new ArrayListAccountRepositoryImpl();

		AccountService accountService = new AccountServiceImpl(accountRepository, statmentRepository);

		bank = new Bank(accountService);
		bank.createAccount(DEFAULT_ACCOUNT_ID, DEFAULT_BALANCE);
	}

	@Test
	public void should_create_default_account_in_initialize() throws AccountNotFoundException {
		// Given default account
		// When 
		// Then
		double balance = bank.getBalanceOf(DEFAULT_ACCOUNT_ID);
		assertThat(balance, equalTo(DEFAULT_BALANCE));
	}

	@Test
	public void should_throw_InsufficientBalanceException_when_withdraw_amount_is_greater_than_balance() throws AccountNotFoundException {
		// Given default account
		// When call withdraw
		// Then
		assertThrows(InsufficientBalanceException.class, () -> bank.withdrawMoneyFromAccount(DEFAULT_ACCOUNT_ID, 1001));
	}

	@Test
	public void should_throw_AccountNotFoundException_when_calling_balance_Of_undefined_account() throws AccountNotFoundException {
		//Given account id
		String id ="xxxx-xxxx";
		// When call balanceOf
		// Then
		assertThrows(AccountNotFoundException.class, () -> bank.getBalanceOf(id));
	}

	@Test
	public void should_throw_AccountAlreadyExistException_when_creating_account_twice() throws AccountAlreadyExistException {
		//Given id
		String id ="fr01-0001";
		// When account is created
		bank.createAccount(id, DEFAULT_BALANCE);
		// Then
		assertThrows(AccountAlreadyExistException.class, () -> bank.createAccount(id, 5000));
	}

}
