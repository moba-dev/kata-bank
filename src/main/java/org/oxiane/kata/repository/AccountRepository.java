package org.oxiane.kata.repository;

import java.util.Optional;

import org.oxiane.kata.model.Account;

public interface AccountRepository {

	boolean save(Account account);

	Optional<Account> findById(String accountId);

	boolean exists(String accountId);

}
