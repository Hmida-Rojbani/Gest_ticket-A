package de.tekup.rst.entities;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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
	
	private String courriel;
	
	private String telephone;
	
	public String getNomComplet() {
		return nom+" "+prenom;
	}

	public int getAge() {
		return (int) ChronoUnit.YEARS.between(dateDeNaissance, 
				LocalDate.now());
	}
}
