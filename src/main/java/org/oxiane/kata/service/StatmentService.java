package org.oxiane.kata.service;

import java.time.LocalDate;
import java.util.List;

import org.oxiane.kata.model.Statment;
import org.oxiane.kata.model.StatmentType;

public interface StatmentService {


	boolean save(String accountId, StatmentType statmentType, double amount, double balance);

	List<Statment> getStatmentsByAccount(String accountId);

	StringBuilder printStatmentsOf(String accountId, LocalDate since);

}
