package br.com.mendes.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

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

@Scope(value = "session")
@Controller("feedbackMB")
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
	
	

	@Autowired
	private FeedbackService feedbackService;
	
	@Autowired
	private ItemService itemService;
	@Autowired
	private ClienteService clienteService;

	@PostConstruct
	public void iniciar() {

		feedbacks = feedbackService.obterTodosFeedback();
		clientes = clienteService.obterTodosCliente();
					
	}

	public FeedbackMB() {

		resetDados();
		
		tiposItem = Arrays.asList(TipoItem.values());		
		tiposAtendimento = Arrays.asList(TipoAtendimento.values());
		
	}
	
	public String abrirTela() {
		resetDados();
		return "/paginas/cadastroFeedback.xhtml";
	}
	
	
	public void resetDados() {
		
		feedback = new Feedback();
		feedback.setItem(new Item());
		feedback.setCliente(new Cliente());
		
		itens = new ArrayList<Item>();
		
	}

	public void escolherTipoItem() {
		
		itens = itemService.buscarPorTipoECLiente(feedback.getCliente().getCodCliente(), tipoItem);		
	
	}
	
	
	public void salvarFeedback() {
		
		if(feedback.getItem().getCod() == null) {
			MBUtil.addWarn("Nenhum Item foi selecionado.");
			return;
		}
		
		Feedback feedbackAtual = feedbackService.obterFeedbackPorClienteItem(
				feedback.getCliente().getCodCliente(), feedback.getItem().getCod());
		
		if(feedbackAtual != null)
			feedback.setCodFeedback(feedbackAtual.getCodFeedback());
		
		feedbackService.criarFeedback(feedback);
		
		MBUtil.addInfo("Cadastrado com sucesso.");
				
		resetDados();

	}

	public Feedback getFeedback() {
		return feedback;
	}

	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}

	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public String getTipoAtendimento() {
		return tipoAtendimento;
	}

	public void setTipoAtendimento(String tipoAtendimento) {
		this.tipoAtendimento = tipoAtendimento;
	}

	public List<TipoAtendimento> getTiposAtendimento() {
		return tiposAtendimento;
	}

	public void setTiposAtendimento(List<TipoAtendimento> tiposAtendimento) {
		this.tiposAtendimento = tiposAtendimento;
	}

	public TipoItem getTipoItem() {
		return tipoItem;
	}

	public void setTipoItem(TipoItem tipoItem) {
		this.tipoItem = tipoItem;
	}

	public List<TipoItem> getTiposItem() {
		return tiposItem;
	}

	public void setTiposItem(List<TipoItem> tiposItem) {
		this.tiposItem = tiposItem;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

}
