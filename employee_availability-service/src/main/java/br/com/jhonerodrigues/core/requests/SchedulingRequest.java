package br.com.jhonerodrigues.core.requests;

import java.time.LocalDate;

import br.com.jhonerodrigues.core.domain.enums.StandardTimes;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SchedulingRequest {
	
	private LocalDate col_day;
	private StandardTimes col_time;
	private String client_phone;
	private String client_name;
	private Long professional_id;
}
