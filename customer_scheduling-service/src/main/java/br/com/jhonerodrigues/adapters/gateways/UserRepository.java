package br.com.jhonerodrigues.adapters.gateways;

import java.util.List;

import br.com.jhonerodrigues.core.DTO.UserDTO;

public interface UserRepository {
	
	List<UserDTO> findAll();
	UserDTO findById(Long id);
}
