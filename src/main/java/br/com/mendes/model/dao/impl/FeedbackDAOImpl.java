package br.com.mendes.model.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.primefaces.model.SortOrder;
import org.springframework.stereotype.Repository;

import br.com.mendes.model.Feedback;
import br.com.mendes.model.TipoAtendimento;
import br.com.mendes.model.dao.FeedbackDAO;
import br.com.mendes.utils.CONSTANTS;

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

	@Override
	public Long countBy() {
		Criteria criteria = getSession().createCriteria(Feedback.class, "feedback");

		criteria.setProjection(Projections.rowCount());

		return (Long) criteria.uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Feedback> obterTodosFeedbacksPaginados(Integer first, Integer pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {

		Criteria criteria = getSession().createCriteria(Feedback.class, "feedback");
		criteria.createAlias("feedback.cliente", "cliente");
		criteria.createAlias("feedback.item", "item");

		if (filters != null && !filters.isEmpty()) {

			for (Entry<String, String> entry : filters.entrySet()) {

				if (entry.getKey().equals(CONSTANTS.NOTA.getDescricao())) {

					try {
						Double valor = new Double(entry.getValue());
						criteria.add(Restrictions.eq(entry.getKey(), valor));
					} catch (NumberFormatException exception) {
						criteria.add(Restrictions.isNull(entry.getKey()));
					}

				} else if (entry.getKey().equals(CONSTANTS.TIPOATENDIMENTO.getDescricao())) {

					List<Criterion> predicates = new ArrayList<Criterion>();
					List<TipoAtendimento> tipos = TipoAtendimento.getEnums(entry.getValue());

					for (TipoAtendimento tipoAtendimento : tipos) {
						predicates.add(Restrictions.eq(entry.getKey(), tipoAtendimento));
					}

					criteria.add(Restrictions.or(predicates.toArray(new Criterion[0])));

				} else if (entry.getKey().equals(CONSTANTS.CLIENTENOME.getDescricao())) {

					List<Criterion> predicates = new ArrayList<Criterion>();

					predicates.add(Restrictions.ilike(entry.getKey(), entry.getValue() + "%", MatchMode.ANYWHERE));
					predicates.add(Restrictions.ilike(entry.getKey().replaceAll(CONSTANTS.NOME.getDescricao(), CONSTANTS.SOBRENOME.getDescricao()),
							entry.getValue() + "%", MatchMode.ANYWHERE));

					criteria.add(Restrictions.or(predicates.toArray(new Criterion[0])));

				} else {

					criteria.add(Restrictions.ilike(entry.getKey(), entry.getValue() + "%", MatchMode.ANYWHERE));

				}
			}
		}

		if (first != null) {
			criteria.setFirstResult(first);
		}

		if (pageSize != null) {
			criteria.setMaxResults(pageSize);
		}

		if (sortField == null) {
			criteria.addOrder(Order.asc("cliente.nome"));
		} else {
			criteria.addOrder(sortOrder.equals(SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField));
		}

		return criteria.list();
	}

}
