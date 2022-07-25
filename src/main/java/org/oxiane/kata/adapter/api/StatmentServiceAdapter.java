package org.oxiane.kata.adapter.api;

import java.time.LocalDateTime;
import java.util.List;

import org.oxiane.kata.domain.model.Statment;
import org.oxiane.kata.domain.model.StatmentType;
import org.oxiane.kata.domain.service.StatmentService;
import org.oxiane.kata.port.api.StatmentServicePort;

public class StatmentServiceAdapter implements StatmentServicePort {

	
	StatmentService statmentService;
	
	public StatmentServiceAdapter(StatmentService statmentService) {
		this.statmentService = statmentService;
	}
	
	@Override
	public boolean save(String accountId, StatmentType statmentType, double amount, double balance) {
		return this.statmentService.save(accountId, statmentType, amount, balance);
	}

	@Override
	public List<Statment> getStatmentsByAccount(String accountId) {
		return this.statmentService.getStatmentsByAccount(accountId);
	}

	@Override
	public StringBuilder printStatmentsOf(String accountId, LocalDateTime since) {
		return this.statmentService.printStatmentsOf(accountId, since);
	}

}
