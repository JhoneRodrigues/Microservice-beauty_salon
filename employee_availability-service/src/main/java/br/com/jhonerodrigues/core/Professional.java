package br.com.jhonerodrigues.core;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of="id")

@Entity
public class Professional {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String urlImage;
	private String description;
	
	@OneToMany (fetch = FetchType.EAGER)
	@JoinColumn(name = "professional_id")
	private Set <Scheduling> schedulings;

	public Professional(Long id, String name, String urlImage, String description) {
		super();
		this.id = id;
		this.name = name;
		this.urlImage = urlImage;
		this.description = description;
	}
}
