package br.com.mendes.view;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import br.com.mendes.model.Meta;
import br.com.mendes.model.Servico;
import br.com.mendes.model.TipoServico;
import br.com.mendes.service.MetaService;
import br.com.mendes.service.ServicoService;
import br.com.mendes.utils.MBUtil;
import br.com.mendes.view.lazy.ServicosLazyDataModel;

@ManagedBean(name = "servicoMB")
@ViewScoped
public class ServicoMB implements Serializable {

	private static final long serialVersionUID = 7157226923907682989L;

	private Servico servico;

	private List<Servico> servicos;

	private ServicosLazyDataModel servicosLazyDataModel;

	private List<TipoServico> tiposServico;

	private Double valorMeta;

	@ManagedProperty(name = "servicoService", value = "#{servicoService}")
	private ServicoService servicoService;

	@ManagedProperty(name = "metaService", value = "#{metaService}")
	private MetaService metaService;

	@PostConstruct
	public void iniciar() {

		this.servicos = this.servicoService.obterTodosServicos();
		this.servicosLazyDataModel = new ServicosLazyDataModel(this.servicoService);
		this.tiposServico = Arrays.asList(TipoServico.values());

		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();

		if (flash.isEmpty()) {
			resetDados();
		} else {
			this.servico = (Servico) flash.get("servico");
			this.valorMeta = (Double) flash.get("valorMeta");
		}
	}

	public void resetDados() {
		this.servico = new Servico();
		this.valorMeta = null;
	}

	public String iniciarEdicao(Long cod) {

		this.servico = this.servicoService.obterServicoPorCod(cod);

		Meta meta = this.metaService.obterMetaEspecificaAtual(cod);

		if (meta != null) {
			this.valorMeta = meta.getValor();
		}

		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();

		flash.put("servico", this.servico);
		flash.put("valorMeta", this.valorMeta);

		return "/paginas/cadastroServico.xhtml";
	}

	public void pesquisa() {
		this.servicosLazyDataModel.setRowIndex(-1);
	}

	public void salvarServico() {

		this.servicoService.criarServico(this.servico);

		if ((this.valorMeta != null) && (this.valorMeta > 0)) {
			this.metaService.criarMetaEspecifica(this.valorMeta, this.servico);
		}

		MBUtil.addInfo("Cadastrado com sucesso.");

		resetDados();

	}

	public Servico getServico() {
		return this.servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public List<Servico> getServicos() {
		return this.servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	public List<TipoServico> getTiposServico() {
		return this.tiposServico;
	}

	public void setTiposServico(List<TipoServico> tiposServico) {
		this.tiposServico = tiposServico;
	}

	public Double getValorMeta() {
		return this.valorMeta;
	}

	public void setValorMeta(Double valorMeta) {
		this.valorMeta = valorMeta;
	}

	public ServicoService getServicoService() {
		return this.servicoService;
	}

	public void setServicoService(ServicoService servicoService) {
		this.servicoService = servicoService;
	}

	public MetaService getMetaService() {
		return this.metaService;
	}

	public void setMetaService(MetaService metaService) {
		this.metaService = metaService;
	}

	public ServicosLazyDataModel getServicosLazyDataModel() {
		return this.servicosLazyDataModel;
	}

	public void setServicosLazyDataModel(ServicosLazyDataModel servicosLazyDataModel) {
		this.servicosLazyDataModel = servicosLazyDataModel;
	}

}
