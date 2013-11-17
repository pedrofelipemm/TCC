package br.com.mendes.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;

import br.com.mendes.dto.QtdePeriodoDTO;
import br.com.mendes.model.Feedback;
import br.com.mendes.model.TipoAtendimento;

public interface FeedbackService extends Serializable {

	public abstract Feedback obterFeedbackPorCod(Long codFeedback);

	public abstract List<Feedback> obterTodosFeedback();

	public abstract void criarFeedback(Feedback feedback);

	public abstract Feedback obterFeedbackPorClienteItem(Long codCliente, Long codItem);

	public abstract List<QtdePeriodoDTO> obterQtdesFeedbackNosPeriodos(
			TipoAtendimento tipoAtendimento, List<QtdePeriodoDTO> periodos);

	public abstract List<Feedback> obterTodosFeedbacksPaginados(Integer first, Integer pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters);

	public abstract Long countBy();

	public abstract void criarAtualizarFeedback(Feedback feedback);
}
