package br.com.jhonerodrigues.core.DTO;

import java.time.LocalDate;

import br.com.jhonerodrigues.core.domain.enums.StandardTimes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SchedulingDTOForMessage {
	
	private LocalDate col_day;
	private StandardTimes col_time;
	private String client_email;
	private String client_name;
	private Long professional_id;
}
