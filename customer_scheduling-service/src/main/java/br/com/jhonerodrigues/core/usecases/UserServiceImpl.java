package br.com.jhonerodrigues.core.usecases;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jhonerodrigues.adapters.gateways.UserRepository;
import br.com.jhonerodrigues.core.DTO.UserDTO;
import br.com.jhonerodrigues.core.domain.User;
import br.com.jhonerodrigues.core.exceptions.DataIntegratyViolationException;
import br.com.jhonerodrigues.core.requests.UserRequest;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository repository;

	@Override
	public List<UserDTO> findAll() {
		var result = repository.findAll();
		return result.stream().map(x -> new UserDTO(x)).toList();
	}

	@Override
	public UserDTO findById(Long id) {
		return new UserDTO (repository.findById(id));
	}

	@Override
	public UserDTO insert(UserRequest request) {
		User user = new User(request);
		findByPhone(user);
		return new UserDTO (repository.insert(user));
	}

	@Override
	public UserDTO update(Long id, User user) {
		return new UserDTO (repository.update(id, user));
	}
	
	private void findByPhone (User obj) {
		Optional<User> user = repository.findByPhone(obj.getPhone());
		if (user.isPresent()){
			throw new DataIntegratyViolationException(obj.getPhone());
		}
	}	
}
