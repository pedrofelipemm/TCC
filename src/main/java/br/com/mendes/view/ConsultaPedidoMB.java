package br.com.mendes.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.mendes.model.Cliente;
import br.com.mendes.model.Pedido;
import br.com.mendes.service.ClienteService;
import br.com.mendes.service.PedidoService;

@Scope(value="request")
@Controller("consultaPedidoMB")
public class ConsultaPedidoMB implements Serializable{
	
	private static final long serialVersionUID = -7865310445122139826L;

	private List<Pedido> pedidos;
	private List<Cliente> clientes;
	
	private Long codCliente;
	
	
	@Autowired 
	private ClienteService clienteService;
	
	@Autowired
	private PedidoService pedidoService;
	
	@PostConstruct
	public void iniciar() {	
		
		pedidos = pedidoService.obterTodosPedidos();
		
		clientes = clienteService.obterTodosCliente();
				
	}    
	
	public void atualizarDados()  {
		pedidos = pedidoService.obterPedidoPorCliente(codCliente);
	}
	
	public ConsultaPedidoMB() {
		
	}
	
	public String iniciarEdicao(Long codCliente) {
		
		this.codCliente = codCliente;
		
		atualizarDados();
		
		return "/paginas/consultaPedidos.xhtml";
	}
	
	public List<Cliente> getClientes() {
		return clientes;
	}


	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public Long getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(Long codCliente) {
		this.codCliente = codCliente;
	}  
}
