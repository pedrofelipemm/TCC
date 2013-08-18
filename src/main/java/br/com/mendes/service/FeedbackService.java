package br.com.mendes.service;

import java.io.Serializable;
import java.util.List;

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
}
