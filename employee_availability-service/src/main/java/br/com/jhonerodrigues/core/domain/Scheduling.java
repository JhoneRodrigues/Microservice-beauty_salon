package br.com.jhonerodrigues.core.domain;

import java.time.LocalDate;

import br.com.jhonerodrigues.core.domain.enums.StandardTimes;
import br.com.jhonerodrigues.core.requests.SchedulingRequest;
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
	
	public Scheduling (SchedulingRequest request) {
		this.col_day = request.getCol_day();
		this.col_time = request.getCol_time();
		this.client_phone = request.getClient_phone();
		this.client_name = request.getClient_name();
	}
}
