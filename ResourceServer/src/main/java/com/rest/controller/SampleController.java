package com.rest.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rest.model.OAuthToken;
import com.rest.model.User;
import com.rest.service.UserService;

@Controller
public class SampleController {
	@Autowired
	UserService userService;
	
	@Autowired
	HttpServletResponse response;
	
	@RequestMapping("/loginView")
	public String sample() {
		return "loginView";
	}
	
	@RequestMapping("/joinView")
	public String joinView() {
		return "joinView";
	}
	
	@RequestMapping("/join")
	public String join(
			User user
			) {
		userService.saveUser(user);
		return "redirect:/loginView";
	}
	
	@RequestMapping("/login")
	public String login(
			User user
			) {
		OAuthToken token = userService.getToken(user.getUid(), user.getPassword());
		String access_token = token.getAccess_token();
		System.out.println("access_token : " + access_token);

		return "redirect:/main";
	}
	
	@RequestMapping("/main")
	public String main(
			@RequestHeader(value="Authorization") String token,
			ModelMap model
			) {
		System.out.println(token);
		return "main";
	}
}
