package org.oxiane.kata.mapper;

import org.oxiane.kata.domain.model.Account;
import org.oxiane.kata.dto.AccountDTO;

public class AccountMapper {

	public static Account toAccount(AccountDTO dto) {
		return new Account(dto.getId(), dto.getBalance());
	}

	public static AccountDTO toAccountDTO(Account account) {
		return new AccountDTO(account.getId(), account.balance());
	}

}
