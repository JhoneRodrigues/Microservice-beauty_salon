package br.com.jhonerodrigues.core.DTO;

import java.time.LocalDate;

import org.springframework.beans.BeanUtils;

import br.com.jhonerodrigues.core.domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
	
	private Long id;
	private String name;
	private LocalDate birthday;
	
	public UserDTO(User domain) {
		BeanUtils.copyProperties(domain, this);
	}
}	
