package br.com.mendes.utils;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.context.RequestContext;

public class MBUtil implements Serializable {
	
	private static final long serialVersionUID = 2130253192385823285L;

	public static  void addFatal(String message) {
		
		addMessage(FacesMessage.SEVERITY_FATAL, message, message);
	}
	
	public static void addError(String message) {
		
		addMessage(FacesMessage.SEVERITY_ERROR, message, message);
	}
	
	public static void addWarn(String message) {
		
		addMessage(FacesMessage.SEVERITY_WARN, message, message);
	}
	
	public static void addInfo(String message) {
		
		addMessage(FacesMessage.SEVERITY_INFO, message, message);
	}

	public static void addMessage(Severity tipo, String title, String message) {
		
		FacesContext.getCurrentInstance().addMessage(null, 
	      		new FacesMessage(tipo, title , message));  
	}
	
	public static void addMessage(Gravidade tipo, String title, List<String> messages) {
		
		Severity severity = null;
		StringBuilder message = new StringBuilder();
		
		switch (tipo) {
			case INFO:
				severity = FacesMessage.SEVERITY_INFO;
				break;

			case AVISO:
				severity = FacesMessage.SEVERITY_WARN;
				break;
				
			case ERRO:
				severity = FacesMessage.SEVERITY_ERROR;
				break;

			case FATAL:
				severity = FacesMessage.SEVERITY_FATAL;
				break;

		}
		
		for(String msg : messages) {
			message.append(msg + "\n");
		}
		
		FacesContext.getCurrentInstance().addMessage(null, 
	      		new FacesMessage(severity, message.toString() , message.toString()));  
	}
	
	public static void update(String components) {
		
		RequestContext context = RequestContext.getCurrentInstance();
		context.update(components);
	}
	
	public static void execute(String script) {
		
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute(script);
	}
	
	public static RequestContext getContext() {		
		return RequestContext.getCurrentInstance();
	}
	
	public static String getRealPath() {
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
 
		ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
 
		return scontext.getRealPath("");
		 
	}
}