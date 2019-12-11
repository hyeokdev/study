package com.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
	private CustomAuthenticationProvider authenticationProvider;
    
    @Autowired
    private PasswordEncoder encoder;
    
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider)
        	.inMemoryAuthentication()
        		.withUser("user")
        		.password(encoder.encode("pass"))
        		.roles("USER")
        		.and()
        		.withUser("admin")
        		.password(encoder.encode("pass"))
        		.roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security
        		.csrf().disable()
        		.headers().frameOptions().disable()
        		.and()
                .authorizeRequests().antMatchers("/oauth2/**").permitAll()
                	.and()
                .formLogin()
                	.and()
                .httpBasic();
    }
}