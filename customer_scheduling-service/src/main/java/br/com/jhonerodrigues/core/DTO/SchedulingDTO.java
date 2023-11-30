package br.com.jhonerodrigues.core.DTO;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import br.com.jhonerodrigues.core.domain.Job;
import br.com.jhonerodrigues.core.domain.Scheduling;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SchedulingDTO {

	private Long id;
	private Instant moment;
	private Set <Job> jobs = new HashSet<>();
	
	public SchedulingDTO(Scheduling domain) {
		BeanUtils.copyProperties(domain, this);
	}
}
