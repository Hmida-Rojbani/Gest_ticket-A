package de.tekup.rst.services;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import de.tekup.rst.dto.models.TicketDTO;
import de.tekup.rst.entities.Client;
import de.tekup.rst.entities.MetEntity;
import de.tekup.rst.entities.TableEntity;
import de.tekup.rst.entities.TicketEntity;
import de.tekup.rst.repositories.ClientRepository;
import de.tekup.rst.repositories.MetRepository;
import de.tekup.rst.repositories.TableRepository;
import de.tekup.rst.repositories.TicketRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TicketServiceImpl implements TicketService{
	
	private TicketRepository ticketRepository;
	private ClientRepository clientRepository;
	private TableRepository tableRepository;
	private MetRepository metRepository;

	@Override
	public double create(TicketDTO ticket) {
		
		TicketEntity ticketEntity = new TicketEntity();
		
		Client client = clientRepository.findById(ticket.getClientId())
										.get();
		
		TableEntity tableEntity = tableRepository.findById(ticket.getTableNumero())
										.get();
		List<MetEntity> mets = metRepository.findAllById(Arrays.asList(ticket.getMetIds()));
		
		ticketEntity.setClient(client);
		ticketEntity.setTable(tableEntity);
		ticketEntity.setMets(mets);
		ticketEntity.setNbCoverts(ticket.getNbCouverts());

		double addition = ticketEntity.getMets()
								.stream()
								.mapToDouble(met -> met.getPrix())
								.sum()
								+ticketEntity.getTable().getSupplement();
		ticketEntity.setAddition(addition);
		ticketEntity.setDateTime(LocalDateTime.now());
		ticketRepository.save(ticketEntity);
		 
		 return addition;
	}

}
