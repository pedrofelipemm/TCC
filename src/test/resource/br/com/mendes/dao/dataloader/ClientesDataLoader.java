package br.com.mendes.dao.dataloader;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import br.com.mendes.model.Cliente;

public abstract class ClientesDataLoader {

	public static List<Cliente> createClientes() {
		List<Cliente> clientes = new ArrayList<Cliente>();

		clientes.add(new Cliente("Pedro", "Moreira", "(19) 3671-1234", "(19) 9340-5079", "12.345.678-9", "111.111.111-11",
				new GregorianCalendar(1992, 4, 27).getTime(), new GregorianCalendar(2013, 7, 01).getTime()));

		clientes.add(new Cliente("Jão", "Silva", "(19) 3671-6534", "(19) 9900-1239", "54.356.876-2", "222.222.222-22",
				new GregorianCalendar(1992, 5, 20).getTime(), new GregorianCalendar(2013, 7, 01).getTime()));

		clientes.add(new Cliente("Joana", "Batista", "(19) 3656-2367", "(19) 8906-1479", "34.561.450-9", "115.851.187-61",
				new GregorianCalendar(1990, 3, 17).getTime(), new GregorianCalendar(2013, 7, 01).getTime()));

		return clientes;
	}

}
