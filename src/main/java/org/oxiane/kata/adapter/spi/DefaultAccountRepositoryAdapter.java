package org.oxiane.kata.adapter.spi;

import java.util.Collection;
import java.util.Optional;

import org.oxiane.kata.domain.model.Account;
import org.oxiane.kata.port.spi.AccountRepositoryPort;

public class DefaultAccountRepositoryAdapter implements AccountRepositoryPort {

	private final Collection<Account> accounts;


	DefaultAccountRepositoryAdapter() {
		this.accounts = null;
	}

	public DefaultAccountRepositoryAdapter(Collection<Account> accounts) {
		this.accounts = accounts;
	}

	@Override
	public boolean save(Account account) {
		return this.accounts.add(account);
	}

	@Override
	public boolean exists(String accountId) {
		return this.accounts.stream().anyMatch(acc -> acc.getId().equals(accountId));
	}

	@Override
	public Optional<Account> findById(String accountId) {
		return this.accounts.stream()
				.filter( account -> account.getId().equals(accountId))
				.findFirst();
	}


}
