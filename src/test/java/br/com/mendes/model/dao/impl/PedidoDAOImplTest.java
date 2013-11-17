package br.com.mendes.model.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.mendes.model.Cliente;
import br.com.mendes.model.ItemPedido;
import br.com.mendes.model.Pedido;
import br.com.mendes.model.StatusPedido;
import br.com.mendes.model.dao.PedidoDAO;
import br.com.mendes.utils.CONSTANTS;

public class PedidoDAOImplTest extends DAOImplTest {

	@Autowired
	PedidoDAO pedidoDAO;

	Calendar data;

	@Before
	public void setUp() throws Exception {
		List<Pedido> pedidos = new ArrayList<Pedido>();

		this.data = Calendar.getInstance();
		this.data.set(2013, 11, 01);

		Cliente cliente = new Cliente("Pedro", "Marques", "11.111.111-1", "11111111111", null, new Date());

		pedidos.add(new Pedido(cliente, this.data.getTime(), new ArrayList<ItemPedido>(), 10000D, StatusPedido.PAGO));
		pedidos.add(new Pedido(cliente, this.data.getTime(), new ArrayList<ItemPedido>(), 10000D, StatusPedido.ABERTO));

		save(cliente);
		save(pedidos.toArray());
	}

	@Test
	public void getAllTest() {
		List<Pedido> pedidos = this.pedidoDAO.getAll();

		Assert.assertNotNull(pedidos);
		Assert.assertFalse(pedidos.isEmpty());
	}

	@Test
	public void obterTodosProdutosPaginadosTest() {
		Map<String, String> filters = new HashMap<String, String>();

		filters.put(CONSTANTS.CLIENTENOME.getDescricao(), "edr");
		filters.put(CONSTANTS.DATAEMISSAO.getDescricao(), "01/11/2013");
		filters.put(CONSTANTS.VALORTOTAL.getDescricao(), "10000");

		List<Pedido> pedidos = this.pedidoDAO.obterTodosPedidosPaginados(0, 5, CONSTANTS.CLIENTENOME.getDescricao(), SortOrder.ASCENDING, filters);

		Assert.assertEquals(2D, pedidos.size(), 0.1);

		filters.put(CONSTANTS.DATAEMISSAO.getDescricao(), "02/11/2013");

		pedidos = this.pedidoDAO.obterTodosPedidosPaginados(0, 5, CONSTANTS.CLIENTENOME.getDescricao(), SortOrder.ASCENDING, filters);

		Assert.assertTrue(pedidos.isEmpty());

	}

}
