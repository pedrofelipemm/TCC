package br.com.mendes.model.dao.impl;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.mendes.model.Cliente;
import br.com.mendes.model.dao.ClienteDAO;

public class ClienteDAOImplTest extends DAOImplTest {

	@Autowired
	ClienteDAO clienteDAO;

	@Before
	public void setUp() throws Exception {

		for (int i = 0; i < 100; i++) {
			save(new Cliente(null, "Cliente" + i, null, null, null, null, null, null, null, null));
		}
	}

	@Test
	public void getAllTest() {

		List<Cliente> clientes = clienteDAO.getAll();

		Assert.assertEquals(100, clientes.size());
	}
}
