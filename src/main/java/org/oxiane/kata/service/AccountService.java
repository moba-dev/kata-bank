package org.oxiane.kata.service;

import org.oxiane.kata.exceptions.AccountAlreadyExistException;
import org.oxiane.kata.model.Account;

public interface AccountService {

	boolean create(Account account) throws AccountAlreadyExistException;

}
