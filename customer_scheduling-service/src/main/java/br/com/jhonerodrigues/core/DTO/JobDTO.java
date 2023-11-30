package br.com.jhonerodrigues.core.DTO;

import br.com.jhonerodrigues.core.domain.Job;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobDTO {
	
	private Long id;
	private String name;
	private Double price;
	private Integer durationMinutes;
	
	public JobDTO(Job domain) {
		setId(domain.getId());
		setName(domain.getName());
		setPrice(domain.getPrice());
		setDurationMinutes(domain.getDurationMinutes());
	}
}	
