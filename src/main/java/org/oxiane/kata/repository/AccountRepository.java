package org.oxiane.kata.repository;

import org.oxiane.kata.model.Account;

public interface AccountRepository {

	boolean save(Account account);

	boolean exists(String accountId);

}
