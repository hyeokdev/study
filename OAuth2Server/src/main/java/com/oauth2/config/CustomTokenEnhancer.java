package com.oauth2.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import com.oauth2.exception.UserException;
import com.oauth2.model.User;
import com.oauth2.repo.UserJpaRepo;

public class CustomTokenEnhancer implements TokenEnhancer {
	@Autowired
	private UserJpaRepo userJpaRepo;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Optional<User> optional = userJpaRepo.findByUserName(authentication.getPrincipal().toString());
		optional.orElseThrow(() -> new UserException("유저를 찾을 수 없습니다."));
		User user = optional.get();
		Map<String, Object> addInfoMap = new HashMap<>();
		addInfoMap.put("user_id", user.getUserId());
		addInfoMap.put("user_nickname", user.getUserNickname());
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(addInfoMap);
		return accessToken;
	}
}
