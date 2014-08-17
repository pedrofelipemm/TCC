package br.com.mendes.service.impl;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.mendes.model.Cliente;
import br.com.mendes.model.dao.impl.DAOImplTest;
import br.com.mendes.service.ClienteService;

public class ClienteServiceImplTest extends DAOImplTest {

	@Autowired
	ClienteService clienteService;

	Cliente cliente;
	Cliente cliente2;

	@Before
	public void setUp() throws Exception {

		Calendar dataCadastro = new GregorianCalendar(2013, 0, 1);

		cliente = new Cliente("Felipe", "Marques", "481761482", "38188173835", null, dataCadastro.getTime());
		cliente2 = new Cliente("Pedro", "Marques", "481761485", "11111111111", null, dataCadastro.getTime());

		save(cliente, cliente2);
	}

	@Test
	public void testObterClientePorCod() {

		Cliente cliente = clienteService.obterClientePorCod(this.cliente.getCodCliente());

		Assert.assertNotNull(cliente);
		Assert.assertEquals(cliente, cliente);
	}

	@Test
	public void testObterTodosCliente() {

		List<Cliente> clientes = clienteService.obterTodosCliente();
		Assert.assertEquals(2, clientes.size());
	}

	@Test
	public void testObterQtdeClientesNoAnoMes() {

		Long quantidade = clienteService.obterQtdeClientesNoAnoMes(2013, 1);

		Assert.assertEquals(Long.valueOf(2), quantidade);
	}

}
