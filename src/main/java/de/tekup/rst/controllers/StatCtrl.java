package de.tekup.rst.controllers;

import java.time.LocalDate;
import java.util.Map;

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
	
	@GetMapping("/day/client/{id}")
	public String jourResClient(@PathVariable int id) {
		return statService.jourPlusReserveParClient(id);
	}
	
	@GetMapping("/revenue")
	public Map<String, Double> revenue() {
		return statService.revenue();
	}
	
	@GetMapping("/revenue/{deb}/{fin}")
	public double revenue(@PathVariable String deb,
			@PathVariable String fin) {
		LocalDate dateDeb = LocalDate.parse(deb);
		LocalDate dateFin = LocalDate.parse(fin);
		return statService.revenue(dateDeb, dateFin);
	}
}
