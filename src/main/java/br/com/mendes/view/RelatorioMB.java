package br.com.mendes.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.mendes.dto.ItemValorDTO;
import br.com.mendes.dto.QtdePeriodoDTO;
import br.com.mendes.model.Item;
import br.com.mendes.model.TipoAtendimento;
import br.com.mendes.model.TipoItem;
import br.com.mendes.model.TipoMetaGeral;
import br.com.mendes.service.ClienteService;
import br.com.mendes.service.FeedbackService;
import br.com.mendes.service.ItemService;
import br.com.mendes.service.MetaService;

@ManagedBean(name = "relatorioMB")
@ViewScoped
public class RelatorioMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1486321293149119534L;

	@ManagedProperty(name = "clienteService", value = "#{clienteService}")
	private ClienteService clienteService;

	@ManagedProperty(name = "metaService", value = "#{metaService}")
	private MetaService metaService;

	@ManagedProperty(name = "feedbackService", value = "#{feedbackService}")
	private FeedbackService feedbackService;

	@ManagedProperty(name = "itemService", value = "#{itemService}")
	private ItemService itemService;

	private List<Integer> clientesConquistados;
	private List<Integer> atendimentosRealizados;
	private List<Integer> produtosVendidos;
	private List<Integer> servicosPrestados;
	private List<Double> receitaProdutos;
	private List<Double> receitaServicos;
	private List<ItemValorDTO> produtoDTO;
	private List<ItemValorDTO> servicoDTO;

	public RelatorioMB() {
	}

	@PostConstruct
	public void init() {
		montaClientesConquistados();
		montaMetaClientesConquistados();

		montaAtendimentosRealizados();
		montaMetaAtendimentosRealizados();

		montaProdutosVendidos();
		montaServicosPrestados();

		montaReceitaProdutos();
		montaReceitaServicos();

		montaQuantidadeDeProdutosEspecificos();
		montaQuantidadeDeServicosEspecificos();

	}

	private void montaQuantidadeDeProdutosEspecificos() {
		List<QtdePeriodoDTO> periodos = gerarPeriodos(12);
		List<Item> produtos = this.itemService.buscarTodos(TipoItem.PRODUTO);
		List<Integer> lista = new ArrayList<Integer>();
		this.produtoDTO = new ArrayList<ItemValorDTO>();

		for (Item item : produtos) {
			periodos = this.itemService.obterQtdesItensEspecificosNosPeriodos(item.getCod(), periodos);
			lista = geraValores(lista, periodos);

			this.produtoDTO.add(new ItemValorDTO(item.getNome(), lista,
					item.getMetas().get(item.getMetas().size() - 1).getValor().intValue()));
		}
	}

	private void montaQuantidadeDeServicosEspecificos() {
		List<QtdePeriodoDTO> periodos = gerarPeriodos(12);
		List<Item> servicos = this.itemService.buscarTodos(TipoItem.SERVICO);
		List<Integer> lista = new ArrayList<Integer>();
		this.servicoDTO = new ArrayList<ItemValorDTO>();

		for (Item item : servicos) {
			periodos = this.itemService.obterQtdesItensEspecificosNosPeriodos(item.getCod(), periodos);
			lista = geraValores(lista, periodos);

			this.servicoDTO.add(new ItemValorDTO(item.getNome(), lista,
					item.getMetas().get(item.getMetas().size() - 1).getValor().intValue()));
		}
	}

	private void montaReceitaProdutos() {
		List<QtdePeriodoDTO> periodos = gerarPeriodos(12);
		periodos = this.itemService.obterQtdesItensGeralValorNosPeriodos(TipoItem.PRODUTO, periodos);

		this.receitaProdutos = geraValoresDecimais(this.receitaProdutos, periodos);

	}

	private void montaReceitaServicos() {
		List<QtdePeriodoDTO> periodos = gerarPeriodos(12);
		periodos = this.itemService.obterQtdesItensGeralValorNosPeriodos(TipoItem.SERVICO, periodos);

		this.receitaServicos = geraValoresDecimais(this.receitaServicos, periodos);

	}

	private void montaServicosPrestados() {
		List<QtdePeriodoDTO> periodos = gerarPeriodos(12);
		periodos = this.itemService.obterQtdesItensGeralNosPeriodos(TipoItem.SERVICO, periodos);

		this.servicosPrestados = geraValores(this.servicosPrestados, periodos);
	}

	private void montaProdutosVendidos() {
		List<QtdePeriodoDTO> periodos = gerarPeriodos(12);
		periodos = this.itemService.obterQtdesItensGeralNosPeriodos(TipoItem.PRODUTO, periodos);

		this.produtosVendidos = geraValores(this.produtosVendidos, periodos);
	}

	private void montaClientesConquistados() {
		List<QtdePeriodoDTO> periodos = gerarPeriodos(12);
		periodos = this.clienteService.obterQtdesClientesNosPeriodos(periodos);

		this.clientesConquistados = geraValores(this.clientesConquistados, periodos);
	}

	private List<Integer> geraMetaGeral(List<Integer> lista, List<QtdePeriodoDTO> periodos) {
		Integer removed;

		carregaLista(lista);

		for (int i = 0; i <= 11; i++) {
			if (periodos.get(i).getQtde() != null && periodos.get(i).getQtde() != 0.0) {
				if (lista.get(3) != null) {
					removed = lista.remove(3);
				}
				removed = periodos.get(i).getQtde().intValue();
				lista.add(3, removed);
			}
		}
		return lista;
	}

	private void montaAtendimentosRealizados() {
		montaAtendimentosRealizadosEmail();
		montaAtendimentosRealizadosPessoalmente();
		montaAtendimentosRealizadosTelefone();
	}

	private void montaMetaAtendimentosRealizados() {
		montaMetaAtendimentosRealizadosEmail();
		montaMetaAtendimentosRealizadosPessoalmente();
		montaMetaAtendimentosRealizadosTelefone();
	}

	private void montaAtendimentosRealizadosEmail() {
		List<QtdePeriodoDTO> periodos = gerarPeriodos(12);
		periodos = this.feedbackService.obterQtdesFeedbackNosPeriodos(TipoAtendimento.EMAIL, periodos);

		this.atendimentosRealizados = geraValores(this.atendimentosRealizados, periodos);
	}

	private void montaAtendimentosRealizadosPessoalmente() {
		List<QtdePeriodoDTO> periodos = gerarPeriodos(12);
		periodos = this.feedbackService.obterQtdesFeedbackNosPeriodos(TipoAtendimento.PESSOAL, periodos);

		List<Integer> temp = geraValores(new ArrayList<Integer>(), periodos);
		for (int i = 4; i <= 6; i++) {
			this.atendimentosRealizados.add(i, temp.remove(0));
		}
	}

	private void montaAtendimentosRealizadosTelefone() {
		List<QtdePeriodoDTO> periodos = gerarPeriodos(12);
		periodos = this.feedbackService.obterQtdesFeedbackNosPeriodos(TipoAtendimento.TELEFONE, periodos);

		List<Integer> temp = geraValores(new ArrayList<Integer>(), periodos);
		for (int i = 8; i <= 10; i++) {
			this.atendimentosRealizados.add(i, temp.remove(0));
		}
	}

	private void montaMetaAtendimentosRealizadosEmail() {
		List<QtdePeriodoDTO> periodos = gerarPeriodos(12);
		periodos = this.metaService.obterMetasGeraisNoPeriodo(TipoMetaGeral.FEEDBACK_EMAIL, periodos);

		this.atendimentosRealizados = geraMetaGeral(this.atendimentosRealizados, periodos);
	}

	private void montaMetaAtendimentosRealizadosPessoalmente() {
		List<QtdePeriodoDTO> periodos = gerarPeriodos(12);
		periodos = this.metaService.obterMetasGeraisNoPeriodo(TipoMetaGeral.FEEDBACK_PESSOAL, periodos);

		List<Integer> temp = geraMetaGeral(new ArrayList<Integer>(), periodos);
		this.atendimentosRealizados.add(7, temp.get(3));
	}

	private void montaMetaAtendimentosRealizadosTelefone() {
		List<QtdePeriodoDTO> periodos = gerarPeriodos(12);
		periodos = this.metaService.obterMetasGeraisNoPeriodo(TipoMetaGeral.FEEDBACK_TELEFONE, periodos);

		List<Integer> temp = geraMetaGeral(new ArrayList<Integer>(), periodos);
		this.atendimentosRealizados.add(11, temp.get(3));
	}

	private List<Integer> geraValores(List<Integer> lista, List<QtdePeriodoDTO> periodos) {
		lista = new ArrayList<Integer>();
		Integer removed;

		carregaLista(lista);

		for (int i = 0; i <= 11; i++) {
			if (i > 8) {
				removed = lista.remove(0);
				removed += periodos.get(i).getQtde().intValue();
				lista.add(0, removed);
			}

			if (i > 5) {
				removed = lista.remove(1);
				removed += periodos.get(i).getQtde().intValue();
				lista.add(1, removed);
			}

			removed = lista.remove(2);
			removed += periodos.get(i).getQtde().intValue();
			lista.add(2, removed);

		}
		return lista;
	}

	private List<Double> geraValoresDecimais(List<Double> lista, List<QtdePeriodoDTO> periodos) {
		lista = new ArrayList<Double>();
		Double removed;

		carregaListaDecimal(lista);

		for (int i = 0; i <= 11; i++) {
			if (i > 8) {
				removed = lista.remove(0);
				removed += periodos.get(i).getQtde().intValue();
				lista.add(0, removed);
			}

			if (i > 5) {
				removed = lista.remove(1);
				removed += periodos.get(i).getQtde().intValue();
				lista.add(1, removed);
			}

			removed = lista.remove(2);
			removed += periodos.get(i).getQtde().intValue();
			lista.add(2, removed);

		}
		return lista;
	}

	private void carregaLista(List<Integer> lista) {
		for (int i = 0; i < 11; i++) {
			if (lista.size() < 12) {
				lista.add(0);
			}
		}
	}

	private void carregaListaDecimal(List<Double> lista) {
		for (int i = 0; i < 11; i++) {
			if (lista.size() < 12) {
				lista.add(0.0);
			}
		}
	}

	private void montaMetaClientesConquistados() {
		List<QtdePeriodoDTO> periodos = gerarPeriodos(12);
		periodos = this.metaService.obterMetasGeraisNoPeriodo(TipoMetaGeral.CLIENTE, periodos);

		this.clientesConquistados = geraMetaGeral(this.clientesConquistados, periodos);
	}

	private List<QtdePeriodoDTO> gerarPeriodos(Integer qtdePeriodos) {

		List<QtdePeriodoDTO> periodos = new ArrayList<QtdePeriodoDTO>();

		Calendar cal = new GregorianCalendar();
		Integer ano = cal.get(Calendar.YEAR);
		Integer mes = cal.get(Calendar.MONTH);
		mes++;

		for (int i = 0; i < qtdePeriodos; i++) {

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

	public List<Integer> getClientesConquistados() {
		return this.clientesConquistados;
	}

	public void setClientesConquistados(List<Integer> clientesConquistados) {
		this.clientesConquistados = clientesConquistados;
	}

	public List<Integer> getAtendimentosRealizados() {
		return this.atendimentosRealizados;
	}

	public void setAtendimentosRealizados(List<Integer> atendimentosRealizados) {
		this.atendimentosRealizados = atendimentosRealizados;
	}

	public List<Integer> getProdutosServicosVendidos() {
		return this.produtosVendidos;
	}

	public void setProdutosServicosVendidos(List<Integer> produtosServicosVendidos) {
		this.produtosVendidos = produtosServicosVendidos;
	}

	public List<Double> getReceitaProdutosServicos() {
		return this.receitaProdutos;
	}

	public void setReceitaProdutosServicos(List<Double> receitaProdutosServicos) {
		this.receitaProdutos = receitaProdutosServicos;
	}

	public List<Integer> getProdutosVendidos() {
		return this.produtosVendidos;
	}

	public void setProdutosVendidos(List<Integer> produtosVendidos) {
		this.produtosVendidos = produtosVendidos;
	}

	public List<Integer> getServicosPrestados() {
		return this.servicosPrestados;
	}

	public void setServicosPrestados(List<Integer> servicosPrestados) {
		this.servicosPrestados = servicosPrestados;
	}

	public List<Double> getReceitaProdutos() {
		return this.receitaProdutos;
	}

	public void setReceitaProdutos(List<Double> receitaProdutos) {
		this.receitaProdutos = receitaProdutos;
	}

	public List<Double> getReceitaServicos() {
		return this.receitaServicos;
	}

	public void setReceitaServicos(List<Double> receitaServicos) {
		this.receitaServicos = receitaServicos;
	}

	public List<ItemValorDTO> getProdutoDTO() {
		return this.produtoDTO;
	}

	public void setProdutoDTO(List<ItemValorDTO> produtoDTO) {
		this.produtoDTO = produtoDTO;
	}

	public List<ItemValorDTO> getServicoDTO() {
		return this.servicoDTO;
	}

	public void setServicoDTO(List<ItemValorDTO> servicoDTO) {
		this.servicoDTO = servicoDTO;
	}

}
