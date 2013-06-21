package br.com.mendes.model.dao;

import java.util.List;

import br.com.mendes.model.ItemPedido;
import br.com.mendes.model.Pedido;


public interface ItemPedidoDAO extends DAO<ItemPedido, Long> {

	List<Pedido> obterPedidoPorCliente(Long codCliente);


}
