package br.com.mendes.model.dao.impl;

import java.util.Date;
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

	Cliente cliente;

	@Before
	public void setUp() throws Exception {
		this.cliente = new Cliente("Pedro", "Marques", "11.111.111-1", "11111111111", null, new Date());

		save(this.cliente);
	}

	@Test
	public void getAllTest() {
		List<Cliente> clientes = this.clienteDAO.getAll();

		Assert.assertNotNull(clientes);
		Assert.assertFalse(clientes.isEmpty());
	}

}
