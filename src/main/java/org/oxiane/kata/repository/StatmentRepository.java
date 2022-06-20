package org.oxiane.kata.repository;

import java.util.List;

import org.oxiane.kata.model.Statment;

public interface StatmentRepository {


	boolean save(Statment statment);

	List<Statment> getStatmentsByAccountId(String accountId);


}
