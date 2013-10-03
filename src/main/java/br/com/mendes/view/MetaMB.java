package br.com.mendes.view;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.mendes.model.MetaGeral;
import br.com.mendes.model.TipoMetaGeral;
import br.com.mendes.service.MetaService;

@ManagedBean(name = "metaMB")
@ViewScoped
public class MetaMB implements Serializable {

	private static final long serialVersionUID = 8057294283144563153L;

	private MetaGeral metaCliente;
	private MetaGeral metaProduto;
	private MetaGeral metaServico;
	private MetaGeral metaFeedBackEmail;
	private MetaGeral metaFeedBackTelefone;
	private MetaGeral metaFeedBackPessoal;

	@ManagedProperty(name = "metaService", value = "#{metaService}")
	private MetaService metaService;

	@PostConstruct
	public void iniciar() {
		init();
	}

	private void init() {
		this.metaCliente = this.metaService.obterMetaGeralAtual(TipoMetaGeral.CLIENTE);
		this.metaProduto = this.metaService.obterMetaGeralAtual(TipoMetaGeral.PRODUTO);
		this.metaServico = this.metaService.obterMetaGeralAtual(TipoMetaGeral.SERVICO);
		this.metaFeedBackTelefone = this.metaService.obterMetaGeralAtual(TipoMetaGeral.FEEDBACK_TELEFONE);
		this.metaFeedBackEmail = this.metaService.obterMetaGeralAtual(TipoMetaGeral.FEEDBACK_EMAIL);
		this.metaFeedBackPessoal = this.metaService.obterMetaGeralAtual(TipoMetaGeral.FEEDBACK_PESSOAL);

		if (this.metaCliente == null) {
			this.metaCliente = new MetaGeral(TipoMetaGeral.CLIENTE);
		}
		if (this.metaProduto == null) {
			this.metaProduto = new MetaGeral(TipoMetaGeral.PRODUTO);
		}
		if (this.metaServico == null) {
			this.metaServico = new MetaGeral(TipoMetaGeral.SERVICO);
		}
		if (this.metaFeedBackEmail == null) {
			this.metaFeedBackEmail = new MetaGeral(TipoMetaGeral.FEEDBACK_EMAIL);
		}
		if (this.metaFeedBackTelefone == null) {
			this.metaFeedBackTelefone = new MetaGeral(TipoMetaGeral.FEEDBACK_TELEFONE);
		}
		if (this.metaFeedBackPessoal == null) {
			this.metaFeedBackPessoal = new MetaGeral(TipoMetaGeral.FEEDBACK_PESSOAL);
		}
	}

	public void limparDados() {
		this.metaCliente = new MetaGeral(TipoMetaGeral.CLIENTE);
		this.metaProduto = new MetaGeral(TipoMetaGeral.PRODUTO);
		this.metaServico = new MetaGeral(TipoMetaGeral.SERVICO);
		this.metaFeedBackEmail = new MetaGeral(TipoMetaGeral.FEEDBACK_EMAIL);
		this.metaFeedBackTelefone = new MetaGeral(TipoMetaGeral.FEEDBACK_TELEFONE);
		this.metaFeedBackPessoal = new MetaGeral(TipoMetaGeral.FEEDBACK_PESSOAL);
	}

	public void salvarMeta() {
		criarMetas();
		init();

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Cadastrado com sucesso."));
	}

	private void criarMetas() {
		this.metaService.criarMetaGeral(this.metaCliente);
		this.metaService.criarMetaGeral(this.metaProduto);
		this.metaService.criarMetaGeral(this.metaServico);
		this.metaService.criarMetaGeral(this.metaFeedBackEmail);
		this.metaService.criarMetaGeral(this.metaFeedBackTelefone);
		this.metaService.criarMetaGeral(this.metaFeedBackPessoal);
	}

	public MetaGeral getMetaCliente() {
		return this.metaCliente;
	}

	public void setMetaCliente(MetaGeral metaCliente) {
		this.metaCliente = metaCliente;
	}

	public MetaGeral getMetaProduto() {
		return this.metaProduto;
	}

	public void setMetaProduto(MetaGeral metaProduto) {
		this.metaProduto = metaProduto;
	}

	public MetaGeral getMetaServico() {
		return this.metaServico;
	}

	public void setMetaServico(MetaGeral metaServico) {
		this.metaServico = metaServico;
	}

	public MetaGeral getMetaFeedBackEmail() {
		return this.metaFeedBackEmail;
	}

	public void setMetaFeedBackEmail(MetaGeral metaFeedBackEmail) {
		this.metaFeedBackEmail = metaFeedBackEmail;
	}

	public MetaGeral getMetaFeedBackTelefone() {
		return this.metaFeedBackTelefone;
	}

	public void setMetaFeedBackTelefone(MetaGeral metaFeedBackTelefone) {
		this.metaFeedBackTelefone = metaFeedBackTelefone;
	}

	public MetaGeral getMetaFeedBackPessoal() {
		return this.metaFeedBackPessoal;
	}

	public void setMetaFeedBackPessoal(MetaGeral metaFeedBackPessoal) {
		this.metaFeedBackPessoal = metaFeedBackPessoal;
	}

	public MetaService getMetaService() {
		return this.metaService;
	}

	public void setMetaService(MetaService metaService) {
		this.metaService = metaService;
	}

}
