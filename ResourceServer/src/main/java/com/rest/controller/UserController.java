package com.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.model.User;
import com.rest.repo.UserJpaRepo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/user")
public class UserController {

	@Autowired
	private UserJpaRepo userJpaRepo;
	
	@PostMapping
	public ResponseEntity<String> join(@RequestBody User user) {
		List<String> roles = new ArrayList<String>();
		roles.add("USER");
		user.setRoles(roles);
		userJpaRepo.save(user);
		return new ResponseEntity<String>("성공", HttpStatus.OK);
	}
	
    @GetMapping(value = "/users")
    public List<User> findAllUser() {
        return userJpaRepo.findAll();
    }
}