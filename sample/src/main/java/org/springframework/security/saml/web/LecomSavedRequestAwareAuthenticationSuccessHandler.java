/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.security.saml.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.saml.web.beans.LoginRequest;
import org.springframework.security.saml.web.beans.LoginResponse;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

/**
 *
 * @author ricardo.camargo
 */
public class LecomSavedRequestAwareAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
    	final String url = "http://192.168.33.119:8080/sso/api/v1/authentication";
    	final ObjectMapper mapper = new ObjectMapper();
    	final CloseableHttpClient client = HttpClientBuilder.create().build();
    	final HttpPost post = new HttpPost(url);
    	post.setHeader("Content-Type", "application/json");
    	final LoginRequest loginRequest = new LoginRequest("adm", "lecom");
    	final StringEntity stringEntity = new StringEntity(mapper.writeValueAsString(loginRequest));
    	post.setEntity(stringEntity);
    	final HttpResponse httpResponse = client.execute(post);
    	final String json = EntityUtils.toString(httpResponse.getEntity());
    	
    	final LoginResponse loginResponse = mapper.readValue(json, LoginResponse.class);
    	final Cookie myCookie = new Cookie("LecomSSOTicket", loginResponse.getTicketSSO());
    	myCookie.setPath("/");
    	myCookie.setMaxAge(999999999);
    	response.addCookie(myCookie);
//    	request.getSession(false).setAttribute("user", "adm");
//    	request.getSession(false).setAttribute("password", "lecom");
//    	request.getSession(false).setAttribute("redirectBackTo", "http://192.168.33.119:8080/bpm/minha_area");
//    	
    	response.sendRedirect("http://192.168.33.119:8080/sso/access/signin.do?redirectBackTo=http://192.168.33.119:8080/bpm/minha_area");
//    	HttpSession session = request.getSession();
//    	ServletContext servletContext = session.getServletContext();
//    	ServletContext servletContextSSO = servletContext.getContext("/sso");
//    	RequestDispatcher dispatcher = servletContextSSO.getRequestDispatcher("/access/signin.do");
//    	dispatcher.forward(request, response);
    	
//        super.onAuthenticationSuccess(request, response, authentication); //To change body of generated methods, choose Tools | Templates.
    }
    
}
