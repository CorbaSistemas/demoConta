package com.example.demoConta.domain.user.interfaces.repository;

import com.example.demoConta.domain.user.model.entidade.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @author Dionízio Inácio on 28/05/2024
 */
@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);

}
