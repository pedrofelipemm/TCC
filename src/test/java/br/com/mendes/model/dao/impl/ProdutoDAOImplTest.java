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

import br.com.mendes.model.CategoriaProduto;
import br.com.mendes.model.Produto;
import br.com.mendes.model.dao.ProdutoDAO;

public class ProdutoDAOImplTest extends DAOImplTest {

	@Autowired
	ProdutoDAO produtoDAO;

	Produto produto;

	@Before
	public void setUp() throws Exception {
		List<Produto> produtos = new ArrayList<Produto>();

		produtos.add(new Produto(CategoriaProduto.INSTRUMENTO, "Baixo", 1000D, 2000D));
		produtos.add(new Produto(CategoriaProduto.ELETRONICO, "Amplificador", 1000D, 2000D));

		save(produtos.toArray());
	}

	@Test
	public void getAllTest() {
		List<Produto> produtos = this.produtoDAO.getAll();

		Assert.assertNotNull(produtos);
		Assert.assertEquals(2, produtos.size());
	}

	@Test
	public void obterTodosProdutosPaginadosTest() {
		Map<String, String> filters = new HashMap<String, String>();

		filters.put("precoVenda", "2000");
		filters.put("nome", "o");
		filters.put("categoria", "o");

		List<Produto> produtos = this.produtoDAO.obterTodosProdutosPaginados(0, 5, "nome", SortOrder.ASCENDING, filters);

		Assert.assertEquals(2, produtos.size());

		filters.put("custo", "-");

		produtos = this.produtoDAO.obterTodosProdutosPaginados(0, 5, "nome", SortOrder.ASCENDING, filters);

		Assert.assertTrue(produtos.isEmpty());
	}

	@Test
	public void obterTodosProdutosPaginadosApenasPorCategoriaTest() {
		Map<String, String> filters = new HashMap<String, String>();

		filters.put("categoria", "o");

		List<Produto> produtos = this.produtoDAO.obterTodosProdutosPaginados(null, null, null, null, filters);

		Assert.assertEquals(2, produtos.size());

		filters.clear();
		filters.put("categoria", "bbgihgji");

		produtos = this.produtoDAO.obterTodosProdutosPaginados(null, null, null, null, filters);

		Assert.assertTrue(produtos.isEmpty());
	}
}
