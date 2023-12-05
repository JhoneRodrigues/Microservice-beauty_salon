package br.com.jhonerodrigues.core.response;

import java.util.List;

import br.com.jhonerodrigues.core.DTO.SchedulingDTO;
import br.com.jhonerodrigues.core.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SchedulingResponse {
	
	private Long id;
	private String name;
	private List <SchedulingDTO> list;
	
	public SchedulingResponse (User entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.list = entity.getSchedulings().stream().map(x -> new SchedulingDTO(x)).toList();
	}
}
