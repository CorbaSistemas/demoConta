package com.example.demoConta.domain.user.service;

import com.example.demoConta.domain.user.model.entidade.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Dionízio Inácio on 24/05/2024
 */
public interface IUserService {

	public Optional<User> findByUsername(String username);
	public User saveUser(User user);

	}
