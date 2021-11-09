package de.tekup.rst.services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import de.tekup.rst.dto.models.ClientReqDTO;
import de.tekup.rst.dto.models.ClientResDTO;
import de.tekup.rst.entities.Client;
import de.tekup.rst.repositories.ClientRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClientService {
	
	private ClientRepository clientRepository;
	
	public ClientResDTO saveToDB(ClientReqDTO clientDto) {
		ModelMapper mapper = new ModelMapper();
		Client client = mapper.map(clientDto, Client.class);
//		Client client = new Client();
//		client.setNom(clientDto.getNom());
//		client.setPrenom(clientDto.getPrenom());
//		client.setDateDeNaissance(clientDto.getDateDeNaissance());
//		client.setCourriel(clientDto.getCourriel());
//		client.setTelephone(clientDto.getTelephone());
		
		clientRepository.save(client);
		ClientResDTO clientResDTO = mapper.map(client, ClientResDTO.class);
//		ClientResDTO clientResDTO = new ClientResDTO();
//		clientResDTO.setId(client.getId());
//		clientResDTO.setNomComplet(client.getPrenom()+" "+client.getNom());
//		clientResDTO.setAge((int)ChronoUnit.YEARS.between(client.getDateDeNaissance(),
//				LocalDate.now()));
		return clientResDTO;
	}

}
