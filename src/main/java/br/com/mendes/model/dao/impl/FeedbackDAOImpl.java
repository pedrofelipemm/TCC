package br.com.mendes.model.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import br.com.mendes.model.Feedback;
import br.com.mendes.model.TipoAtendimento;
import br.com.mendes.model.dao.FeedbackDAO;

@Repository
public class FeedbackDAOImpl extends DAOImpl<Feedback, Long> implements
		FeedbackDAO {

	private static final long serialVersionUID = 373982098438808712L;

	@Override
	public Feedback obterFeedbackPorClienteItem(Long codCliente, Long codItem) {
		
		StringBuilder hql = new StringBuilder(" select fb " +
				" from Feedback fb " +
				" join fb.item item " +
				" join fb.cliente cliente " +
				" where item.cod=:codItem " +
				" and cliente.codCliente=:codCliente ");
		
		Query query = getSession().createQuery(hql.toString());
		
		query.setParameter("codCliente", codCliente);
		query.setParameter("codItem", codItem);
		
		return (Feedback) query.uniqueResult();
	}

	@Override
	public Long obterQtdeFeedbackNoMesAno(TipoAtendimento tipoAtendimento,
			Integer ano, Integer mes) {
		
		StringBuilder hql = new StringBuilder();
		
		hql.append(" select count(f.codFeedback) " +
				   " from  Feedback f 					 " +
				   " where f.tipoAtendimento=:tipoAtendimento " +
				   " and year(f.dataFeedback)=:ano " +
				   " and month(f.dataFeedback)=:mes ");
		
		Query query = getSession().createQuery(hql.toString());
		
		query.setParameter("tipoAtendimento", tipoAtendimento);
		
		query.setParameter("ano", ano);
		
		query.setParameter("mes", mes);
				
		return (Long) query.uniqueResult();
	}

}
