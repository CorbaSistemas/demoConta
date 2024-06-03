package com.example.demoConta.interfaces.controller;

import com.example.demoConta.domain.user.interfaces.controller.IUserController;
import com.example.demoConta.domain.user.model.entidade.User;
import com.example.demoConta.domain.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * @author <seu nome> on 03/06/2024
 */
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController implements IUserController {


	private IUserService userService;

	@PostMapping("/login")
	public String login(@RequestParam String username, @RequestParam String password) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication.isAuthenticated()) {
			return "User authenticated successfully";
		} else {
			return "Authentication failed";
		}
	}

	@PostMapping("/register")
	public String register(@RequestBody User user) {
		userService.saveUser(user);
		return "User registered successfully";
	}
}
