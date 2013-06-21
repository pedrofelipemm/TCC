package br.com.mendes.service;

import java.io.Serializable;
import java.util.List;

import br.com.mendes.dto.QtdePeriodoDTO;
import br.com.mendes.model.Feedback;
import br.com.mendes.model.TipoAtendimento;

public interface FeedbackService extends Serializable {

	Feedback obterFeedbackPorCod(Long codFeedback);

	List<Feedback> obterTodosFeedback();

	Feedback criarFeedback(Feedback feedback);

	Feedback obterFeedbackPorClienteItem(Long codCliente, Long codItem);

	List<QtdePeriodoDTO> obterQtdesFeedbackNosPeriodos(
			TipoAtendimento tipoAtendimento, List<QtdePeriodoDTO> periodos);
}
