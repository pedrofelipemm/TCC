package br.com.mendes.model.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.mendes.model.Pedido;
import br.com.mendes.model.dao.PedidoDAO;

@Repository
public class PedidoDAOImpl extends DAOImpl<Pedido, Long> implements
		PedidoDAO {

	private static final long serialVersionUID = 9051943387825326329L;

	@Override
	@SuppressWarnings("unchecked")
	public List<Pedido> getAllSorted() {
		Criteria criteria = getSession().createCriteria(Pedido.class, "pedido");

		criteria.createAlias("pedido.cliente", "cliente");

		criteria.addOrder(Order.asc("cliente.nome"));

		return criteria.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Pedido> obterTodosPedidosPaginados(String filter, Integer first, Integer pageSize) {
		Criteria criteria = getSession().createCriteria(Pedido.class, "pedido");

		criteria.createAlias("pedido.cliente", "cliente");

		if (!StringUtils.isBlank(filter)) {
			criteria.add(Restrictions.ilike("cliente.nome", filter + "%", MatchMode.ANYWHERE));
		}

		if (first != null) {
			criteria.setFirstResult(first);
		}

		if (pageSize != null) {
			criteria.setMaxResults(pageSize);
		}

		criteria.addOrder(Order.desc("dataEmissao"));

		return criteria.list();
	}

	@Override
	public Long countBy(String filter) {
		Criteria criteria = getSession().createCriteria(Pedido.class, "pedido");

		criteria.createAlias("pedido.cliente", "cliente");

		if (!StringUtils.isBlank(filter)) {
			criteria.add(Restrictions.ilike("cliente.nome", filter + "%", MatchMode.ANYWHERE));
		}

		criteria.setProjection(Projections.rowCount());

		return (Long) criteria.uniqueResult();
	}
}
