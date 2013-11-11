package br.com.mendes.dao.dataloader;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.mendes.model.Feedback;
import br.com.mendes.model.ItemPedido;
import br.com.mendes.model.Pedido;
import br.com.mendes.model.TipoAtendimento;

public class FeedbackDataLoader {

	public static List<Feedback> createFeedbacks(List<Pedido> pedidos) {
		List<Feedback> feedbacks = new ArrayList<Feedback>();
		TipoAtendimento[] tipos = TipoAtendimento.values();
		Double nota;
		Random random = new Random();

		for (Pedido pedido : pedidos) {
			for (ItemPedido item : pedido.getItensPedido()) {
				do {
					nota = new Double(new Integer(random.nextInt(11)).toString());
				} while (nota < 5);

				feedbacks.add(new Feedback(pedido.getCliente(), item.getItem(), tipos[random.nextInt(3)],
						nota, "Excelente Produto", pedido.getDataEmissao()));
			}
		}
		return feedbacks;
	}
}
