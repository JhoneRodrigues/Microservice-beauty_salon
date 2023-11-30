package br.com.jhonerodrigues.adapters.gateways;

import java.util.List;

import br.com.jhonerodrigues.core.DTO.UserDTO;
import br.com.jhonerodrigues.core.domain.User;
import br.com.jhonerodrigues.core.requests.UserRequest;

public interface UserRepository {
	
	List<UserDTO> findAll();
	UserDTO findById(Long id);
	User insert (UserRequest request);
}
