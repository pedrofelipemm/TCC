package br.com.mendes.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.mendes.model.Cliente;
import br.com.mendes.model.Endereco;
import br.com.mendes.service.ClienteService;
import br.com.mendes.service.EnderecoService;

@Scope(value="request")
@Controller("clienteMB")
public class ClienteMB implements Serializable{

	private static final long serialVersionUID = 7948132687109359178L;

	private Cliente cliente;
	
	private Endereco endereco;
	
	private List<Cliente> clientes;
	
	@Autowired 
	private ClienteService clienteService;
	
	@Autowired 
	private EnderecoService enderecoService;
	
	@PostConstruct
	public void iniciar() {
		
		cliente = new Cliente();
		
		endereco = new Endereco();
		
		clientes = clienteService.obterTodosCliente();
	}    
	
	public ClienteMB() {
		
	}
	
	public String iniciarEdicao(Long codCliente) {
		
		cliente = clienteService.obterClientePorCod(codCliente);
		
		endereco = cliente.getEndereco();
		
		return "/paginas/cadastroCliente.xhtml";
	}
	
    public void salvarCliente() {
    	
    	endereco = enderecoService.criarEndereco(endereco);
    	
    	cliente.setEndereco(endereco);
    	
    	cliente = clienteService.criarCliente(cliente);
    	
    	FacesContext.getCurrentInstance().addMessage(null, 
	      		new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso" , "Cadastrado com sucesso."));  
    	
    	clientes = clienteService.obterTodosCliente();
    	
    	limparDados();
    }
    
    public void limparDados() {
    	cliente = new Cliente();
    	endereco = new Endereco();
    }

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public List<Cliente> getClientes() {
		return clientes;
	}


	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
  
}
