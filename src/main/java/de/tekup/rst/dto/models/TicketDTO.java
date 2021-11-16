package de.tekup.rst.dto.models;

import lombok.Data;

@Data
public class TicketDTO {

	private int clientId;
	private int tableNumero;
	private int nbCouverts;
	private Integer[] metIds;
}
