package br.com.mendes.view;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

@ManagedBean(name = "loginBean")
@ViewScoped
public class LoginBean {

	@ManagedProperty(value = "#{authenticationManager}")
	private AuthenticationManager authenticationManager;

	private String username;

	private String password;

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

	public AuthenticationManager getAuthenticationManager() {
		return this.authenticationManager;
	}

	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
