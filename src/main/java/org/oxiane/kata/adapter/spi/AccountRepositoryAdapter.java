package org.oxiane.kata.adapter.spi;

import java.util.Optional;

import org.oxiane.kata.domain.model.Account;
import org.oxiane.kata.port.spi.AccountRepositoryPort;

public class AccountRepositoryAdapter extends DefaultAccountRepositoryAdapter {

	private AccountRepositoryPort accountRepository;



	public AccountRepositoryAdapter(AccountRepositoryPort accountRepositoryPort) {
		super();
		this.accountRepository = accountRepositoryPort;
	}

	@Override
	public boolean save(Account account) {
		return this.accountRepository.save(account);
	}

	@Override
	public boolean exists(String accountId) {
		return this.accountRepository.exists(accountId);
	}

	@Override
	public Optional<Account> findById(String accountId) {
		return this.accountRepository.findById(accountId);
	}



}
