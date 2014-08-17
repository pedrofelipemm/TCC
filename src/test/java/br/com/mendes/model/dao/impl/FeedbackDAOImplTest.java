package br.com.mendes.model.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.mendes.model.CategoriaProduto;
import br.com.mendes.model.Cliente;
import br.com.mendes.model.Feedback;
import br.com.mendes.model.Produto;
import br.com.mendes.model.TipoAtendimento;
import br.com.mendes.model.dao.FeedbackDAO;

public class FeedbackDAOImplTest extends DAOImplTest {

	@Autowired
	FeedbackDAO feedbackDAO;

	@Before
	public void setUp() throws Exception {

		Cliente cliente = new Cliente("Pedro", "Marques", "11.111.111-1", "11111111111", null, new Date());
		Produto produto = new Produto(CategoriaProduto.INSTRUMENTO, "Baixo", 1000D, 2000D);

		Feedback feedback1 = new Feedback(cliente, produto, TipoAtendimento.PESSOAL, 10D, "Muito Bom", new Date());
		Feedback feedback2 = new Feedback(cliente, produto, TipoAtendimento.EMAIL, 10D, "Muito Bom", new Date());

		save(cliente, produto, feedback1, feedback2);
	}

	@Test
	public void getAllTest() {

		List<Feedback> feedbacks = this.feedbackDAO.getAll();
		Assert.assertEquals(2, feedbacks.size());
	}

	@Test
	public void obterTodosProdutosPaginadosTest() {
		Map<String, String> filters = new HashMap<String, String>();

		filters.put("nota", "10");
		filters.put("cliente.nome", "o");
		filters.put("tipoAtendimento", "a");

		List<Feedback> feedbacks = this.feedbackDAO.obterTodosFeedbacksPaginados(0, 5, "cliente.nome", SortOrder.ASCENDING, filters);

		Assert.assertEquals(2, feedbacks.size());

		filters.put("nota", "-");

		feedbacks = this.feedbackDAO.obterTodosFeedbacksPaginados(0, 5, "cliente.nome", SortOrder.ASCENDING, filters);

		Assert.assertTrue(feedbacks.isEmpty());
	}
}
