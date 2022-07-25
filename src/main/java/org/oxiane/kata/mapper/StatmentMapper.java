package org.oxiane.kata.mapper;

import org.oxiane.kata.domain.model.Statment;
import org.oxiane.kata.domain.model.StatmentType;
import org.oxiane.kata.dto.StatmentDTO;

public class StatmentMapper {


	public static Statment toStatment(StatmentDTO dto) {
		return new Statment(dto.getAccountId(), StatmentType.valueOf(dto.getType()), dto.getAmount(), dto.getBalance())
				.updateCreationDate(dto.getCreationDate());
	}

	public static StatmentDTO toStatmentDTO(Statment statment) {
		return new StatmentDTO(
				statment.getAccountId(), statment.getStatmentType().name(), statment.getAmount(), statment.getBalance());
	}

}
