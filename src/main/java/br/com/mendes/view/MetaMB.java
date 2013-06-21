package br.com.mendes.view;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.mendes.model.MetaGeral;
import br.com.mendes.model.TipoMetaGeral;
import br.com.mendes.service.MetaService;

@Scope(value="request")
@Controller("metaMB")
public class MetaMB implements Serializable{

	private static final long serialVersionUID = 8057294283144563153L;

	private MetaGeral metaCliente;
	private MetaGeral metaProduto;
	private MetaGeral metaServico;
	private MetaGeral metaFeedBackEmail;
	private MetaGeral metaFeedBackTelefone;
	private MetaGeral metaFeedBackPessoal;
		
	@Autowired 
	private MetaService metaService;
	
	@PostConstruct
	public void iniciar() {
		
		metaCliente = metaService.obterMetaGeralAtual(TipoMetaGeral.CLIENTE);
    	metaProduto = metaService.obterMetaGeralAtual(TipoMetaGeral.PRODUTO);
    	metaServico = metaService.obterMetaGeralAtual(TipoMetaGeral.SERVICO);
    	metaFeedBackTelefone = metaService.obterMetaGeralAtual(TipoMetaGeral.FEEDBACK_TELEFONE);
    	metaFeedBackEmail = metaService.obterMetaGeralAtual(TipoMetaGeral.FEEDBACK_EMAIL);
    	metaFeedBackPessoal = metaService.obterMetaGeralAtual(TipoMetaGeral.FEEDBACK_PESSOAL);
    	
    	if(metaCliente==null)
    		metaCliente = new MetaGeral(TipoMetaGeral.CLIENTE);
    	if(metaProduto==null)
    		metaProduto = new MetaGeral(TipoMetaGeral.PRODUTO);
    	if(metaServico==null)
    		metaServico = new MetaGeral(TipoMetaGeral.SERVICO);
    	if(metaFeedBackEmail==null)
    		metaFeedBackEmail = new MetaGeral(TipoMetaGeral.FEEDBACK_EMAIL);
    	if(metaFeedBackTelefone==null)
    		metaFeedBackTelefone = new MetaGeral(TipoMetaGeral.FEEDBACK_TELEFONE);
    	if(metaFeedBackPessoal==null)
    		metaFeedBackPessoal = new MetaGeral(TipoMetaGeral.FEEDBACK_PESSOAL);
	}

	public void limparDados() {
		metaCliente = new MetaGeral(TipoMetaGeral.CLIENTE);
		metaProduto = new MetaGeral(TipoMetaGeral.PRODUTO);
		metaServico = new MetaGeral(TipoMetaGeral.SERVICO);
		metaFeedBackEmail = new MetaGeral(TipoMetaGeral.FEEDBACK_EMAIL);
		metaFeedBackTelefone = new MetaGeral(TipoMetaGeral.FEEDBACK_TELEFONE);
		metaFeedBackPessoal = new MetaGeral(TipoMetaGeral.FEEDBACK_PESSOAL);
	}
	
	public void salvarMeta() {
    	
    	metaService.criarMetaGeral(metaCliente);
    	metaService.criarMetaGeral(metaProduto);
    	metaService.criarMetaGeral(metaServico);
    	metaService.criarMetaGeral(metaFeedBackEmail);
    	metaService.criarMetaGeral(metaFeedBackTelefone);
    	metaService.criarMetaGeral(metaFeedBackPessoal);
    	
    	FacesContext.getCurrentInstance().addMessage(null, 
	      		new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso" , "Cadastrado com sucesso."));  
    }

	public MetaGeral getMetaCliente() {
		return metaCliente;
	}

	public void setMetaCliente(MetaGeral metaCliente) {
		this.metaCliente = metaCliente;
	}

	public MetaGeral getMetaProduto() {
		return metaProduto;
	}

	public void setMetaProduto(MetaGeral metaProduto) {
		this.metaProduto = metaProduto;
	}

	public MetaGeral getMetaServico() {
		return metaServico;
	}

	public void setMetaServico(MetaGeral metaServico) {
		this.metaServico = metaServico;
	}

	public MetaGeral getMetaFeedBackEmail() {
		return metaFeedBackEmail;
	}

	public void setMetaFeedBackEmail(MetaGeral metaFeedBackEmail) {
		this.metaFeedBackEmail = metaFeedBackEmail;
	}

	public MetaGeral getMetaFeedBackTelefone() {
		return metaFeedBackTelefone;
	}

	public void setMetaFeedBackTelefone(MetaGeral metaFeedBackTelefone) {
		this.metaFeedBackTelefone = metaFeedBackTelefone;
	}

	public MetaGeral getMetaFeedBackPessoal() {
		return metaFeedBackPessoal;
	}

	public void setMetaFeedBackPessoal(MetaGeral metaFeedBackPessoal) {
		this.metaFeedBackPessoal = metaFeedBackPessoal;
	}


  
}
