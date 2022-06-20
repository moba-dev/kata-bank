package org.oxiane.kata.ds;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.oxiane.kata.model.Statment;
import org.oxiane.kata.repository.StatmentRepository;

public class ArrayListStatmentRepositoryImpl implements StatmentRepository {

	private final List<Statment> statments = new ArrayList<Statment>();


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
