package de.tekup.rst.controllers;



import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.tekup.rst.entities.Client;
import de.tekup.rst.services.ClientService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ClientCtrl {
	
	private ClientService clientService;
	@PostMapping("/api/clients")
	public Client addClient(@RequestBody Client client) {
		return clientService.saveToDB(client);
	}

}
