package org.oxiane.kata.port.api;

import java.util.Optional;

import org.oxiane.kata.domain.model.Account;
import org.oxiane.kata.exceptions.AccountAlreadyExistException;
import org.oxiane.kata.exceptions.AccountNotFoundException;
import org.oxiane.kata.exceptions.InsufficientBalanceException;

public interface AccountServicePort {

	boolean create(Account account) throws AccountAlreadyExistException;

	Optional<Account> findAccount(String accountId);

	boolean depositMoney(String accountId, double amount) throws AccountNotFoundException;

	boolean withdrawMoney(String accountId, double amount) throws AccountNotFoundException, InsufficientBalanceException;

}
