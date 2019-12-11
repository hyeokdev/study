package com.hyeokdev.user.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TokenInfo {
	private String user_name;
	private int user_id;
	private String user_nickname;
	private List<String> scope;
	private boolean active;
	private int exp;
	private List<String> authorities;
}
