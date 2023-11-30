package br.com.jhonerodrigues.core.domain;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

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
	private Instant moment;
	
	@ManyToMany (fetch = FetchType.EAGER)
	@JoinTable(name = "tb_schedulings_jobs", 
	joinColumns = @JoinColumn(name = "scheduling_id"),
	inverseJoinColumns = @JoinColumn(name = "job_id"))
	private Set <Job> jobs = new HashSet<>();

	public Scheduling(Long id, Instant moment) {
		super();
		this.id = id;
		this.moment = moment;
	}
}
