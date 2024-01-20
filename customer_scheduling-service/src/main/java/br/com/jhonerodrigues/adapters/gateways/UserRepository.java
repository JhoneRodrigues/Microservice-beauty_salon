package br.com.jhonerodrigues.adapters.gateways;

import java.util.List;

import br.com.jhonerodrigues.core.domain.User;
import br.com.jhonerodrigues.core.requests.UserRequest;

public interface UserRepository {
	
	List<User> findAll();
	User findById(Long id);
	User insert (UserRequest request);
}
