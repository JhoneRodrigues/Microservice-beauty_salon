package br.com.jhonerodrigues.adapters.gateways;

import java.util.List;
import java.util.Optional;

import br.com.jhonerodrigues.core.domain.User;

public interface UserRepository {
	
	List<User> findAll();
	User findById(Long id);
	User insert (User request);
	User update (Long id, User user);
	Optional<User> findByPhone (String phone);
}
