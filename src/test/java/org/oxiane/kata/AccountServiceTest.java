package org.oxiane.kata;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.oxiane.kata.adapter.spi.DefaultAccountRepositoryAdapter;
import org.oxiane.kata.adapter.spi.DefaultStatmentRepositoryAdapter;
import org.oxiane.kata.domain.model.Account;
import org.oxiane.kata.domain.model.Statment;
import org.oxiane.kata.domain.model.StatmentType;
import org.oxiane.kata.domain.service.AccountService;
import org.oxiane.kata.domain.service.StatmentService;
import org.oxiane.kata.exceptions.AccountAlreadyExistException;
import org.oxiane.kata.exceptions.AccountNotFoundException;
import org.oxiane.kata.exceptions.InsufficientBalanceException;
import org.oxiane.kata.port.spi.AccountRepositoryPort;
import org.oxiane.kata.port.spi.StatmentRepositoryPort;

public class AccountServiceTest {

	static final String DEFAULT_ACCOUNT_ID = "abcd-efgh";
	static final double DEFAULT_BALANCE = 1000d;
	
	private AccountService accountService;
	private StatmentService statmentService;


	@BeforeEach
	public void intialize() {
		AccountRepositoryPort accountRepository = new DefaultAccountRepositoryAdapter(new ArrayList<>());
		StatmentRepositoryPort statmentRepository = new DefaultStatmentRepositoryAdapter(new ArrayList<>());

		this.accountService = new AccountService(accountRepository, statmentRepository);
		this.statmentService = new StatmentService(statmentRepository);
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
	public void should_search_and_find_all_created_account_and_their_balance() throws AccountAlreadyExistException {

		// Given

		// When
		Account acc = new Account(DEFAULT_ACCOUNT_ID, DEFAULT_BALANCE);
		this.accountService.create(acc);

		for (int i = 0; i< 10; i++) {
			this.accountService.create(new Account("acc"+i, i));
		}

		// Then 

		Optional<Account> opt = this.accountService.findAccount(DEFAULT_ACCOUNT_ID);
		assertThat(opt.get(), equalTo(acc));

		for (int i = 0; i< 10; i++) {
			opt = this.accountService.findAccount("acc"+i);
			assertThat(opt.isPresent(), is(true));
			assertThat(opt.get().balance(), is(Double.valueOf(i)));
		}

	}

	@Test
	public void should_return_1000_as_balance_after_creation_with_intitial_deposit_0_and_deposition_of_1000() 
			throws AccountNotFoundException, AccountAlreadyExistException {

		// Given default account with initial deposit of 0
		Account acc = new Account(DEFAULT_ACCOUNT_ID);
		this.accountService.create(acc);

		// When deposit of DEFAULT_BALANCE
		this.accountService.depositMoney(DEFAULT_ACCOUNT_ID, DEFAULT_BALANCE);

		// Then 
		assertThat(acc.balance(), equalTo(DEFAULT_BALANCE));
	}

	@Test
	public void should_return_2000_as_balance_after_creation_with_intitial_deposit_1000_and_deposition_of_1000() 
			throws AccountNotFoundException, AccountAlreadyExistException {

		// Given default account with initial deposit of DEFAULT_BALANCE
		Account acc = new Account(DEFAULT_ACCOUNT_ID, DEFAULT_BALANCE);
		this.accountService.create(acc);

		// When deposit of DEFAULT_BALANCE
		this.accountService.depositMoney(DEFAULT_ACCOUNT_ID, DEFAULT_BALANCE);

		// Then
		assertThat(acc.balance(), equalTo(2000d));
	}

	@Test
	public void should_return_500_as_balance_after_creation_with_intitial_deposit_1000_and_withdrawOf_of_500() 
			throws AccountNotFoundException, InsufficientBalanceException, AccountAlreadyExistException {

		// Given default account with initial deposit of DEFAULT_BALANCE
		Account acc = new Account(DEFAULT_ACCOUNT_ID, DEFAULT_BALANCE);
		this.accountService.create(acc);

		// When withdrawOf 500
		this.accountService.withdrawMoney(DEFAULT_ACCOUNT_ID, 500);

		// Then
		assertThat(acc.balance(), equalTo(500d));

	}

	@Test
	public void should_throw_InsufficientBalanceException_after_creation_with_intitial_deposit_0_and_withdrawOf_of_500() 
			throws AccountNotFoundException, InsufficientBalanceException, AccountAlreadyExistException {

		// Given default account with initial deposit of 0
		Account account = new Account(DEFAULT_ACCOUNT_ID);
		this.accountService.create(account);

		// When withdrawing amount greater than balance
		// Then
		assertThrows(InsufficientBalanceException.class, () -> this.accountService.withdrawMoney(DEFAULT_ACCOUNT_ID, 500));
	}


	@Test
	public void should_not_find_account_when_id_is_xxxx_xxxx() throws AccountNotFoundException {
		//Given the xxxx-xxxx id
		String id ="xxxx-xxxx";

		// When call 
		Optional<Account> optAcc = this.accountService.findAccount(id);

		// Then
		assertThat(optAcc.isPresent(), is(false));
	}

	@ParameterizedTest
	@ValueSource(ints = {1,2,3,4,5,6,7,8,9,10})
	public void should_create_10_accounts_with_4_statments_in_each_and_test_their_type_and_print_them(int index) 
			throws AccountAlreadyExistException, AccountNotFoundException, InsufficientBalanceException {
		// Given index

		// When
		String id = "acc"+index;
		Account acc = new Account(id, index);
		this.accountService.create(acc);
		this.accountService.depositMoney(id, 10*index);
		this.accountService.depositMoney(id, 100*index);
		this.accountService.withdrawMoney(id, index);

		// Then
		StringBuilder sb = null;

		List<Statment> statments = this.statmentService.getStatmentsByAccount(id);
		assertThat(statments.size(), is(4));

		Collections.sort(statments, (stmt1, stmt2) -> stmt1.getCreationDate().compareTo(stmt2.getCreationDate()));
		assertAll("statmentstype",
			() -> assertThat(statments.get(0).getStatmentType(), equalTo(StatmentType.DEPOSIT)),
			() -> assertThat(statments.get(1).getStatmentType(), equalTo(StatmentType.DEPOSIT)),
			() -> assertThat(statments.get(2).getStatmentType(), equalTo(StatmentType.DEPOSIT)),
			() -> assertThat(statments.get(3).getStatmentType(), equalTo(StatmentType.WITHDRAW))
		);
		LocalDateTime since= LocalDateTime.of(2022, 1, 1, 0, 0);
		sb = this.statmentService.printStatmentsOf(id, since);
		System.out.println(sb.toString());

	}


}
