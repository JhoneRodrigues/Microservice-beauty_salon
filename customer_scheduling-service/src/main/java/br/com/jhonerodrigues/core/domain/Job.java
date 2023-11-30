package br.com.jhonerodrigues.core.domain;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of="id")

@Entity
public class Job {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Double price;
	private Integer durationMinutes;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "jobs")
	private Set <Scheduling> schedulings = new HashSet<>();

	public Job(Long id, String name, Double price, Integer durationMinutes) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.durationMinutes = durationMinutes;
	}
}
