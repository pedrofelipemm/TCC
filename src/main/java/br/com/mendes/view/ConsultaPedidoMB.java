package br.com.mendes.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.mendes.model.Cliente;
import br.com.mendes.model.Pedido;
import br.com.mendes.service.ClienteService;
import br.com.mendes.service.PedidoService;

@ManagedBean(name = "consultaPedidoMB")
@ViewScoped
public class ConsultaPedidoMB implements Serializable {

	private static final long serialVersionUID = -7865310445122139826L;

	private List<Pedido> pedidos;

	private List<Cliente> clientes;

	private Long codCliente;

	@ManagedProperty(name = "clienteService", value = "#{clienteService}")
	private ClienteService clienteService;

	@ManagedProperty(name = "pedidoService", value = "#{pedidoService}")
	private PedidoService pedidoService;

	@PostConstruct
	public void iniciar() {

		this.pedidos = this.pedidoService.obterTodosPedidos();

		this.clientes = this.clienteService.obterTodosCliente();

	}

	public ConsultaPedidoMB() {

	}

	public void atualizarDados() {
		this.pedidos = this.pedidoService.obterPedidoPorCliente(this.codCliente);
	}

	public String iniciarEdicao(Long codCliente) {

		this.codCliente = codCliente;

		atualizarDados();

		return "/paginas/consultaPedidos.xhtml";
	}

	public List<Cliente> getClientes() {
		return this.clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Pedido> getPedidos() {
		return this.pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public Long getCodCliente() {
		return this.codCliente;
	}

	public void setCodCliente(Long codCliente) {
		this.codCliente = codCliente;
	}

	public ClienteService getClienteService() {
		return this.clienteService;
	}

	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	public PedidoService getPedidoService() {
		return this.pedidoService;
	}

	public void setPedidoService(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}

}
