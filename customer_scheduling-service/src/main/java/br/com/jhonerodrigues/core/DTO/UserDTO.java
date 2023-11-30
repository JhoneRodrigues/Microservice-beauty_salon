package br.com.jhonerodrigues.core.DTO;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.BeanUtils;

import br.com.jhonerodrigues.core.domain.Scheduling;
import br.com.jhonerodrigues.core.domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
	
	private Long id;
	private String name;
	private LocalDate birthday;
	private List<Scheduling> schedulings;
	
	public UserDTO(User domain) {
		BeanUtils.copyProperties(domain, this);
	}
}	
