package org.oxiane.kata.service;

import java.util.Optional;

import org.oxiane.kata.exceptions.AccountAlreadyExistException;
import org.oxiane.kata.exceptions.AccountNotFoundException;
import org.oxiane.kata.exceptions.InsufficientBalanceException;
import org.oxiane.kata.model.Account;

public interface AccountService {

	boolean create(Account account) throws AccountAlreadyExistException;

	Optional<Account> findAccount(String accountId);

	boolean depositMoney(String accountId, double amount) throws AccountNotFoundException;

	boolean withdrawMoney(String accountId, double amount) throws AccountNotFoundException, InsufficientBalanceException;

}
