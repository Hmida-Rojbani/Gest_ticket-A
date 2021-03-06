package de.tekup.rst.services;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import de.tekup.rst.dto.models.ClientResDTO;
import de.tekup.rst.entities.Client;
import de.tekup.rst.entities.MetEntity;
import de.tekup.rst.entities.Plat;
import de.tekup.rst.entities.TicketEntity;
import de.tekup.rst.repositories.ClientRepository;
import de.tekup.rst.repositories.MetRepository;
import de.tekup.rst.repositories.TicketRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StatService {
	
	private MetRepository metRepository;
	private ClientRepository clientRepository;
	private TicketRepository ticketRepository;
	private ModelMapper mapper;
	
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

	public ClientResDTO clientPlusFidele() {
		
		List<Client> clients = clientRepository.findAll();
		
		Client client = clients.stream()
				.max(Comparator.comparing(c-> c.getTickets().size()))
				.orElse(new Client());
		
		return mapper.map(client, ClientResDTO.class);
	}
	
	public String jourPlusReserveParClient(int id) {
		Client client = clientRepository.findById(id).get();
		List<TicketEntity> tickets = client.getTickets();
		List<LocalDateTime> dates = tickets.stream()
											.map(t-> t.getDateTime())
											.collect(Collectors.toList());
		List<DayOfWeek> days = dates.stream()
									.map(d-> d.getDayOfWeek())
									.collect(Collectors.toList());
		System.out.println(days);
		
		Map<DayOfWeek, Long> map = days.stream()
			.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		
		System.out.println(map);
		
		DayOfWeek day = map.entrySet().stream()
								.max(Map.Entry.comparingByValue())
								.get().getKey();
		return day.getDisplayName(TextStyle.FULL, new Locale("fr"));
	}
	
	public Map<String, Double> revenue(){
		List<TicketEntity> tickets = ticketRepository.findAll();
		LocalDate today = LocalDate.now();
		
		double todayAddition =  tickets.stream()
				.filter(t-> t.getDateTime().toLocalDate().isEqual(today))
				.mapToDouble(t-> t.getAddition())
				.sum();
		
		double monthAddition = tickets.stream()
				.filter(t-> t.getDateTime().getMonthValue() == today.getMonthValue() &&
				t.getDateTime().getYear() == today.getYear())
				.mapToDouble(t-> t.getAddition())
				.sum();
		
		TemporalField weekOfYear = WeekFields.ISO.weekOfWeekBasedYear();
		
		double weekAddition = tickets.stream()
				.filter(t-> t.getDateTime().get(weekOfYear) == today.get(weekOfYear) &&
				t.getDateTime().getYear() == today.getYear())
				.mapToDouble(t-> t.getAddition())
				.sum();
		
		Map<String, Double> map = new HashMap<>();
		map.put("Revenue par jour", todayAddition);
		map.put("Revenue par semaine", weekAddition);
		map.put("Revenue par mois", monthAddition);
		
		return map;
	}

	public double revenue(LocalDate dateDeb, LocalDate dateFin) {
		
		return ticketRepository.findAll().stream()
				.filter(t-> t.getDateTime().toLocalDate().isAfter(dateDeb) &&
						t.getDateTime().toLocalDate().isBefore(dateFin))
				.mapToDouble(t-> t.getAddition())
				.sum();
	}
}
