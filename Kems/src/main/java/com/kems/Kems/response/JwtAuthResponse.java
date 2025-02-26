package com.kems.Kems.response;

import com.kems.Kems.entity.UserDto;
import lombok.Data;

@Data
public class JwtAuthResponse {

	private String token;
	
	private UserDto user;

	/**
	 * get field
	 *
	 * @return token
	 */
	public String getToken() {
		return this.token;
	}

	/**
	 * set field
	 *
	 * @param token
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * get field
	 *
	 * @return user
	 */
	public UserDto getUser() {
		return this.user;
	}

	/**
	 * set field
	 *
	 * @param user
	 */
	public void setUser(UserDto user) {
		this.user = user;
	}
}
