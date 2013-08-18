package br.com.mendes.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.mendes.model.Cliente;
import br.com.mendes.model.Feedback;
import br.com.mendes.model.Item;
import br.com.mendes.model.Produto;
import br.com.mendes.model.Servico;
import br.com.mendes.model.TipoAtendimento;
import br.com.mendes.model.TipoItem;
import br.com.mendes.service.ClienteService;
import br.com.mendes.service.FeedbackService;
import br.com.mendes.service.ItemService;
import br.com.mendes.utils.MBUtil;

@ManagedBean(name = "feedbackMB")
@ViewScoped
public class FeedbackMB implements Serializable {

	private static final long serialVersionUID = -4486064975269370483L;

	private Feedback feedback;

	private String tipoAtendimento;

	private TipoItem tipoItem;

	private List<Feedback> feedbacks;

	private List<Produto> produtos;

	private List<Servico> servicos;

	private List<Item> itens;

	private List<Cliente> clientes;

	private List<TipoAtendimento> tiposAtendimento;

	private List<TipoItem> tiposItem;

	@ManagedProperty(name = "feedbackService", value = "#{feedbackService}")
	private FeedbackService feedbackService;

	@ManagedProperty(name = "itemService", value = "#{itemService}")
	private ItemService itemService;

	@ManagedProperty(name = "clienteService", value = "#{clienteService}")
	private ClienteService clienteService;

	@PostConstruct
	public void iniciar() {

		this.feedbacks = this.feedbackService.obterTodosFeedback();
		this.clientes = this.clienteService.obterTodosCliente();

	}

	public FeedbackMB() {

		resetDados();

		this.tiposItem = Arrays.asList(TipoItem.values());
		this.tiposAtendimento = Arrays.asList(TipoAtendimento.values());

	}

	public String abrirTela() {
		resetDados();
		return "/paginas/cadastroFeedback.xhtml";
	}

	public void resetDados() {

		this.feedback = new Feedback();
		this.feedback.setItem(new Item());
		this.feedback.setCliente(new Cliente());

		this.itens = new ArrayList<Item>();

	}

	public void escolherTipoItem() {

		this.itens = this.itemService.buscarPorTipoECLiente(this.feedback.getCliente().getCodCliente(), this.tipoItem);

	}

	public void salvarFeedback() {

		if (this.feedback.getItem().getCod() == null) {
			MBUtil.addWarn("Nenhum Item foi selecionado.");
			return;
		}

		Feedback feedbackAtual = this.feedbackService.obterFeedbackPorClienteItem(
				this.feedback.getCliente().getCodCliente(), this.feedback.getItem().getCod());

		if (feedbackAtual != null) {
			this.feedback.setCodFeedback(feedbackAtual.getCodFeedback());
		}

		this.feedbackService.criarFeedback(this.feedback);

		MBUtil.addInfo("Cadastrado com sucesso.");

		resetDados();

	}

	public Feedback getFeedback() {
		return this.feedback;
	}

	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}

	public List<Feedback> getFeedbacks() {
		return this.feedbacks;
	}

	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	public List<Cliente> getClientes() {
		return this.clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public String getTipoAtendimento() {
		return this.tipoAtendimento;
	}

	public void setTipoAtendimento(String tipoAtendimento) {
		this.tipoAtendimento = tipoAtendimento;
	}

	public List<TipoAtendimento> getTiposAtendimento() {
		return this.tiposAtendimento;
	}

	public void setTiposAtendimento(List<TipoAtendimento> tiposAtendimento) {
		this.tiposAtendimento = tiposAtendimento;
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

	public List<Produto> getProdutos() {
		return this.produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Servico> getServicos() {
		return this.servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	public List<Item> getItens() {
		return this.itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
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

	public ClienteService getClienteService() {
		return this.clienteService;
	}

	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

}
