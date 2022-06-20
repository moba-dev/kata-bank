package org.oxiane.kata.ds;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.oxiane.kata.model.Account;
import org.oxiane.kata.repository.AccountRepository;

public class ArrayListAccountRepositoryImpl implements AccountRepository {

	private List<Account> accounts = new ArrayList<Account>();


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
