package br.com.mendes.view;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.mendes.model.Meta;
import br.com.mendes.model.Servico;
import br.com.mendes.model.TipoServico;
import br.com.mendes.service.MetaService;
import br.com.mendes.service.ServicoService;

@Scope(value="request")
@Controller("servicoMB")
public class ServicoMB implements Serializable{
	
	private static final long serialVersionUID = 7157226923907682989L;

	private Servico servico;
	
	private List<Servico> servicos;
	
	private List<TipoServico> tiposServico;
	
	private Double valorMeta;
	
	@Autowired 
	private ServicoService servicoService;
	
	@Autowired
	private MetaService metaService;
	
	@PostConstruct
	public void iniciar() {
		
		servicos = servicoService.obterTodosServicos();
		
		tiposServico =  Arrays.asList(TipoServico.values());
		
		resetDados();
	}
	

	public void resetDados() {
		servico = new  Servico();
		valorMeta = null;
	}
	
    
	public String iniciarEdicao(Long cod) {
		
		servico = servicoService.obterServicoPorCod(cod);
		
		Meta meta = metaService.obterMetaEspecificaAtual(cod);
		
		if(meta!=null)
			valorMeta = meta.getValor();
		
		return "/paginas/cadastroServico.xhtml";
	}
	
    public void salvarServico() {
    	
    	Servico servicoSalvo = servicoService.criarServico(servico);
    	
    	if(valorMeta!=null)
    		metaService.criarMetaEspecifica(valorMeta, servicoSalvo);
    	
    	FacesContext.getCurrentInstance().addMessage(null, 
	      		new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso" , "Cadastrado com sucesso."));  
    	
    	resetDados();
    	
    }

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}


	public List<Servico> getServicos() {
		return servicos;
	}


	public void setServicos(List<Servico> servicos) {
		this.servicos= servicos;
	}
	
	public List<TipoServico> getTiposServico() {
		return tiposServico;
	}

	public void setTiposServico(List<TipoServico> tiposServico) {
		this.tiposServico = tiposServico;
	}  

	public Double getValorMeta() {
		return valorMeta;
	}

	public void setValorMeta(Double valorMeta) {
		this.valorMeta = valorMeta;
	}
}
