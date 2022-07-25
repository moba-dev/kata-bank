package org.oxiane.kata.domain.service;

import java.time.LocalDateTime;
import java.util.List;

import org.oxiane.kata.domain.model.Statment;
import org.oxiane.kata.domain.model.StatmentType;
import org.oxiane.kata.port.spi.StatmentRepositoryPort;

public class StatmentService {

	private StatmentRepositoryPort statmentRepository;


	public StatmentService(StatmentRepositoryPort statmentRepository) {
		this.statmentRepository = statmentRepository;
	}


	// @Override
	public boolean save(String accountId, StatmentType statmentType, double amount, double balance) {
		Statment stmt = new Statment(accountId, statmentType, amount, balance);
		return this.statmentRepository.save(stmt);
	}


	// @Override
	public List<Statment> getStatmentsByAccount(String accountId) {
		return this.statmentRepository.getStatmentsByAccountId(accountId);
	}

	// @Override
	public StringBuilder printStatmentsOf(String accountId, LocalDateTime since) {

		StringBuilder sb = new StringBuilder();

		this.statmentRepository.getStatmentsByAccountId(accountId)
		.stream()
		.filter(stmt -> stmt.getCreationDate().isAfter(since))
		.forEach(stmt -> sb.append(stmt.print()));

		return sb;
	}

}
