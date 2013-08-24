package br.com.mendes.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.mendes.model.Cliente;
import br.com.mendes.model.Feedback;
import br.com.mendes.model.Item;
import br.com.mendes.model.TipoAtendimento;
import br.com.mendes.model.TipoItem;
import br.com.mendes.service.ClienteService;
import br.com.mendes.service.FeedbackService;
import br.com.mendes.service.ItemService;
import br.com.mendes.utils.MBUtil;

@ManagedBean(name = "feedbackMB")
@ViewScoped
public class FeedbackMB implements Serializable {

	private static final long serialVersionUID = 7948132687109359178L;

	@ManagedProperty(name = "feedbackService", value = "#{feedbackService}")
	private FeedbackService feedbackService;

	@ManagedProperty(name = "clienteService", value = "#{clienteService}")
	private ClienteService clienteService;

	@ManagedProperty(name = "itemService", value = "#{itemService}")
	private ItemService itemService;

	private Feedback feedback;
	private Cliente cliente;
	private Item item;
	private TipoItem tipoItem;

	private List<TipoItem> tiposItem;
	private List<TipoAtendimento> tipoAtendimentos;
	private List<Feedback> feedbacks;
	private List<Cliente> clientes;
	private List<Item> itens;

	@PostConstruct
	public void iniciar() {

		this.cliente = new Cliente();
		this.clientes = this.clienteService.obterTodosCliente();

		this.item = new Item();
		this.tipoAtendimentos = Arrays.asList(TipoAtendimento.values());

		this.tiposItem = Arrays.asList(TipoItem.values());
		this.itens = new ArrayList<Item>();

		resetDados();
	}

	public FeedbackMB() {
	}

	public void resetDados() {
		this.feedback = new Feedback();
		this.feedback.setCliente(this.cliente);
		this.feedback.setItem(this.item);
		this.feedback.setDataFeedback(new Date());
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

	public void escolherTipoItem() {

		this.itens = this.itemService.buscarPorTipoECLiente(this.feedback.getCliente().getCodCliente(), this.tipoItem);

	}

	public FeedbackService getFeedbackService() {
		return this.feedbackService;
	}

	public void setFeedbackService(FeedbackService feedbackService) {
		this.feedbackService = feedbackService;
	}

	public ClienteService getClienteService() {
		return this.clienteService;
	}

	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	public Feedback getFeedback() {
		return this.feedback;
	}

	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public List<TipoAtendimento> getTipoAtendimentos() {
		return this.tipoAtendimentos;
	}

	public void setTipoAtendimentos(List<TipoAtendimento> tipoAtendimentos) {
		this.tipoAtendimentos = tipoAtendimentos;
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

	public ItemService getItemService() {
		return this.itemService;
	}

	public List<Item> getItens() {
		return this.itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
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

}
