package br.com.mendes.model.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.primefaces.model.SortOrder;
import org.springframework.stereotype.Repository;

import br.com.mendes.model.Servico;
import br.com.mendes.model.TipoServico;
import br.com.mendes.model.dao.ServicoDAO;
import br.com.mendes.utils.CONSTANTS;

@Repository
public class ServicoDAOImpl extends DAOImpl<Servico, Long> implements ServicoDAO {

	private static final long serialVersionUID = 2409967233524119615L;

	@Override
	@SuppressWarnings("unchecked")
	public List<Servico> getAllSorted() {
		Criteria criteria = getSession().createCriteria(Servico.class);

		criteria.addOrder(Order.asc("nome"));

		return criteria.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Servico> obterTodosServicosPaginados(Integer first, Integer pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {

		Criteria criteria = getSession().createCriteria(Servico.class, "servico");

		if (filters != null && !filters.isEmpty()) {

			for (Entry<String, String> entry : filters.entrySet()) {

				if (entry.getKey().equals(CONSTANTS.CUSTO.getDescricao()) || entry.getKey().equals(CONSTANTS.PRECOVENDA.getDescricao())) {

					try {
						Double valor = new Double(entry.getValue());
						criteria.add(Restrictions.eq(entry.getKey(), valor));
					} catch (NumberFormatException exception) {
						criteria.add(Restrictions.isNull(entry.getKey()));
					}

				} else if (entry.getKey().equals(CONSTANTS.TIPOSERVICO.getDescricao())) {

					List<Criterion> predicates = new ArrayList<Criterion>();
					List<TipoServico> tipos = TipoServico.getEnums(entry.getValue());

					for (TipoServico tipoServico : tipos) {
						predicates.add(Restrictions.eq(entry.getKey(), tipoServico));
					}

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
			criteria.addOrder(Order.asc("nome"));
		} else {
			criteria.addOrder(sortOrder.equals(SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField));
		}

		return criteria.list();
	}

}
