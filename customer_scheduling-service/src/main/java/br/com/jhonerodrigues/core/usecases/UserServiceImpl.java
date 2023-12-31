package br.com.jhonerodrigues.core.usecases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jhonerodrigues.adapters.gateways.UserRepository;
import br.com.jhonerodrigues.core.DTO.UserDTO;
import br.com.jhonerodrigues.core.domain.User;
import br.com.jhonerodrigues.core.requests.UserRequest;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository repository;

	@Override
	public List<UserDTO> findAll() {
		return repository.findAll();
	}

	@Override
	public UserDTO findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public User insert(UserRequest request) {
		return repository.insert(request);
	}

}
