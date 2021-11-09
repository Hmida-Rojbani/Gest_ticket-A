package de.tekup.rst.dto.models;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ClientReqDTO {

	@NotBlank(message = "Nom doit inclut des chars")
	@Size(min = 3)
	private String nom;
	@NotBlank(message = "Nom doit inclut des chars")
	@Size(min = 3, max = 30)
	private String prenom;
	
	@Past
	private LocalDate dateDeNaissance;
	
	@Email
	private String courriel;
	
	private String telephone;
}
