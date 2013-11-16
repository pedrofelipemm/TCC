package br.com.mendes.model.dao.impl;

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
		this.produto = new Produto(CategoriaProduto.INSTRUMENTO, "Baixo", 1000D, 2000D);

		save(this.produto);
	}

	@Test
	public void getAllTest() {
		List<Produto> produtos = this.produtoDAO.getAll();

		Assert.assertNotNull(produtos);
		Assert.assertFalse(produtos.isEmpty());
	}

	@Test
	public void obterTodosProdutosPaginadosTest() {
		Map<String, String> filters = new HashMap<String, String>();

		filters.put("precoVenda", "2000");
		filters.put("nome", "aix");
		filters.put("categoria", "st");

		List<Produto> produtos = this.produtoDAO.obterTodosProdutosPaginados(0, 5, "nome", SortOrder.ASCENDING, filters);

		Assert.assertNotNull(produtos);
		Assert.assertFalse(produtos.isEmpty());

		filters.put("custo", "-");

		produtos = this.produtoDAO.obterTodosProdutosPaginados(0, 5, "nome", SortOrder.ASCENDING, filters);

		Assert.assertTrue(produtos.isEmpty());

	}

}
