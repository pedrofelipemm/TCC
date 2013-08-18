package br.com.mendes.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.mendes.model.Endereco;
import br.com.mendes.service.EnderecoService;

@ManagedBean(name = "enderecoMB")
@ViewScoped
public class EnderecoMB implements Serializable{

	private static final long serialVersionUID = -4165603506554303884L;

	private Endereco endereco;

	private List<Endereco> enderecos;

	@ManagedProperty(name = "enderecoService", value = "#{enderecoService}")
	private EnderecoService enderecoService;

	@PostConstruct
	public void iniciar() {
		this.enderecos = this.enderecoService.obterTodosEnderecos();
	}

	public EnderecoMB() {

		this.endereco = new Endereco();
	}


	public void salvarEndereco() {

		this.enderecoService.criarEndereco(this.endereco);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso" , "Cadastrado com sucesso."));

		this.enderecos = this.enderecoService.obterTodosEnderecos();
	}

	public Endereco getEndereco() {
		return this.endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}


	public List<Endereco> getEnderecos() {
		return this.enderecos;
	}


	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

}
