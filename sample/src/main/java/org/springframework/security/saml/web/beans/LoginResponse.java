package org.springframework.security.saml.web.beans;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

public class LoginResponse implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4473959136571529624L;
	
	@JsonProperty("ticket-sso")
	private String ticketSSO;

	/**
	* 
	*/
	public LoginResponse() {
	}

	/**
	 * @param ticketSSO
	 */
	public LoginResponse(final String ticketSSO) {
		this.ticketSSO = ticketSSO;
	}

	/**
	 * @return the ticketSSO
	 */
	public String getTicketSSO() {
		return ticketSSO;
	}

	/**
	 * @param ticketSSO
	 *            the ticketSSO to set
	 */
	public void setTicketSSO(final String ticketSSO) {
		this.ticketSSO = ticketSSO;
	}

}
