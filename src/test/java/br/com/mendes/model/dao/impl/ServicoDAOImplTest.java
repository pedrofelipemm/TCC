package br.com.mendes.model.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.mendes.model.Servico;
import br.com.mendes.model.TipoServico;
import br.com.mendes.model.dao.ServicoDAO;

public class ServicoDAOImplTest extends DAOImplTest {

	@Autowired
	ServicoDAO servicoDAO;

	Servico servico;

	@Before
	public void setUp() throws Exception {
		List<Servico> servicos = new ArrayList<Servico>();

		servicos.add(new Servico("Colagem Expert", 50D, 100D, TipoServico.COLAGEM));
		servicos.add(new Servico("Escapolagem Expert", 50D, 100D, TipoServico.ESCALOPAGEM));

		save(servicos.toArray());
	}

	@Test
	public void getAllTest() {
		List<Servico> servicos = this.servicoDAO.getAll();

		Assert.assertNotNull(servicos);
		Assert.assertFalse(servicos.isEmpty());
	}

	@Test
	public void obterTodosProdutosPaginadosTest() {
		Map<String, String> filters = new HashMap<String, String>();

		filters.put("precoVenda", "100");
		filters.put("nome", "xper");
		filters.put("tipoServico", "age");

		List<Servico> servicos = this.servicoDAO.obterTodosServicosPaginados(0, 5, "nome", SortOrder.ASCENDING, filters);

		Assert.assertEquals(2D, servicos.size(), 0.1);;

		filters.put("custo", "-");

		servicos = this.servicoDAO.obterTodosServicosPaginados(0, 5, "nome", SortOrder.ASCENDING, filters);

		Assert.assertTrue(servicos.isEmpty());

	}

}
