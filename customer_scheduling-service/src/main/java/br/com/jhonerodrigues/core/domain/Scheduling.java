package br.com.jhonerodrigues.core.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import br.com.jhonerodrigues.core.domain.enums.StandardTimes;
import br.com.jhonerodrigues.core.requests.SchedulingRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of="id")

@Entity
@Table(name = "schedulings")
public class Scheduling {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate col_day;
	private StandardTimes col_time;
	private Long professional_id;
	private Long client_id;
	
	@ManyToMany (fetch = FetchType.EAGER)
	@JoinTable(name = "tb_schedulings_jobs", 
	joinColumns = @JoinColumn(name = "scheduling_id"),
	inverseJoinColumns = @JoinColumn(name = "job_id"))
	private Set <Job> jobs = new HashSet<>();

	public Scheduling(Long id, LocalDate day, StandardTimes time, Long professionalId, Long client_id) {
		this.id = id;
		this.col_day = day;
		this.col_time = time;
		this.professional_id = professionalId;
		this.client_id = client_id;
	}
	
	public Scheduling (Long id, SchedulingRequest request) {
		this.col_day = request.getDay();
		this.col_time = request.getTime();
		this.professional_id = request.getProfessional_id();
		this.client_id = id;
	}
}
