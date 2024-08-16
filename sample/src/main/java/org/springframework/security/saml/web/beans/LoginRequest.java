package org.springframework.security.saml.web.beans;

import java.io.Serializable;

public class LoginRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9217794535078456293L;
	
	private String user;
	
	private String pass;

	public LoginRequest() {
	}

	public LoginRequest(final String user, final String pass) {
		this.user = user;
		this.pass = pass;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(final String user) {
		this.user = user;
	}

	/**
	 * @return the pass
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * @param pass the pass to set
	 */
	public void setPass(final String pass) {
		this.pass = pass;
	}

}
