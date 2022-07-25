package org.oxiane.kata.port.api;

import java.time.LocalDateTime;
import java.util.List;

import org.oxiane.kata.domain.model.Statment;
import org.oxiane.kata.domain.model.StatmentType;

public interface StatmentServicePort {


	boolean save(String accountId, StatmentType statmentType, double amount, double balance);

	List<Statment> getStatmentsByAccount(String accountId);

	StringBuilder printStatmentsOf(String accountId, LocalDateTime since);

}
