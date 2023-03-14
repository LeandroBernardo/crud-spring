package br.com.growintech.crud.repository;

import br.com.growintech.crud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
