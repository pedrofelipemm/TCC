package br.com.mendes.dao.dataloader;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.mendes.model.Cliente;
import br.com.mendes.model.ItemPedido;
import br.com.mendes.model.Pedido;
import br.com.mendes.model.Produto;
import br.com.mendes.model.StatusPedido;

public abstract class PedidosDataLoader {

	public static List<Pedido> createPedidos(List<? extends Cliente> clientes, List<? extends Produto> produtos) {
		List<Pedido> pedidos = new ArrayList<Pedido>();

		for (int i = 0; i < 3; i++) {
			List<ItemPedido> itensPedido = new ArrayList<ItemPedido>();
			Double valorTotal = 0.0;

			for (int j = 0; j < 3; j++) {
				itensPedido.add(new ItemPedido(produtos.get(j), (j + 1)));
				valorTotal += ((Produto) produtos.get(j)).getPrecoVenda() * (j + 1);
			}

			pedidos.add(new Pedido(clientes.get(i), new Date(), itensPedido, valorTotal, StatusPedido.PAGO));
		}

		return pedidos;
	}

}
