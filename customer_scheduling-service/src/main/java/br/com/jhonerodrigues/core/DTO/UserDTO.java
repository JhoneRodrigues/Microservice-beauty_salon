package br.com.jhonerodrigues.core.DTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import br.com.jhonerodrigues.core.domain.Scheduling;
import br.com.jhonerodrigues.core.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
	
	private Long id;
	private String name;
	private LocalDate birthday;
	private String phone;
	private List<Scheduling> schedulings = new ArrayList<>(); 
	
	public UserDTO(Long id, String name, LocalDate birthday, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.birthday = birthday;
		this.phone = phone;
	}
	
	public UserDTO(User domain) {
		BeanUtils.copyProperties(domain, this);
	}
}	
