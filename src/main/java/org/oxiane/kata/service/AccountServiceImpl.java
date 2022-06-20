package org.oxiane.kata.service;

import java.util.Optional;

import org.oxiane.kata.exceptions.AccountAlreadyExistException;
import org.oxiane.kata.exceptions.AccountNotFoundException;
import org.oxiane.kata.exceptions.InsufficientBalanceException;
import org.oxiane.kata.model.Account;
import org.oxiane.kata.model.Statment;
import org.oxiane.kata.model.StatmentType;
import org.oxiane.kata.repository.AccountRepository;
import org.oxiane.kata.repository.StatmentRepository;

public class AccountServiceImpl implements AccountService {

	private AccountRepository accountRepository;

	private StatmentRepository statmentRepository;



	public AccountServiceImpl(AccountRepository accountRepository, StatmentRepository statmentRepository) {
		this.accountRepository = accountRepository;
		this.statmentRepository = statmentRepository;
	}


	@Override
	public boolean create(Account account) throws AccountAlreadyExistException {
		if (this.accountRepository.exists(account.getId())) {
			throw new AccountAlreadyExistException(account.getId(), "This account already exists!");
		}

		this.accountRepository.save(account);
		Statment stmt = new Statment(account.getId(), StatmentType.DEPOSIT, account.balance(), account.balance());
		return this.statmentRepository.save(stmt);
	}


	@Override
	public Optional<Account> findAccount(String accountId) {
		return this.accountRepository.findById(accountId);
	}


	@Override
	public boolean depositMoney(String accountId, double amount) throws AccountNotFoundException {
		Account account = this.accountRepository.findById(accountId)
				.orElseThrow(() -> new AccountNotFoundException(accountId, "Account"));

		account.deposit(amount);

		Statment stmt = new Statment(account, StatmentType.DEPOSIT, amount);
		return this.statmentRepository.save(stmt);
	}


	@Override
	public boolean withdrawMoney(String accountId, double amount) throws AccountNotFoundException, InsufficientBalanceException {
		Account account = this.accountRepository.findById(accountId)
				.orElseThrow(() -> new AccountNotFoundException(accountId, "Account not exists id=" + accountId));

		account.withdraw(amount);

		Statment stmt = new Statment(account, StatmentType.WITHDRAW, amount);
		return this.statmentRepository.save(stmt);
	}



}
