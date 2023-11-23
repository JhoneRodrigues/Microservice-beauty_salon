package br.com.jhonerodrigues.infra.user;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jhonerodrigues.core.domain.User;

public interface jpaUserRepository extends JpaRepository<User, Long>{

}
