package br.com.mendes.service.impl;

import java.util.ArrayList;
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

public class ClienteServiceImplTest extends DAOImplTest{

	@Autowired
	ClienteService clienteService;

	List<Cliente> clientes;
	Cliente cliente;
	Cliente cliente2;

	@Before
	public void setUp() throws Exception {
		Calendar dataCadastro = new GregorianCalendar(2013, 0, 1);

		this.cliente = new Cliente("Felipe", "Marques", "481761482", "38188173835", dataCadastro.getTime());
		this.cliente2 = new Cliente("Pedro", "Marques", "481761485", "11111111111", dataCadastro.getTime());

		this.clientes = new ArrayList<Cliente>();
		this.clientes.add(this.cliente);
		this.clientes.add(this.cliente2);

		save(this.cliente);
		save(this.cliente2);
	}

	@Test
	public void testObterClientePorCod() {
		Cliente cliente = this.clienteService.obterClientePorCod(this.cliente.getCodCliente());

		Assert.assertNotNull(cliente);
		Assert.assertEquals(this.cliente, cliente);
	}

	@Test
	public void testObterTodosCliente() {
		List<Cliente> clientes = this.clienteService.obterTodosCliente();

		Assert.assertNotNull(clientes);
		Assert.assertTrue(!clientes.isEmpty());
		Assert.assertEquals(this.clientes.size(), clientes.size());
	}

	@Test
	public void testObterQtdeClientesNoAnoMes() {
		Long quantidade =  this.clienteService.obterQtdeClientesNoAnoMes(2013, 1);

		Assert.assertEquals(new Long(this.clientes.size()), quantidade);
	}

}
