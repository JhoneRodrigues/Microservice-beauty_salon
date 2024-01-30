package br.com.jhonerodrigues.infra.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.jhonerodrigues.adapters.gateways.UserRepository;
import br.com.jhonerodrigues.core.domain.User;
import br.com.jhonerodrigues.core.exceptions.DataIntegratyViolationException;
import br.com.jhonerodrigues.core.exceptions.ResourceNotFoundException;

@Repository
public class UserRepositoryImpl implements UserRepository{
	
	@Autowired
	private jpaUserRepository repository;

	@Override
	public List<User> findAll() {
		return repository.findAll();
	}

	@Override
	public User findById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	@Override
	public User insert(User user) {
		findByEmail(user);
		return repository.save(user);
	}

	@Override
	public User update(Long id, User obj) {
		var entity = findById(id);
		return repository.save(updateData(entity, obj));
	}
	
	private User updateData(User entity, User obj) {
		entity.setBirthday(obj.getBirthday());
		entity.setName(obj.getName());
		entity.setPhone(obj.getPhone());
		entity.setSchedulings(obj.getSchedulings());
		return entity;
	}
	
	private void findByEmail (User entity) {
		Optional<User> user = repository.findByPhone(entity.getPhone());
		if(user.isPresent()) throw new DataIntegratyViolationException(entity.getPhone());
	}
}
