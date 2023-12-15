package br.com.jhonerodrigues.core.domain;

import java.time.LocalDate;

import br.com.jhonerodrigues.core.domain.enums.SchedulingStatus;
import br.com.jhonerodrigues.core.domain.enums.StandardTimes;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")

@Entity
public class Scheduling {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate col_day;
	private StandardTimes col_time;
	private String client_phone;
	private String client_name;
	private SchedulingStatus status;
}
