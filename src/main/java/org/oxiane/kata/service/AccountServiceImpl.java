package org.oxiane.kata.service;

import org.oxiane.kata.exceptions.AccountAlreadyExistException;
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



}
