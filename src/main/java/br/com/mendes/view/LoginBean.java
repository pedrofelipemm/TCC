package br.com.mendes.view;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

@Controller
@Scope("request")
public class LoginBean {

	private AuthenticationManager authenticationManager;

	private String username;

	private String password;

	@Autowired
	public LoginBean(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	public String login() {

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(this.username, this.password);

		try {

			Authentication authentication = this.authenticationManager.authenticate(token);
			SecurityContext springContext = SecurityContextHolder.getContext();
			springContext.setAuthentication(authentication);

			return "success";

		} catch (AuthenticationException loginError) {

			FacesContext facesContext = FacesContext.getCurrentInstance();

			FacesMessage message = new FacesMessage("Invalid username/password. Reason " + loginError.getMessage());

			facesContext.addMessage(null, message);

			return "fail";
		}
	}
}
