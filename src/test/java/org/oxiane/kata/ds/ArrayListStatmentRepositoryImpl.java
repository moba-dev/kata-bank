package org.oxiane.kata.ds;

import java.util.ArrayList;
import java.util.List;

import org.oxiane.kata.model.Statment;
import org.oxiane.kata.repository.StatmentRepository;

public class ArrayListStatmentRepositoryImpl implements StatmentRepository {

	private final List<Statment> statments = new ArrayList<Statment>();


	@Override
	public boolean save(Statment statment) {
		return this.statments.add(statment);
	}

}
