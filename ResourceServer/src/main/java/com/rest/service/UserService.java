package com.rest.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.rest.model.OAuthToken;
import com.rest.model.User;
import com.rest.repo.UserJpaRepo;

@Service
public class UserService {
	@Autowired
	UserJpaRepo userJpaRepo;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
    RestTemplate restTemplate;
	
	@Autowired
	Gson gson;
	
	public void saveUser(User user) {
		List<String> roles = new ArrayList<String>();
		roles.add("ROLE_USER");
		user.setRoles(roles);
		user.setPassword(encoder.encode(user.getPassword()));
		userJpaRepo.save(user);
	}
	public OAuthToken getToken(String uid, String password) {
		String credentials = "poongd-client:poongd-password";
        String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.add("Authorization", "Basic " + encodedCredentials);
        
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("username", uid);
        params.add("password", password);
        
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8082/oauth/token", request, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            OAuthToken token = gson.fromJson(response.getBody(), OAuthToken.class);
            return token;
        }
        return null;
	}
}
