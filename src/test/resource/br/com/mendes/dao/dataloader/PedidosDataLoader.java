package br.com.mendes.dao.dataloader;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import br.com.mendes.model.Cliente;
import br.com.mendes.model.ItemPedido;
import br.com.mendes.model.Pedido;
import br.com.mendes.model.Produto;
import br.com.mendes.model.Servico;
import br.com.mendes.model.StatusPedido;

public abstract class PedidosDataLoader {

	public static List<Pedido> createPedidos(List<? extends Cliente> clientes, List<Produto> produtos, List<Servico> servicos) {
		List<Pedido> pedidos = new ArrayList<Pedido>();
		List<Date> datas = geraDatas();
		List<ItemPedido> itensPedido = null;

		while (pedidos.size() < 40) {
			itensPedido = geraItensPedido(itensPedido, produtos, servicos);

			pedidos.add(new Pedido(clientes.remove(0), datas.remove(0), itensPedido,
					calcularValorTotal(itensPedido), StatusPedido.PAGO));
		}

		return pedidos;
	}

	private static List<Date> geraDatas() {
		List<Date> lista = new ArrayList<Date>();

		lista.add(new GregorianCalendar(2013, 10, 01).getTime());
		lista.add(new GregorianCalendar(2013, 10, 01).getTime());
		lista.add(new GregorianCalendar(2013, 10, 01).getTime());
		lista.add(new GregorianCalendar(2013, 9, 01).getTime());
		lista.add(new GregorianCalendar(2013, 9, 01).getTime());
		lista.add(new GregorianCalendar(2013, 9, 01).getTime());
		lista.add(new GregorianCalendar(2013, 8, 01).getTime());
		lista.add(new GregorianCalendar(2013, 8, 01).getTime());
		lista.add(new GregorianCalendar(2013, 8, 01).getTime());
		lista.add(new GregorianCalendar(2013, 8, 01).getTime());
		lista.add(new GregorianCalendar(2013, 8, 01).getTime());
		lista.add(new GregorianCalendar(2013, 7, 01).getTime());
		lista.add(new GregorianCalendar(2013, 7, 01).getTime());
		lista.add(new GregorianCalendar(2013, 6, 01).getTime());
		lista.add(new GregorianCalendar(2013, 4, 01).getTime());
		lista.add(new GregorianCalendar(2013, 4, 01).getTime());
		lista.add(new GregorianCalendar(2013, 4, 01).getTime());
		lista.add(new GregorianCalendar(2013, 3, 01).getTime());
		lista.add(new GregorianCalendar(2013, 3, 01).getTime());
		lista.add(new GregorianCalendar(2013, 2, 01).getTime());
		lista.add(new GregorianCalendar(2013, 2, 01).getTime());
		lista.add(new GregorianCalendar(2013, 2, 01).getTime());
		lista.add(new GregorianCalendar(2013, 2, 01).getTime());
		lista.add(new GregorianCalendar(2013, 2, 01).getTime());
		lista.add(new GregorianCalendar(2013, 2, 01).getTime());
		lista.add(new GregorianCalendar(2013, 2, 01).getTime());
		lista.add(new GregorianCalendar(2013, 2, 01).getTime());
		lista.add(new GregorianCalendar(2013, 1, 01).getTime());
		lista.add(new GregorianCalendar(2013, 1, 01).getTime());
		lista.add(new GregorianCalendar(2013, 1, 01).getTime());
		lista.add(new GregorianCalendar(2013, 1, 01).getTime());
		lista.add(new GregorianCalendar(2013, 1, 01).getTime());
		lista.add(new GregorianCalendar(2013, 1, 01).getTime());
		lista.add(new GregorianCalendar(2013, 0, 01).getTime());
		lista.add(new GregorianCalendar(2013, 0, 01).getTime());
		lista.add(new GregorianCalendar(2013, 0, 01).getTime());
		lista.add(new GregorianCalendar(2013, 0, 01).getTime());
		lista.add(new GregorianCalendar(2013, 0, 01).getTime());
		lista.add(new GregorianCalendar(2013, 0, 01).getTime());
		lista.add(new GregorianCalendar(2013, 0, 01).getTime());

		return lista;
	}

	private static List<ItemPedido> geraItensPedido(List<ItemPedido> itensPedido, List<Produto> produtos, List<Servico> servicos) {
		Random random = new Random();
		itensPedido = new ArrayList<ItemPedido>();
		int quantidade = 1;
		int x = random.nextInt(6);

		if (x > 0) {
			quantidade = x;
		}

		itensPedido.add(new ItemPedido(produtos.get(x), quantidade));
		while (itensPedido.size() < 3) {
			x = random.nextInt(8);

			if (x > 0 && x < 6) {
				quantidade = x;
			}

			itensPedido.add(new ItemPedido(servicos.get(x), quantidade));
		}

		return itensPedido;
	}

	private static Double calcularValorTotal(List<ItemPedido> itensPedido) {

		Double valorTotal = 0D;
		for (ItemPedido itemPedido : itensPedido) {
			valorTotal += itemPedido.getTotal();
		}

		return valorTotal;
	}

}
