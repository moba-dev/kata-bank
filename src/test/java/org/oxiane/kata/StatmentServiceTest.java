package org.oxiane.kata;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.oxiane.kata.ds.ArrayListStatmentRepositoryImpl;
import org.oxiane.kata.model.Statment;
import org.oxiane.kata.model.StatmentType;
import org.oxiane.kata.service.StatmentService;
import org.oxiane.kata.service.StatmentServiceImpl;

public class StatmentServiceTest {

	static final String DEFAULT_ACCOUNT_ID = "abcd-efgh";
	static final double DEFAULT_BALANCE = 1000d;

	private StatmentService statmentService;


	@BeforeEach
	public void initialize() {
		this.statmentService = new StatmentServiceImpl(new ArrayListStatmentRepositoryImpl());
	}



	@Test
	public void should_save_default_statment() {
		boolean value = this.statmentService.save(DEFAULT_ACCOUNT_ID, StatmentType.DEPOSIT, DEFAULT_BALANCE, DEFAULT_BALANCE);
		assertThat(value,  is(true));
	}


	@Test
	public void should_create_4_statments_and_test_their_type_amount_balance_and_finally_print_them() {
		// Given 
		String accId = "accc-fr76";

		// When
		this.statmentService.save(accId, StatmentType.DEPOSIT, DEFAULT_BALANCE, DEFAULT_BALANCE);
		this.statmentService.save(accId, StatmentType.DEPOSIT, 500, DEFAULT_BALANCE + 500);
		this.statmentService.save(accId, StatmentType.WITHDRAW, 300, DEFAULT_BALANCE + 500 - 300);
		this.statmentService.save(accId, StatmentType.DEPOSIT, 50, DEFAULT_BALANCE + 500 - 300 + 50);

		// Then
		StringBuilder sb = null;
		List<Statment> statments = this.statmentService.getStatmentsByAccount(accId);
		assertThat(statments.size(), is(4));

		Collections.sort(statments, (stmt1, stmt2) -> stmt1.getCreationDate().compareTo(stmt2.getCreationDate()));;
		assertThat(statments.get(0).getStatmentType(), equalTo(StatmentType.DEPOSIT));
		assertThat(statments.get(0).getAmount(), equalTo(DEFAULT_BALANCE));
		assertThat(statments.get(0).getBalance(), equalTo(DEFAULT_BALANCE));
		
		assertThat(statments.get(1).getStatmentType(), equalTo(StatmentType.DEPOSIT));
		assertThat(statments.get(1).getAmount(), equalTo(500d));
		assertThat(statments.get(1).getBalance(), equalTo(DEFAULT_BALANCE + 500));

		assertThat(statments.get(2).getStatmentType(), equalTo(StatmentType.WITHDRAW));
		assertThat(statments.get(2).getAmount(), equalTo(300d));
		assertThat(statments.get(2).getBalance(), equalTo(DEFAULT_BALANCE + 500 - 300));

		assertThat(statments.get(3).getStatmentType(), equalTo(StatmentType.DEPOSIT));
		assertThat(statments.get(3).getAmount(), equalTo(50d));
		assertThat(statments.get(3).getBalance(), equalTo(DEFAULT_BALANCE + 500 - 300 + 50));

		LocalDate since= LocalDate.of(2022, 1, 1);
		sb = this.statmentService.printStatmentsOf(accId, since);

		System.out.println(sb.toString());
	}

}


