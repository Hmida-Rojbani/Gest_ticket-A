package de.tekup.rst.services;

import org.springframework.stereotype.Service;

import de.tekup.rst.dto.models.ClientReqDTO;
import de.tekup.rst.entities.Client;
import de.tekup.rst.repositories.ClientRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClientService {
	
	private ClientRepository clientRepository;
	
	public Client saveToDB(ClientReqDTO clientDto) {
		Client client = new Client();
		client.setNom(clientDto.getNom());
		client.setPrenom(clientDto.getPrenom());
		client.setDateDeNaissance(clientDto.getDateDeNaissance());
		client.setCourriel(clientDto.getCourriel());
		client.setTelephone(clientDto.getTelephone());
		return clientRepository.save(client);
	}

}
