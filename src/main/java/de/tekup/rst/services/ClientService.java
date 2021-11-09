package de.tekup.rst.services;

import org.springframework.stereotype.Service;

import de.tekup.rst.entities.Client;
import de.tekup.rst.repositories.ClientRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClientService {
	
	private ClientRepository clientRepository;
	
	public Client saveToDB(Client client) {
		return clientRepository.save(client);
	}

}
