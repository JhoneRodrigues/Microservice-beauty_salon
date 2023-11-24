package br.com.jhonerodrigues.core.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "clients")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private LocalDate birthday;
	
	@OneToMany(mappedBy = "client")
	private List<Scheduling> schedulings = new ArrayList<>();

	public User(Long id, String name, LocalDate birthday) {
		this.id = id;
		this.name = name;
		this.birthday = birthday;
	}	
}
