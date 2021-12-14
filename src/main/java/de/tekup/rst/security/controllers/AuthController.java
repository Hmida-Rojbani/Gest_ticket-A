package de.tekup.rst.security.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class AuthController {
	
	@GetMapping("/register")
	public String getRegisterForm() {
		return "auth/signup";
	}

}
