package br.com.mendes.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.mendes.model.Endereco;
import br.com.mendes.service.EnderecoService;

@Scope(value="request")
@Controller("enderecoMB")
public class EnderecoMB implements Serializable{

	private static final long serialVersionUID = -4165603506554303884L;
	
	private Endereco endereco;
	
	private List<Endereco> enderecos;
	
	@Autowired 
	private EnderecoService enderecoService;
	
	@PostConstruct
	public void iniciar() {
		enderecos = enderecoService.obterTodosEnderecos();
	}
	
    public EnderecoMB() {  
    	
    	endereco = new Endereco();    	
    }
    
        
    public void salvarEndereco() {
    	
    	enderecoService.criarEndereco(endereco);
    	FacesContext.getCurrentInstance().addMessage(null, 
	      		new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso" , "Cadastrado com sucesso."));  
    	
    	enderecos = enderecoService.obterTodosEnderecos();
    }

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}


	public List<Endereco> getEnderecos() {
		return enderecos;
	}


	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}  
  
}
