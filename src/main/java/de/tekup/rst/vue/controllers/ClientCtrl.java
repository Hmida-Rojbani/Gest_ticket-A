package de.tekup.rst.vue.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import de.tekup.rst.dto.models.ClientReqDTO;
import de.tekup.rst.services.ClientService;
import lombok.AllArgsConstructor;

@Controller(value = "clientCtrlVue")
@AllArgsConstructor
public class ClientCtrl {
	
	private ClientService clientService;

	@GetMapping("/clients/add")
	public String getClientAdd(Model model) {
		model.addAttribute("client", new ClientReqDTO());
		return "clients/add-client";
	}
	
	@PostMapping("/clients/add")
	public String postClientAdd(@Valid @ModelAttribute("client") ClientReqDTO client, BindingResult result) {
		if(result.hasErrors()) {
			return "clients/add-client";
		}
		clientService.saveToDB(client);
		return "redirect:/clients/display";
	}
	
	@GetMapping("/clients/display")
	public String getClientDisplay(Model model) {
		model.addAttribute("listClient", clientService.getAllClients());
		return "clients/show-client";
	}
}
