package br.com.jhonerodrigues.adapters.gateways;

import java.util.List;

import br.com.jhonerodrigues.core.domain.User;

public interface UserRepository {
	
	List<User> findAll();
	User findById(Long id);
	User insert (User request);
	User update (Long id, User user);
}
