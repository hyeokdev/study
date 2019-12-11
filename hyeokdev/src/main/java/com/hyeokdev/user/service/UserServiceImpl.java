package com.hyeokdev.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import com.google.gson.Gson;
import com.hyeokdev.error.BoardException;
import com.hyeokdev.user.model.OAuthToken;
import com.hyeokdev.user.model.TokenInfo;
import com.hyeokdev.user.model.User;
import com.hyeokdev.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private Gson gson;
	
	@Value("${security.oauth2.jwt.signkey}")
    private String signKey;
	
	@Override
	public void insertUser(User user) {
		user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
		List<String> roles = new ArrayList<String>();
		roles.add("USER");
		user.setRoles(roles);
		userRepository.save(user);
	}

	@Override
	public User getUser(String userName) {
		Optional<User>optional = userRepository.findByUserName(userName);
		optional.orElseThrow(() -> new BoardException("유저를 찾을 수 없습니다."));
		return optional.get();
	}
	
	
	@Override
	public OAuthToken getToken(String userName, String userPassword) {
		String credentials = "poongd-client:poongd-password";
        String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.add("Authorization", "Basic " + encodedCredentials);
        
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("username", userName);
        params.add("password", userPassword);
        
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8082/oauth/token", request, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            OAuthToken token = gson.fromJson(response.getBody(), OAuthToken.class);
            return token;
        }
		return null;
	}

	@Override
	public TokenInfo getTokenInfo(String access_token) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("token", access_token);
		ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8082/oauth/check_token", params, String.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			TokenInfo tokenInfo = gson.fromJson(response.getBody(), TokenInfo.class);
			return tokenInfo;
		}
		return null;
	}

}
