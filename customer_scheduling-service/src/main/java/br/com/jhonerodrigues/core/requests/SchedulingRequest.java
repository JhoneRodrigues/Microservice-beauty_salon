package br.com.jhonerodrigues.core.requests;

import java.time.LocalDate;
import java.util.List;

import br.com.jhonerodrigues.core.domain.enums.StandardTimes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SchedulingRequest {
	
	private LocalDate day;
	private StandardTimes time;
	private List<JobId> jobs;
}
