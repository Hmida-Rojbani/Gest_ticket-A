package de.tekup.rst.entities;

import java.time.LocalDate;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class Client {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nom;
	private String prenom;
	
	private LocalDate dateDeNaissance;
	
	private String courrier;
	
	private String telephone;
	

}
