package br.com.mendes.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.RequestMatcher;

public class MendesRequestMatcher implements RequestMatcher {

	@Override
	public boolean matches(HttpServletRequest request) {

		return request.getHeader("faces-request") != null;

	}

}
