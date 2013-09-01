package br.com.mendes.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.mendes.dto.QtdePeriodoDTO;
import br.com.mendes.model.Item;
import br.com.mendes.model.TipoAtendimento;
import br.com.mendes.model.TipoItem;
import br.com.mendes.model.TipoMetaGeral;
import br.com.mendes.service.ClienteService;
import br.com.mendes.service.FeedbackService;
import br.com.mendes.service.ItemService;
import br.com.mendes.service.MetaService;

@ManagedBean(name = "graficoMB")
@ViewScoped
public class GraficoMB implements Serializable {

	private static final long serialVersionUID = -2610583250767977035L;

	@ManagedProperty(name = "clienteService", value = "#{clienteService}")
	private ClienteService clienteService;

	@ManagedProperty(name = "metaService", value = "#{metaService}")
	private MetaService metaService;

	@ManagedProperty(name = "feedbackService", value = "#{feedbackService}")
	private FeedbackService feedbackService;

	@ManagedProperty(name = "itemService", value = "#{itemService}")
	private ItemService itemService;

	private CartesianChartModel linearModel;

	private TipoAtendimento tipoAtendimento;

	private TipoItem tipoItem;

	private String tipo;

	private List<TipoAtendimento> tiposAtendimento;

	private List<TipoItem> tiposItem;

	private List<Item> itens;

	private Integer qtdePeriodos;
	private Integer maxY;

	private Integer tipoGrafico;

	private Long codItem;

	public GraficoMB() {
	}

	@PostConstruct
	public void iniciar() {
		this.linearModel = new CartesianChartModel();
		this.qtdePeriodos = 3;
		this.maxY = 5;
		this.tipoGrafico = 1;

		switch (FacesContext.getCurrentInstance().getViewRoot().getViewId()) {
			case "/paginas/graficoClientesConquistados.xhtml":
				carregarGraficoCliente();
				break;
			case "/paginas/graficoAtendimentosRealizados.xhtml":
				carregarGraficoFeedback();
				break;
			case "/paginas/graficoMetasEspecificas.xhtml":
				carregarGraficoMetaEspecifica();
				break;
			case "/paginas/graficoProdutoServicoGeral.xhtml":
				carregarGraficoProdutoServicoGeral();
				break;
		}

	}

	public void carregarGraficoFeedback() {
		this.linearModel = new CartesianChartModel();
		this.tiposAtendimento = Arrays.asList(TipoAtendimento.values());

		if (this.tipoAtendimento == null) {
			this.tipoAtendimento = TipoAtendimento.EMAIL;
		}

		criarLinhaQtdeFeedback(this.tipoAtendimento);

		switch (this.tipoAtendimento) {

			case EMAIL:
				criarLinhaMetaGeral(TipoMetaGeral.FEEDBACK_EMAIL);
				break;
			case PESSOAL:
				criarLinhaMetaGeral(TipoMetaGeral.FEEDBACK_PESSOAL);
				break;
			case TELEFONE:
				criarLinhaMetaGeral(TipoMetaGeral.FEEDBACK_TELEFONE);
				break;
		}
	}

	public void limparItem() {
		this.codItem = null;
	}

	public void carregarGraficoMetaEspecifica() {
		this.linearModel = new CartesianChartModel();
		this.tiposItem = Arrays.asList(TipoItem.values());

		if (this.tipoItem == null) {
			this.tipoItem = TipoItem.PRODUTO;
			this.tipo = this.tipoItem.name();
		}

		if (this.tipoItem.name() != this.tipo) {
			this.codItem = null;
			this.tipo = this.tipoItem.name();
		}

		this.itens = this.itemService.buscarTodos(this.tipoItem);

		if (this.codItem == null) {
			this.codItem = this.itemService.buscarTodos(this.tipoItem).get(0).getCod();
		}

		criarLinhaQtdeItemEspecifico(this.codItem);
		criarLinhaMetaEspecifica(this.codItem);
	}

	private void criarLinhaMetaEspecifica(Long codItem) {

		List<QtdePeriodoDTO> periodos = gerarPeriodos();

		periodos = this.metaService.obterMetasEspecificasNoPeriodo(codItem, periodos);

		gerarLinha("Metas", periodos);

	}

	public void carregarGraficoProdutoServicoGeral() {
		this.linearModel = new CartesianChartModel();

		switch (this.tipoGrafico) {
			case 1:
				criarLinhaItemGeral(TipoItem.SERVICO, "Qtde Serviços Prestados");
				criarLinhaItemGeral(TipoItem.PRODUTO, "Qtde Produtos Vendidos");
				break;
			case 2:
				criarLinhaItemGeralValor(TipoItem.SERVICO, "Serviços Prestados");
				criarLinhaItemGeralValor(TipoItem.PRODUTO, "Produtos Vendidos");
				break;
			case 3:
				criarLinhaItemGeral(TipoItem.PRODUTO, "Produtos Vendidos");
				criarLinhaMetaGeral(TipoMetaGeral.PRODUTO);
				break;
			case 4:
				criarLinhaItemGeral(TipoItem.SERVICO, "Serviços Prestados");
				criarLinhaMetaGeral(TipoMetaGeral.SERVICO);
				break;
		}

	}

	private void criarLinhaItemGeral(TipoItem tipoItem, String descricao) {

		List<QtdePeriodoDTO> periodos = gerarPeriodos();

		periodos = this.itemService.obterQtdesItensGeralNosPeriodos(tipoItem, periodos);

		gerarLinha(descricao, periodos);

	}

