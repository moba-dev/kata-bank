package org.oxiane.kata;

import java.time.LocalDateTime;

import org.oxiane.kata.domain.model.Account;
import org.oxiane.kata.exceptions.AccountAlreadyExistException;
import org.oxiane.kata.exceptions.AccountNotFoundException;
import org.oxiane.kata.exceptions.InsufficientBalanceException;
import org.oxiane.kata.port.api.AccountServicePort;
import org.oxiane.kata.port.api.StatmentServicePort;

public class Bank {


	private AccountServicePort accountAdapter;

	private StatmentServicePort statmentAdapter;



	public Bank(AccountServicePort accountAdapter, StatmentServicePort statmentAdapter) {
		this.accountAdapter = accountAdapter;
		this.statmentAdapter = statmentAdapter;
	}


	public boolean createAccount(String accountId, double balance) throws AccountAlreadyExistException {
		Account account = new Account(accountId, balance);
		return accountAdapter.create(account);
	}

	public double getBalanceOf(String accountId) throws AccountNotFoundException {
		return this.accountAdapter.findAccount(accountId)
				.orElseThrow(() -> new AccountNotFoundException(accountId, "Account not exists id=" + accountId))
				.balance();
	}

	public boolean depositMoneyToAccount(String accountId, double amount) throws AccountNotFoundException {
		return this.accountAdapter.depositMoney(accountId, amount);
	}

	public boolean withdrawMoneyFromAccount(String accountId, double amount) 
			throws AccountNotFoundException, InsufficientBalanceException {
		return this.accountAdapter.withdrawMoney(accountId, amount);
	}

	public void printStatmentsOf(String accountId, LocalDateTime since) {
		StringBuilder sb = this.statmentAdapter.printStatmentsOf(accountId, since);
		System.out.println(sb.toString());
	}

}
