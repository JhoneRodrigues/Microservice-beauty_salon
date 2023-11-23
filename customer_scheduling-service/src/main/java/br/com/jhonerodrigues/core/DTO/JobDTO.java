package br.com.jhonerodrigues.core.DTO;

import org.springframework.beans.BeanUtils;

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
		BeanUtils.copyProperties(domain, this);
	}
}	
