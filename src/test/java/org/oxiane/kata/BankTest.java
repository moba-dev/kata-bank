package org.oxiane.kata;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.oxiane.kata.adapter.api.AccountServiceAdapter;
import org.oxiane.kata.adapter.api.StatmentServiceAdapter;
import org.oxiane.kata.adapter.spi.DefaultAccountRepositoryAdapter;
import org.oxiane.kata.adapter.spi.DefaultStatmentRepositoryAdapter;
import org.oxiane.kata.domain.model.Statment;
import org.oxiane.kata.domain.service.AccountService;
import org.oxiane.kata.domain.service.StatmentService;
import org.oxiane.kata.exceptions.AccountAlreadyExistException;
import org.oxiane.kata.exceptions.AccountNotFoundException;
import org.oxiane.kata.exceptions.InsufficientBalanceException;
import org.oxiane.kata.port.spi.AccountRepositoryPort;
import org.oxiane.kata.port.spi.StatmentRepositoryPort;

public class BankTest {
	
	static final String DEFAULT_ACCOUNT_ID = "abcd-efgh";
	static final double DEFAULT_BALANCE = 1000d;

	Bank bank;



	@BeforeEach
	public void initilize() throws AccountAlreadyExistException {
		StatmentRepositoryPort statmentRepository = new DefaultStatmentRepositoryAdapter(new HashSet<Statment>());
		AccountRepositoryPort accountRepository = new DefaultAccountRepositoryAdapter(new ArrayList<>());

		AccountServiceAdapter accountAdpter = new AccountServiceAdapter(
				new AccountService(accountRepository, statmentRepository));
		StatmentServiceAdapter statmentAdapter = new StatmentServiceAdapter(
				new StatmentService(statmentRepository));

		bank = new Bank(accountAdpter, statmentAdapter);
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

	@Test
	public void should_print_statments_of_accounts() throws AccountNotFoundException, InsufficientBalanceException, AccountAlreadyExistException {
		// Given id
		String id = "fr01-0001";
		// When
		bank.createAccount(id, 1000);
		bank.depositMoneyToAccount(id, 1000);
		bank.withdrawMoneyFromAccount(id, 500);
		// Then
		double balance = bank.getBalanceOf(id);
		assertThat(balance, equalTo(1500d));

		LocalDateTime since= LocalDateTime.of(2022, 1, 1, 0, 0, 0);
		bank.printStatmentsOf(id, since);
		bank.printStatmentsOf(DEFAULT_ACCOUNT_ID, since);
	}

}
