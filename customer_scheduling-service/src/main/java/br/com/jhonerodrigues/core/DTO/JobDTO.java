package br.com.jhonerodrigues.core.DTO;

import br.com.jhonerodrigues.core.domain.Job;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
