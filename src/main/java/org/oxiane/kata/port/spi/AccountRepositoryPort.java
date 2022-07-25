package org.oxiane.kata.port.spi;

import java.util.Optional;

import org.oxiane.kata.domain.model.Account;

public interface AccountRepositoryPort {

	boolean save(Account account);

	Optional<Account> findById(String accountId);

	boolean exists(String accountId);

}
