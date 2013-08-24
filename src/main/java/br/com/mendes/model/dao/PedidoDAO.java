package br.com.mendes.model.dao;

import java.util.List;

import br.com.mendes.model.Pedido;

public interface PedidoDAO extends DAO<Pedido, Long> {

	List<Pedido> getAllSorted();

}
