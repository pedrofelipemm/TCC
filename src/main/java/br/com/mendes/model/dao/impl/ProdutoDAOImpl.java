package br.com.mendes.model.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.mendes.model.Produto;
import br.com.mendes.model.dao.ProdutoDAO;

@Repository
public class ProdutoDAOImpl extends DAOImpl<Produto, Long> implements ProdutoDAO {

	private static final long serialVersionUID = 3201421256269456694L;

	@Override
	@SuppressWarnings("unchecked")
	public List<Produto> getAllSorted() {
		Criteria criteria = getSession().createCriteria(Produto.class);

		criteria.addOrder(Order.asc("nome"));

		return criteria.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Produto> obterTodosProdutosPaginados(String filter, Integer first, Integer pageSize) {
		Criteria criteria = getSession().createCriteria(Produto.class);

		if (!StringUtils.isBlank(filter)) {
			criteria.add(Restrictions.ilike("nome", filter + "%", MatchMode.ANYWHERE));
		}

		if (first != null) {
			criteria.setFirstResult(first);
		}

		if (pageSize != null) {
			criteria.setMaxResults(pageSize);
		}

		criteria.addOrder(Order.asc("nome"));

		return criteria.list();
	}

	@Override
	public Long countBy(String filter) {
		Criteria criteria = getSession().createCriteria(Produto.class);

		if (!StringUtils.isBlank(filter)) {
			criteria.add(Restrictions.ilike("nome", filter, MatchMode.ANYWHERE));
		}

		criteria.setProjection(Projections.rowCount());

		return (Long) criteria.uniqueResult();
	}

}
