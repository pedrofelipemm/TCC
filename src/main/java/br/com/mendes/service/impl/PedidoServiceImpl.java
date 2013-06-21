package br.com.mendes.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mendes.model.ItemPedido;
import br.com.mendes.model.Pedido;
import br.com.mendes.model.dao.ItemPedidoDAO;
import br.com.mendes.model.dao.PedidoDAO;
import br.com.mendes.service.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService {

	@Autowired
	private PedidoDAO pedidoDAO;
	
	@Autowired
	private ItemPedidoDAO itemPedidoDAO;
	
	
	@Override
	@Transactional
	public Pedido obterPedidoPorCod(Long codPedido) {
		return pedidoDAO.getByCod(codPedido);
	}

	@Override
	@Transactional
	public List<Pedido> obterTodosPedidos() {
		return pedidoDAO.getAll();
	}

	@Override
	@Transactional
	public Pedido criarPedido(Pedido pedido) {
		return pedidoDAO.saveUpdateGetEntity(pedido);
	}
	
	@Transactional
	@Override
	public ItemPedido criarAlterarItemPedido(ItemPedido itemPedido) {
		return itemPedidoDAO.saveUpdateGetEntity(itemPedido);
	}

	@Override
	@Transactional
	public List<Pedido> obterPedidoPorCliente(Long codCliente) {
		return itemPedidoDAO.obterPedidoPorCliente(codCliente);
	}
}
