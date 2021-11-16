package de.tekup.rst.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import de.tekup.rst.entities.MetEntity;
import de.tekup.rst.entities.Plat;
import de.tekup.rst.repositories.MetRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StatService {
	
	private MetRepository metRepository;
	
	public String platAcheter(LocalDate dateA, LocalDate dateB) {
		List<MetEntity> mets = metRepository.findAll();
		mets.removeIf(m-> {
			return m instanceof Plat == false;
		});
		
		for (MetEntity plat : mets) {
			plat.getTickets().removeIf(t-> t.getDateTime().isBefore(dateA.atStartOfDay()) 
					|| t.getDateTime().isAfter(dateB.atStartOfDay()));
		}
		MetEntity platMax = null;
		int max = -1;
		
		for (MetEntity plat : mets) {
			if(plat.getTickets().size() > max) {
				max = plat.getTickets().size();
				platMax = plat;
			}
		}
		return platMax.getName();
	}

}
