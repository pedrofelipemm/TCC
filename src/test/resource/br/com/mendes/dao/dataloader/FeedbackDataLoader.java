package br.com.mendes.dao.dataloader;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.mendes.model.Cliente;
import br.com.mendes.model.Feedback;
import br.com.mendes.model.Produto;
import br.com.mendes.model.TipoAtendimento;

public class FeedbackDataLoader {

	public static List<Feedback> createFeedbacks(List<? extends Cliente> clientes, List<? extends Produto> produtos) {
		List<Feedback> feedbacks = new ArrayList<Feedback>();

		for (int i = 0; i < 3; i++) {
			feedbacks.add(new Feedback(clientes.get(i), produtos.get(i),
					TipoAtendimento.EMAIL, 10D, "Excelente Produto", new Date()));
		}

		return feedbacks;
	}

}
