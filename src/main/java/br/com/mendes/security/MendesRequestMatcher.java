package br.com.mendes.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.RequestMatcher;

public class MendesRequestMatcher implements RequestMatcher {

	@Override
	public boolean matches(HttpServletRequest request) {

		String header = request.getHeader("faces-request");
		String heade = request.getHeader("primefacesAjaxRequest");

		System.out.println(request.getHeader("faces-request") != null);

		return request.getHeader("faces-request") != null;
	}

}
