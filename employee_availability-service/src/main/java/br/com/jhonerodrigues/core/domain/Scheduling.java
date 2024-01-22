package br.com.jhonerodrigues.core.domain;

import java.time.LocalDate;

import org.springframework.beans.BeanUtils;

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
	private Long professional_id;
	
	public Scheduling (SchedulingRequest request) {
		BeanUtils.copyProperties(request, this);
	}
}
