package br.com.mendes.model.dao;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;

import br.com.mendes.model.Feedback;
import br.com.mendes.model.TipoAtendimento;

public interface FeedbackDAO extends DAO<Feedback, Long> {

	Feedback obterFeedbackPorClienteItem(Long codCliente, Long codItem);

	Long obterQtdeFeedbackNoMesAno(TipoAtendimento tipoAtendimento,
			Integer ano, Integer mes);

	List<Feedback> obterTodosFeedbacksPaginados(Integer first, Integer pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters);

}
