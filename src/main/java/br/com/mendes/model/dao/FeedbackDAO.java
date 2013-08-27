package br.com.mendes.model.dao;

import java.util.List;

import br.com.mendes.model.Feedback;
import br.com.mendes.model.TipoAtendimento;

public interface FeedbackDAO extends DAO<Feedback, Long> {

	Feedback obterFeedbackPorClienteItem(Long codCliente, Long codItem);

	Long obterQtdeFeedbackNoMesAno(TipoAtendimento tipoAtendimento,
			Integer ano, Integer mes);

	List<Feedback> obterTodosFeedbacksPaginados(String filter, Integer first, Integer pageSize);

	Long countBy(String filter);

}
