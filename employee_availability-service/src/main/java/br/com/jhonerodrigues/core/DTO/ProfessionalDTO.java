package br.com.jhonerodrigues.core.DTO;

import java.util.Set;

import org.springframework.beans.BeanUtils;

import br.com.jhonerodrigues.core.domain.Professional;
import br.com.jhonerodrigues.core.domain.Scheduling;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfessionalDTO {
	
	private Long id;
	private String name;
	private String urlImage;
	private String description;
	private Set <Scheduling> schedulings;
	
	public ProfessionalDTO(Professional domain) {
		BeanUtils.copyProperties(domain, this);
	}
}
