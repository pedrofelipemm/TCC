package br.com.mendes.service;

import java.util.List;

import br.com.mendes.model.ItemPedido;
import br.com.mendes.model.Pedido;

public interface PedidoService {

	Pedido obterPedidoPorCod(Long codPedido);

	List<Pedido> obterTodosPedidos();

	Pedido criarPedido(Pedido pedido);

	ItemPedido criarAlterarItemPedido(ItemPedido itemPedido);

	List<Pedido> obterPedidoPorCliente(Long codCliente);
}
