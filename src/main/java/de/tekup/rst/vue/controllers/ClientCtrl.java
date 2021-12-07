package de.tekup.rst.vue.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller(value = "clientCtrlVue")
public class ClientCtrl {

	@GetMapping("/clients/add")
	public String getClientAdd() {
		return "clients/add-client";
	}
}
