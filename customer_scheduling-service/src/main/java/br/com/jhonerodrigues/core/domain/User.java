package br.com.jhonerodrigues.core.domain;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.BeanUtils;

import br.com.jhonerodrigues.core.DTO.UserDTO;
import br.com.jhonerodrigues.core.requests.UserRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
	
	@Column(unique = true)
	private String phone;
	
	@OneToMany (fetch = FetchType.EAGER)
	@JoinColumn(name = "client_id")	
	private List <Scheduling> schedulings;

	public User(Long id, String name, LocalDate birthday, String phone) {
		this.id = id;
		this.name = name;
		this.birthday = birthday;
		this.phone = phone;
	}	
	
	public User (UserRequest request) {
		this.name = request.getName();
		this.birthday = request.getBirthday();
		this.phone = request.getPhone();
	}

	public User(UserDTO dto) {
		BeanUtils.copyProperties(dto, this);
	}
}
