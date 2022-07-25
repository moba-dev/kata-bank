package org.oxiane.kata.adapter.api;

import java.util.Optional;

import org.oxiane.kata.domain.model.Account;
import org.oxiane.kata.domain.service.AccountService;
import org.oxiane.kata.exceptions.AccountAlreadyExistException;
import org.oxiane.kata.exceptions.AccountNotFoundException;
import org.oxiane.kata.exceptions.InsufficientBalanceException;
import org.oxiane.kata.port.api.AccountServicePort;

public class AccountServiceAdapter implements AccountServicePort {

	AccountService accountService;

	public AccountServiceAdapter(AccountService accountService) {
		this.accountService = accountService;
	}

	@Override
	public boolean create(Account account) throws AccountAlreadyExistException {
		return this.accountService.create(account);
	}

	@Override
	public Optional<Account> findAccount(String accountId) {
		return this.accountService.findAccount(accountId);
	}

	@Override
	public boolean depositMoney(String accountId, double amount) throws AccountNotFoundException {
		return this.accountService.depositMoney(accountId, amount);
	}

	@Override
	public boolean withdrawMoney(String accountId, double amount)
			throws AccountNotFoundException, InsufficientBalanceException {
		return this.accountService.withdrawMoney(accountId, amount);
	}

}
