package de.tekup.rst.vue.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import de.tekup.rst.dto.models.TicketDTO;
import de.tekup.rst.services.ClientService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class TicketCtrlVue {

	private ClientService clientService;
	@GetMapping("/create/ticket")
	public String createTicket(Model model) {
		model.addAttribute("ticket",new TicketDTO());
		model.addAttribute("listClient", clientService.getAllClients());
		return "tickets/add-ticket";
	}
}
