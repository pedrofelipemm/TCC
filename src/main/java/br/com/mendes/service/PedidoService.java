package br.com.mendes.service;

import java.util.List;

import br.com.mendes.model.ItemPedido;
import br.com.mendes.model.Pedido;

public interface PedidoService {

	public abstract Pedido obterPedidoPorCod(Long codPedido);

	public abstract List<Pedido> obterTodosPedidos();

	public abstract void criarPedido(Pedido pedido);

	public abstract void criarAlterarItemPedido(ItemPedido itemPedido);

	public abstract List<Pedido> obterPedidoPorCliente(Long codCliente);
}
