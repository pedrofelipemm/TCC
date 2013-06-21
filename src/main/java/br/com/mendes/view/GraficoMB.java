package br.com.mendes.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.mendes.dto.QtdePeriodoDTO;
import br.com.mendes.model.Item;
import br.com.mendes.model.TipoAtendimento;
import br.com.mendes.model.TipoItem;
import br.com.mendes.model.TipoMetaGeral;
import br.com.mendes.service.ClienteService;
import br.com.mendes.service.FeedbackService;
import br.com.mendes.service.ItemService;
import br.com.mendes.service.MetaService;


@Component("graficoMB")
@Scope(value="request")
public class GraficoMB implements Serializable {

	private static final long serialVersionUID = -2610583250767977035L;

	private CartesianChartModel painelGrafico;
		
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private MetaService metaService;
	
	@Autowired
	private FeedbackService feedbackService;
	
	@Autowired
	private ItemService itemService;
	
	private List<TipoItem> tiposItem;
	
	private List<Item> itens;
	
	private TipoItem tipoItem;
	
	private Long codItem;
	
	private Integer tipoGrafico=1;
	
	private List<TipoAtendimento> tiposAtendimento;
	
	private TipoAtendimento tipoAtendimento;
	
	private Integer qtdePeriodos = 6;
	
	private Integer maxY;
	
	public GraficoMB() {

		painelGrafico = new CartesianChartModel();		
		maxY = 0;	
	}
		
	@PostConstruct
	public void iniciar() {
	}

	public String carregarGraficoCliente() {
		
		criarLinhaQtdeClientes();
		criarLinhaMetaGeral(TipoMetaGeral.CLIENTE);
				
		return "/paginas/graficoClientesConquistados.xhtml";
	}
	
