package br.com.mendes.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

public class Http401EntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {

		JsfRedirectStrategy jsfRedirectStrategy = new JsfRedirectStrategy();

		jsfRedirectStrategy.sendRedirect(request, response, "/");

	}
}
