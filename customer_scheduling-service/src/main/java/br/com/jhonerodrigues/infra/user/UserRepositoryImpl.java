package br.com.jhonerodrigues.infra.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.jhonerodrigues.adapters.gateways.UserRepository;
import br.com.jhonerodrigues.core.DTO.UserDTO;
import br.com.jhonerodrigues.core.domain.User;
import br.com.jhonerodrigues.core.exceptions.ResourceNotFoundException;
import br.com.jhonerodrigues.core.requests.UserRequest;

@Repository
public class UserRepositoryImpl implements UserRepository{
	
	@Autowired
	private jpaUserRepository repository;

	@Override
	public List<UserDTO> findAll() {
		List <User> result= repository.findAll();
		return result.stream().map(x -> new UserDTO(x)).toList();
	}

	@Override
	public UserDTO findById(Long id) {
		UserDTO dto = repository.findById(id)
		        .map(UserDTO::new)
		        .orElseThrow(() -> new ResourceNotFoundException(id));
		return dto;
	}

	@Override
	public User insert(UserRequest request) {
		User entity = new User (request);
		return repository.save(entity);
	}
}
