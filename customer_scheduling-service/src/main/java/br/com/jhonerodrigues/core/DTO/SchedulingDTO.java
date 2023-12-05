package br.com.jhonerodrigues.core.DTO;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import br.com.jhonerodrigues.core.domain.Job;
import br.com.jhonerodrigues.core.domain.Scheduling;
import br.com.jhonerodrigues.core.domain.enums.StandardTimes;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SchedulingDTO {

	private Long id;
	private LocalDate col_day;
	private StandardTimes col_time;
	private Set <Job> jobs = new HashSet<>();
	
	public SchedulingDTO(Scheduling domain) {
		BeanUtils.copyProperties(domain, this);
	}
}
