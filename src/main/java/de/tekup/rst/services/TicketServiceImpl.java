package de.tekup.rst.services;

import org.springframework.stereotype.Service;

import de.tekup.rst.entities.TicketEntity;
import de.tekup.rst.repositories.TicketRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TicketServiceImpl implements TicketService{
	
	private TicketRepository ticketRepository;

	@Override
	public TicketEntity create(TicketEntity ticketEntity) {

		double addition = ticketEntity.getMets()
								.stream()
								.mapToDouble(met -> met.getPrix())
								.sum()
								+ticketEntity.getTable().getSupplement();
		return ticketRepository.save(ticketEntity);
	}

}