	public String carregarGraficoProdutoServicoGeral() {
		
		switch (tipoGrafico) {
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
				
		return "/paginas/graficoProdutoServicoGeral.xhtml";
	}
		
	private void criarLinhaItemGeral(TipoItem tipoItem, String descricao) {
		
		List<QtdePeriodoDTO> periodos = gerarPeriodos();
		
		periodos = itemService.obterQtdesItensGeralNosPeriodos(tipoItem, periodos);
	
		gerarLinha(descricao, periodos);
		
	}
	
	private void criarLinhaItemGeralValor(TipoItem tipoItem, String descricao) {
		
		List<QtdePeriodoDTO> periodos = gerarPeriodos();
		
		periodos = itemService.obterQtdesItensGeralValorNosPeriodos(tipoItem, periodos);
	
		gerarLinha(descricao, periodos);
		
	}

	public String carregarGraficoFeedback() {
		
		tiposAtendimento = Arrays.asList(TipoAtendimento.values());
		
		if(tipoAtendimento==null)
			tipoAtendimento = TipoAtendimento.EMAIL;
		
		criarLinhaQtdeFeedback(tipoAtendimento);
		
		switch (tipoAtendimento) {
			
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
		
		
		return "/paginas/graficoAtendimentosRealizados.xhtml";
	}

	public String carregarGraficoMetaEspecifica() {
		
		tiposItem = Arrays.asList(TipoItem.values());	
		
		if(tipoItem!=null)
			itens = itemService.buscarTodos(tipoItem);	
		
		if(codItem!=null) {
			criarLinhaQtdeItemEspecifico(codItem);
			criarLinhaMetaEspecifica(codItem);
		} else {
			painelGrafico = null;
		}
		
		return "/paginas/graficoMetasEspecificas.xhtml";
	}	
		
	private void criarLinhaMetaEspecifica(Long codItem) {
		
		List<QtdePeriodoDTO> periodos = gerarPeriodos();
		
		periodos = metaService.obterMetasEspecificasNoPeriodo(codItem, periodos);		
		
		gerarLinha("Metas", periodos);
		
	}

	private void criarLinhaQtdeItemEspecifico(Long codItem) {
		
		List<QtdePeriodoDTO> periodos = gerarPeriodos();
		
		periodos = itemService.obterQtdesItensEspecificosNosPeriodos(codItem, periodos);
	
		gerarLinha("Produto", periodos);
		
	}

	private void criarLinhaQtdeFeedback(TipoAtendimento tipoAtendimento) {
		
		List<QtdePeriodoDTO> periodos = gerarPeriodos();
		
		periodos = feedbackService.obterQtdesFeedbackNosPeriodos(tipoAtendimento, periodos);
	
		gerarLinha("Atendimentos", periodos);
		
	}

	private List<QtdePeriodoDTO> gerarPeriodos() {
		
		List<QtdePeriodoDTO> periodos = new ArrayList<QtdePeriodoDTO>();
		
		Calendar cal = new GregorianCalendar();
		Integer ano = cal.get(Calendar.YEAR);
		Integer mes = cal.get(Calendar.MONTH);
		mes++;
		
		
		for(int i=0; i<qtdePeriodos; i++) {
			
			periodos.add(0,new QtdePeriodoDTO(0.0,mes,ano));
			
			if(mes.equals(1)) {
				mes=12;
				ano--;
			} else {
				mes--;
			}					
		}
		
		return periodos;		
	}


	public Date getDataInicial() {
		
		Calendar cal = new GregorianCalendar();
		
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		
		cal.add(Calendar.MONTH, ((qtdePeriodos-1) *-1));
				
		return cal.getTime();
		
	}
	
	private void criarLinhaQtdeClientes() {
		
		List<QtdePeriodoDTO> periodos = gerarPeriodos();
		
		periodos = clienteService.obterQtdesClientesNosPeriodos(periodos);
	
		gerarLinha("Clientes", periodos);
	}

	private void criarLinhaMetaGeral(TipoMetaGeral tipoMetaGeral) {
		
		List<QtdePeriodoDTO> periodos = gerarPeriodos();
				
		periodos = metaService.obterMetasGeraisNoPeriodo(tipoMetaGeral, periodos);		
		
		gerarLinha("Metas", periodos);		
	}
	
	public CartesianChartModel getLinearModel() {
		return painelGrafico;
	}

	private void gerarLinha(String nomeLinha, List<QtdePeriodoDTO> qtdePeriodo) {
		
		ChartSeries novaLinha = new ChartSeries();
		
		novaLinha.setLabel(nomeLinha);
				
		for(QtdePeriodoDTO dto : qtdePeriodo) {			
			
			if(maxY < dto.getQtde().intValue()) {
				maxY = dto.getQtde().intValue();
			}
			
			String periodo = dto.getMes() + "/" + dto.getAno();
			novaLinha.set(periodo , dto.getQtde().intValue());		
		}
		
		zerarPeriodos(qtdePeriodo);
		
		painelGrafico.addSeries(novaLinha);
	}

	private void zerarPeriodos(List<QtdePeriodoDTO> periodos) {

		for(QtdePeriodoDTO periodo : periodos) {
			periodo.setQtde(0.0);
		}
		
	}


	public Integer getMaxY() {
		return (maxY+6) - ( maxY % 6);
	}

	public void setMaxY(Integer maxY) {
		this.maxY = maxY;
	}


	public Integer getQtdePeriodos() {
		return qtdePeriodos;
	}


	public void setQtdePeriodos(Integer qtdePeriodos) {
		this.qtdePeriodos = qtdePeriodos;
	}

	public List<TipoAtendimento> getTiposAtendimento() {
		return tiposAtendimento;
	}

	public void setTiposAtendimento(List<TipoAtendimento> tiposAtendimento) {
		this.tiposAtendimento = tiposAtendimento;
	}

	public TipoAtendimento getTipoAtendimento() {
		return tipoAtendimento;
	}

	public void setTipoAtendimento(TipoAtendimento tipoAtendimento) {
		this.tipoAtendimento = tipoAtendimento;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public List<TipoItem> getTiposItem() {
		return tiposItem;
	}

	public void setTiposItem(List<TipoItem> tiposItem) {
		this.tiposItem = tiposItem;
	}

	public TipoItem getTipoItem() {
		return tipoItem;
	}

	public void setTipoItem(TipoItem tipoItem) {
		this.tipoItem = tipoItem;
	}

	public Long getCodItem() {
		return codItem;
	}

	public void setCodItem(Long codItem) {
		this.codItem = codItem;
	}

	public Integer getTipoGrafico() {
		return tipoGrafico;
	}

	public void setTipoGrafico(Integer tipoGrafico) {
		this.tipoGrafico = tipoGrafico;
	}
	

	
}
