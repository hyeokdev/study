package com.hyeokdev.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hyeokdev.user.model.OAuthToken;
import com.hyeokdev.user.model.TokenInfo;
import com.hyeokdev.user.model.User;
import com.hyeokdev.user.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/info")
	public ResponseEntity<TokenInfo> userInfo(@RequestParam String accessToken) {
		TokenInfo tokenInfo = userService.getTokenInfo(accessToken);
		return new ResponseEntity<TokenInfo>(tokenInfo, HttpStatus.OK);
	}
	
	@PostMapping("/post")
	public ResponseEntity<User> userInsert(@RequestBody User user) {
		userService.insertUser(user);
		System.out.println(user.toString());
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@GetMapping("/login")
	public ResponseEntity<OAuthToken> login(@RequestParam String userName, @RequestParam String userPassword) {
		OAuthToken token = userService.getToken(userName, userPassword);
		return new ResponseEntity<OAuthToken>(token, HttpStatus.OK);
	}
}
