package br.com.jhonerodrigues.core.usecases;

import java.util.List;

import br.com.jhonerodrigues.core.DTO.UserDTO;

public interface UserService {
	
	List<UserDTO> findAll();
	UserDTO findById(Long id);
}
