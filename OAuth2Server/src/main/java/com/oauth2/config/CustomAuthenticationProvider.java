package com.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.oauth2.model.User;
import com.oauth2.repo.UserJpaRepo;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private PasswordEncoder passwordEncoder;
    
	@Autowired
	private UserJpaRepo userJpaRepo;

    @Override
    public Authentication authenticate(Authentication authentication) {
    	
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = userJpaRepo.findByUserName(name).orElseThrow(() -> new UsernameNotFoundException("user is not exists"));

        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new BadCredentialsException("password is not valid");

        return new UsernamePasswordAuthenticationToken(name, password, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(
                UsernamePasswordAuthenticationToken.class);
    }
}