	private void criarLinhaItemGeralValor(TipoItem tipoItem, String descricao) {

		List<QtdePeriodoDTO> periodos = gerarPeriodos();

		periodos = this.itemService.obterQtdesItensGeralValorNosPeriodos(tipoItem, periodos);

		gerarLinha(descricao, periodos);

	}

	private void criarLinhaQtdeItemEspecifico(Long codItem) {

		List<QtdePeriodoDTO> periodos = gerarPeriodos();

		periodos = this.itemService.obterQtdesItensEspecificosNosPeriodos(codItem, periodos);

		gerarLinha("Produto", periodos);

	}

	private void criarLinhaQtdeFeedback(TipoAtendimento tipoAtendimento) {

		List<QtdePeriodoDTO> periodos = gerarPeriodos();

		periodos = this.feedbackService.obterQtdesFeedbackNosPeriodos(tipoAtendimento, periodos);

		gerarLinha("Atendimentos", periodos);

	}

	public void carregarGraficoCliente() {
		this.linearModel = new CartesianChartModel();
		criarLinhaQtdeClientes();
		criarLinhaMetaGeral(TipoMetaGeral.CLIENTE);
	}

	private void criarLinhaQtdeClientes() {

		List<QtdePeriodoDTO> periodos = gerarPeriodos();

		periodos = this.clienteService.obterQtdesClientesNosPeriodos(periodos);

		gerarLinha("Clientes", periodos);
	}

	private void criarLinhaMetaGeral(TipoMetaGeral tipoMetaGeral) {

		List<QtdePeriodoDTO> periodos = gerarPeriodos();

		periodos = this.metaService.obterMetasGeraisNoPeriodo(tipoMetaGeral, periodos);

		gerarLinha("Metas", periodos);
	}

	private void gerarLinha(String nomeLinha, List<QtdePeriodoDTO> qtdePeriodo) {

		ChartSeries novaLinha = new ChartSeries();

		novaLinha.setLabel(nomeLinha);

		for (QtdePeriodoDTO dto : qtdePeriodo) {

			if (this.maxY < dto.getQtde().intValue()) {
				this.maxY = (int) ((dto.getQtde().intValue()) + (dto.getQtde().intValue() * 0.25));
			}

			String periodo = dto.getMes() + "/" + dto.getAno();
			novaLinha.set(periodo, dto.getQtde().intValue());
		}

		zerarPeriodos(qtdePeriodo);

		this.linearModel.addSeries(novaLinha);
	}

	private void zerarPeriodos(List<QtdePeriodoDTO> periodos) {

		for (QtdePeriodoDTO periodo : periodos) {
			periodo.setQtde(0.0);
		}

	}

	private List<QtdePeriodoDTO> gerarPeriodos() {

		List<QtdePeriodoDTO> periodos = new ArrayList<QtdePeriodoDTO>();

		Calendar cal = new GregorianCalendar();
		Integer ano = cal.get(Calendar.YEAR);
		Integer mes = cal.get(Calendar.MONTH);
		mes++;

		for (int i = 0; i < this.qtdePeriodos; i++) {

			periodos.add(0, new QtdePeriodoDTO(0.0, mes, ano));

			if (mes.equals(1)) {
				mes = 12;
				ano--;
			} else {
				mes--;
			}
		}

		return periodos;
	}

	public ClienteService getClienteService() {
		return this.clienteService;
	}

	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	public MetaService getMetaService() {
		return this.metaService;
	}

	public void setMetaService(MetaService metaService) {
		this.metaService = metaService;
	}

	public FeedbackService getFeedbackService() {
		return this.feedbackService;
	}

	public void setFeedbackService(FeedbackService feedbackService) {
		this.feedbackService = feedbackService;
	}

	public ItemService getItemService() {
		return this.itemService;
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

	public CartesianChartModel getLinearModel() {
		return this.linearModel;
	}

	public void setLinearModel(CartesianChartModel linearModel) {
		this.linearModel = linearModel;
	}

	public Integer getMaxY() {
		return this.maxY;
	}

	public void setMaxY(Integer maxY) {
		this.maxY = maxY;
	}

	public Integer getQtdePeriodos() {
		return this.qtdePeriodos;
	}

	public void setQtdePeriodos(Integer qtdePeriodos) {
		this.qtdePeriodos = qtdePeriodos;
	}

	public List<TipoAtendimento> getTiposAtendimento() {
		return this.tiposAtendimento;
	}

	public void setTiposAtendimento(List<TipoAtendimento> tiposAtendimento) {
		this.tiposAtendimento = tiposAtendimento;
	}

	public TipoAtendimento getTipoAtendimento() {
		return this.tipoAtendimento;
	}

	public void setTipoAtendimento(TipoAtendimento tipoAtendimento) {
		this.tipoAtendimento = tipoAtendimento;
	}

	public TipoItem getTipoItem() {
		return this.tipoItem;
	}

	public void setTipoItem(TipoItem tipoItem) {
		this.tipoItem = tipoItem;
	}

	public List<TipoItem> getTiposItem() {
		return this.tiposItem;
	}

	public void setTiposItem(List<TipoItem> tiposItem) {
		this.tiposItem = tiposItem;
	}

	public List<Item> getItens() {
		return this.itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public Long getCodItem() {
		return this.codItem;
	}

	public void setCodItem(Long codItem) {
		this.codItem = codItem;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getTipoGrafico() {
		return this.tipoGrafico;
	}

	public void setTipoGrafico(Integer tipoGrafico) {
		this.tipoGrafico = tipoGrafico;
	}

}
