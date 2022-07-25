package org.oxiane.kata.port.spi;

import java.util.List;

import org.oxiane.kata.domain.model.Statment;

public interface StatmentRepositoryPort {


	boolean save(Statment statment);

	List<Statment> getStatmentsByAccountId(String accountId);


}
