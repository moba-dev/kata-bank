package org.oxiane.kata.service;

import java.time.LocalDate;
import java.util.List;

import org.oxiane.kata.model.Statment;
import org.oxiane.kata.model.StatmentType;
import org.oxiane.kata.repository.StatmentRepository;

public class StatmentServiceImpl implements StatmentService {

	private StatmentRepository statmentRepository;


	public StatmentServiceImpl(StatmentRepository statmentRepository) {
		this.statmentRepository = statmentRepository;
	}


	@Override
	public boolean save(String accountId, StatmentType statmentType, double amount, double balance) {
		Statment stmt = new Statment(accountId, statmentType, amount, balance);
		return this.statmentRepository.save(stmt);
	}


	@Override
	public List<Statment> getStatmentsByAccount(String accountId) {
		return this.statmentRepository.getStatmentsByAccountId(accountId);
	}

	@Override
	public StringBuilder printStatmentsOf(String accountId, LocalDate since) {

		StringBuilder sb = new StringBuilder();

		this.statmentRepository.getStatmentsByAccountId(accountId)
		.stream()
		.filter(stmt -> stmt.getCreationDate().isAfter(since))
		.forEach(stmt -> sb.append(stmt.print()));

		return sb;
	}

}


