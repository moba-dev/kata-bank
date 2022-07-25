package org.oxiane.kata.adapter.spi;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.oxiane.kata.domain.model.Statment;
import org.oxiane.kata.port.spi.StatmentRepositoryPort;

public class DefaultStatmentRepositoryAdapter implements StatmentRepositoryPort {

	private final Collection<Statment> statments;


	DefaultStatmentRepositoryAdapter() {
		this.statments = null;
	}

	public DefaultStatmentRepositoryAdapter(Collection<Statment> statments) {
		this.statments = statments;
	}

	@Override
	public boolean save(Statment statment) {
		return this.statments.add(statment);
	}

	@Override
	public List<Statment> getStatmentsByAccountId(String accountId) {
		return this.statments.stream()
				.filter(statment -> statment.getAccountId().equals(accountId))
				.collect(Collectors.toList());
	}

}
