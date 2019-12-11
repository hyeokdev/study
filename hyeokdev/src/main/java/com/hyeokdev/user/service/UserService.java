package com.hyeokdev.user.service;

import com.hyeokdev.user.model.OAuthToken;
import com.hyeokdev.user.model.TokenInfo;
import com.hyeokdev.user.model.User;

public interface UserService {
	void insertUser(User user);
	User getUser(String userName);
	OAuthToken getToken(String userName, String userPassword);
	TokenInfo getTokenInfo(String access_token);
}
