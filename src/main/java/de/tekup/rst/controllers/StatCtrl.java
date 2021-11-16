package de.tekup.rst.controllers;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.tekup.rst.services.StatService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/stats")
public class StatCtrl {
	
	private StatService statService;

	@GetMapping("/most/plat/{dateDep}/{dateFin}")
	public String platPlusAcheter(@PathVariable String dateDep, 
			@PathVariable String dateFin) {
		LocalDate dateA = LocalDate.parse(dateDep);
		LocalDate dateB = LocalDate.parse(dateFin);
		return statService.platAcheter(dateA,dateB);
	}
}
