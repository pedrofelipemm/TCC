package br.com.mendes.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mendes.dto.QtdePeriodoDTO;
import br.com.mendes.model.Feedback;
import br.com.mendes.model.TipoAtendimento;
import br.com.mendes.model.dao.FeedbackDAO;
import br.com.mendes.service.FeedbackService;

@Service("feedbackService")
public class FeedbackServiceImpl implements FeedbackService {

	private static final long serialVersionUID = 4908229674807691850L;

	@Autowired
	private FeedbackDAO feedbackDAO;

	@Override
	@Transactional
	public Feedback obterFeedbackPorCod(Long codFeedback) {
		return this.feedbackDAO.getByCod(codFeedback);
	}

	@Override
	@Transactional
	public List<Feedback> obterTodosFeedback() {
		return this.feedbackDAO.getAll();
	}

	@Override
	@Transactional
	public void criarFeedback(Feedback feedback) {
		this.feedbackDAO.saveUpdateGetEntity(feedback);
	}

	@Override
	@Transactional
	public Feedback obterFeedbackPorClienteItem(Long codCliente, Long codItem) {
		return this.feedbackDAO.obterFeedbackPorClienteItem(codCliente, codItem);
	}

	@Override
	@Transactional(readOnly=true)
	public List<QtdePeriodoDTO> obterQtdesFeedbackNosPeriodos(TipoAtendimento tipoAtendimento,
			List<QtdePeriodoDTO> periodos) {

		for(QtdePeriodoDTO periodo : periodos) {
			Long qtde = this.obterQtdeFeedbackNoMesAno(tipoAtendimento,periodo.getAno(), periodo.getMes());
			periodo.setQtde(qtde.doubleValue());
		}

		return periodos;
	}

	private Long obterQtdeFeedbackNoMesAno(TipoAtendimento tipoAtendimento,
			Integer ano, Integer mes) {

		return this.feedbackDAO.obterQtdeFeedbackNoMesAno(tipoAtendimento, ano, mes);
	}
}
