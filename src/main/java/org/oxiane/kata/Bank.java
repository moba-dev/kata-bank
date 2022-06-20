package org.oxiane.kata;

import java.time.LocalDate;

import org.oxiane.kata.exceptions.AccountAlreadyExistException;
import org.oxiane.kata.exceptions.AccountNotFoundException;
import org.oxiane.kata.exceptions.InsufficientBalanceException;
import org.oxiane.kata.model.Account;
import org.oxiane.kata.service.AccountService;
import org.oxiane.kata.service.StatmentService;

public class Bank {


	private AccountService accountService;

	private StatmentService statmentService;



	public Bank(AccountService accountService, StatmentService statmentService) {
		this.accountService = accountService;
		this.statmentService = statmentService;
	}


	public boolean createAccount(String accountId, double balance) throws AccountAlreadyExistException {
		Account account = new Account(accountId, balance);
		return accountService.create(account);
	}

	public double getBalanceOf(String accountId) throws AccountNotFoundException {
		return this.accountService.findAccount(accountId)
				.orElseThrow(() -> new AccountNotFoundException(accountId, "Account not exists id=" + accountId))
				.balance();
	}

	public boolean depositMoneyToAccount(String accountId, double amount) throws AccountNotFoundException {
		return this.accountService.depositMoney(accountId, amount);
	}

	public boolean withdrawMoneyFromAccount(String accountId, double amount) 
			throws AccountNotFoundException, InsufficientBalanceException {
		return this.accountService.withdrawMoney(accountId, amount);
	}

	public void printStatmentsOf(String accountId, LocalDate since) {
		StringBuilder sb = this.statmentService.printStatmentsOf(accountId, since);
		System.out.println(sb.toString());
	}

}
