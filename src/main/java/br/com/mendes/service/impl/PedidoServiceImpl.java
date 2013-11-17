package br.com.mendes.service.impl;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mendes.model.ItemPedido;
import br.com.mendes.model.Pedido;
import br.com.mendes.model.dao.ItemPedidoDAO;
import br.com.mendes.model.dao.PedidoDAO;
import br.com.mendes.service.PedidoService;

@Service("pedidoService")
public class PedidoServiceImpl implements PedidoService {

	@Autowired
	private PedidoDAO pedidoDAO;

	@Autowired
	private ItemPedidoDAO itemPedidoDAO;

	@Override
	@Transactional
	public Pedido obterPedidoPorCod(Long codPedido) {
		return this.pedidoDAO.getByCod(codPedido);
	}

	@Override
	@Transactional
	public List<Pedido> obterTodosPedidos() {
		return this.pedidoDAO.getAllSorted();
	}

	@Override
	@Transactional
	public void criarPedido(Pedido pedido) {
		this.pedidoDAO.saveUpdateGetEntity(pedido);
	}

	@Transactional
	@Override
	public void criarAlterarItemPedido(ItemPedido itemPedido) {
		this.itemPedidoDAO.saveUpdateGetEntity(itemPedido);
	}

	@Override
	@Transactional
	public List<Pedido> obterPedidoPorCliente(Long codCliente) {
		return this.itemPedidoDAO.obterPedidoPorCliente(codCliente);
	}

	@Override
	public List<Pedido> obterTodosPedidosPaginados(Integer first, Integer pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		return this.pedidoDAO.obterTodosPedidosPaginados(first, pageSize, sortField, sortOrder, filters);
	}

	@Override
	public Long countBy() {
		return this.pedidoDAO.countBy();
	}
}
