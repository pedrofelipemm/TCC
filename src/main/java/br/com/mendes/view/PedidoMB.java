package br.com.mendes.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.mendes.model.Cliente;
import br.com.mendes.model.Item;
import br.com.mendes.model.ItemPedido;
import br.com.mendes.model.Pedido;
import br.com.mendes.model.Produto;
import br.com.mendes.model.Servico;
import br.com.mendes.model.StatusPedido;
import br.com.mendes.model.TipoItem;
import br.com.mendes.service.ClienteService;
import br.com.mendes.service.ItemService;
import br.com.mendes.service.PedidoService;
import br.com.mendes.service.ProdutoService;
import br.com.mendes.service.ServicoService;
import br.com.mendes.utils.MBUtil;

@Scope(value = "session")
@Controller("pedidoMB")
public class PedidoMB implements Serializable {

	private static final long serialVersionUID = -2031182511996672906L;

	private TipoItem tipoItem;
	private Long codCliente;
	private Long codItem;
	private Integer quantidade;
	private Double total;	

	private List<Pedido> pedidos;
	private List<Cliente> clientes;
	private List<Produto> produtos;
	private List<Servico> servicos;
	private List<TipoItem> tiposItem;
	private List<Item> itens;
	private List<ItemPedido> itensPedido;
	

	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private ServicoService servicoService;
	
	@Autowired
	private ItemService itemService;

	@PostConstruct
	public void iniciar() {
		
		pedidos = pedidoService.obterTodosPedidos();
		clientes = clienteService.obterTodosCliente();
		produtos = produtoService.obterTodosProduto();
		servicos = servicoService.obterTodosServicos();
		itens = itemService.buscarTodos(null);
		
		resetDados();
		
	}

	public String abrirTela() {
		resetDados();
		return "/paginas/realizarPedido.xhtml";
	}
	
	public PedidoMB() {
				
		tiposItem = Arrays.asList(TipoItem.values());		
	}
	
	public void resetDados() {
			
		codCliente=null;
		codItem=null;
		tipoItem=null;
		quantidade = 1;
		itensPedido = new ArrayList<ItemPedido>();
		itens = new ArrayList<Item>();		
		total = 0.0;
		
	}

	public void adicionarProduto() {
		
		if(itensPedido==null)
			itensPedido = new ArrayList<ItemPedido>();
		
		if(codItem == null) {
			MBUtil.addWarn("Item não foi selecionado.");
			return;
		}
		
		boolean encontrado = false;
		
		for(ItemPedido ip :itensPedido) {
			
			if(ip.getItem().getCod().equals(codItem)) {
				ip.setQuantidade(quantidade);
				encontrado = true;
				MBUtil.addWarn("Produto já estava na lista, sua quantidade foi alterada.");
			}
			
			
		}
		
		if(!encontrado) {
			
			Item item = itemService.buscarPorCodigo(codItem);
			
			ItemPedido itemPedido = new ItemPedido();
			itemPedido.setItem(item);
			itemPedido.setQuantidade(quantidade);
			itensPedido.add(itemPedido);
		}
		
		calcularTotal();
	}
		
	public Double calcularTotal() {
		
		total = 0.0;
		
		for(ItemPedido ip :itensPedido) {
			total+=ip.getItem().getPrecoVenda() * ip.getQuantidade();
		}
		
		return total;
	}
	
	public void escolherTipoItem() {
		
		itens = itemService.buscarTodos(tipoItem);	
	
	}

		
	public void salvarPedido() {

		Pedido pedido = new Pedido();
		pedido.setStatus(StatusPedido.PAGO);
		pedido.setDataEmissao(new Date());
		pedido.setCliente(new Cliente(codCliente));		
		pedido.setValorTotal(calcularTotal());
		
		pedido = pedidoService.criarPedido(pedido);
		
		for(ItemPedido ip :itensPedido) {
			ip.setPedido(pedido);
			pedidoService.criarAlterarItemPedido(ip);
		}
		
		MBUtil.addInfo("Cadastrado com sucesso.");
		
		resetDados();

	}
	

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setOrcamentos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
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

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public List<ItemPedido> getItensPedido() {
		return itensPedido;
	}

	public void setItensPedido(List<ItemPedido> itensPedido) {
		this.itensPedido = itensPedido;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Long getCodItem() {
		return codItem;
	}

	public void setCodItem(Long codItem) {
		this.codItem = codItem;
	}

	public Long getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(Long codCliente) {
		this.codCliente = codCliente;
	}

}
