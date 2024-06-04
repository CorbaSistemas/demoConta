package com.example.demoConta.domain.user.interfaces.service;

import com.example.demoConta.domain.conta.interfaces.repository.IContaRepository;
import com.example.demoConta.domain.conta.interfaces.service.IContaService;
import com.example.demoConta.domain.conta.model.entidade.Conta;
import com.example.demoConta.domain.user.interfaces.repository.IUserRepository;
import com.example.demoConta.domain.user.model.entidade.User;
import com.example.demoConta.domain.user.service.IUserService;
import com.example.demoConta.infra.configuration.Util;
import com.example.demoConta.infra.exceptions.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @author Dionízio Inácio on 28/05/2024
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService implements IUserService {

	private final IUserRepository userRepository;

	//private final PasswordEncoder passwordEncoder;

	@Override
	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	@Transactional
	public User saveUser(User user) {
	//	user.setPassword(passwordEncoder.encode(user.getPassword()));
		try{
			return userRepository.save(user);
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}

	}
}