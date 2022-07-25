package org.oxiane.kata.adapter.spi;

import java.util.List;

import org.oxiane.kata.domain.model.Statment;
import org.oxiane.kata.port.spi.StatmentRepositoryPort;

public class StatmentRepositoryAdapter extends DefaultStatmentRepositoryAdapter {

	StatmentRepositoryPort statmentRepository;

	
	public StatmentRepositoryAdapter() {
		super();
	}

	public StatmentRepositoryAdapter(StatmentRepositoryPort statmentRepository) {
		this.statmentRepository = statmentRepository;
	}

	@Override
	public boolean save(Statment statment) {
		return this.statmentRepository.save(statment);
	}

	@Override
	public List<Statment> getStatmentsByAccountId(String accountId) {
		return this.statmentRepository.getStatmentsByAccountId(accountId);
	}

}
