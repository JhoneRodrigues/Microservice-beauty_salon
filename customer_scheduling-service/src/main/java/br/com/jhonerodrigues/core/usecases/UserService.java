package br.com.jhonerodrigues.core.usecases;

import java.util.List;

import br.com.jhonerodrigues.core.DTO.UserDTO;
import br.com.jhonerodrigues.core.requests.UserRequest;

public interface UserService {
	
	List<UserDTO> findAll();
	UserDTO findById(Long id);
	UserDTO insert (UserRequest request);
}
