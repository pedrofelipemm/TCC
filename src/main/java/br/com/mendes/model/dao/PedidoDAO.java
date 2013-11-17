package br.com.mendes.model.dao;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;

import br.com.mendes.model.Pedido;

public interface PedidoDAO extends DAO<Pedido, Long> {

	List<Pedido> getAllSorted();

	List<Pedido> obterTodosPedidosPaginados(Integer first, Integer pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters);

}
