package com.poongd.demo.configs;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.poongd.demo.accounts.AccountService;
import com.poongd.demo.common.AppProperties;
import com.poongd.demo.common.BaseControllerTest;
import com.poongd.demo.common.TestDescription;

public class AuthServerConfigTest extends BaseControllerTest {

	@Autowired
	AccountService accountService;
	
	@Autowired
	AppProperties appProperties;
	
	@Test
	@TestDescription("인증 토큰을 발급 받는 테스트")
	public void getAuthToken() throws Exception {
						
		this.mockMvc.perform(post("/oauth/token")
					.with(httpBasic(appProperties.getClientId(), appProperties.getClientSecret()))
					.param("username", appProperties.getUserUsername())
					.param("password", appProperties.getUserPassword())
					.param("grant_type", "password"))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(jsonPath("access_token").exists());
	}
	
}
