package de.tekup.rst.services;


import java.util.List;
import java.util.stream.Collectors;

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
	private ModelMapper mapper;
	
	public ClientResDTO saveToDB(ClientReqDTO clientDto) {
		 
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

	public List<ClientResDTO> getAllClients() {
		
		return clientRepository.findAll().stream()
					.map(ce-> mapper.map(ce, ClientResDTO.class))
					.collect(Collectors.toList());
	}

}
