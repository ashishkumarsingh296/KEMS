package com.kems.Kems.payload;

import lombok.Data;

//@Data
public class JwtAuthRequest {

	private String username;
	
	private String password;

	/**
	 * get field
	 *
	 * @return username
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * set field
	 *
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * get field
	 *
	 * @return password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * set field
	 *
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
