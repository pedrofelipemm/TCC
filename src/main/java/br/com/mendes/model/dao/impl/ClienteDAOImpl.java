package br.com.mendes.model.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.mendes.model.Cliente;
import br.com.mendes.model.dao.ClienteDAO;

@Repository
public class ClienteDAOImpl extends DAOImpl<Cliente, Long> implements ClienteDAO {

	private static final long serialVersionUID = -7190050683815189287L;

	@Override
	public Long obterQtdeClientesNoAnoMes(Integer ano, Integer mes) {

		StringBuilder hql = new StringBuilder();

		hql.append(" select count(c.codCliente) " +
				" from  Cliente c 					 " +
				" where year(c.dataCadastro)=:ano " +
				" and month(c.dataCadastro)=:mes ");

		Query query = getSession().createQuery(hql.toString());

		query.setParameter("ano", ano);

		query.setParameter("mes", mes);

		return (Long) query.uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Cliente> getAllSorted() {
		Criteria criteria = getSession().createCriteria(Cliente.class);

		criteria.addOrder(Order.asc("nome"));

		return criteria.list();
	}

	@Override
	public Long countBy(String filter) {
		Criteria criteria = getSession().createCriteria(Cliente.class);

		if (!StringUtils.isBlank(filter)) {
			criteria.add(Restrictions.ilike("nome", filter,
					MatchMode.ANYWHERE));
		}

		criteria.setProjection(Projections.rowCount());

		return (Long) criteria.uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Cliente> obterTodosClientesPaginados(String filter, Integer first, Integer pageSize) {
		Criteria criteria = getSession().createCriteria(Cliente.class);

		if (!StringUtils.isBlank(filter)) {
			criteria.add(Restrictions.or(Restrictions.ilike("nome", filter + "%", MatchMode.ANYWHERE),
					Restrictions.ilike("sobrenome", filter + "%", MatchMode.ANYWHERE)));
		}

		/*
		 * RODA NO POST select * from cliente c left outer join endereco e on
		 * c.endereco_codEndereco = e.codEndereco where( nome ilike 'm%' or
		 * sobrenome ilike 'm%') order by c.nome
		 */

		if (first != null) {
			criteria.setFirstResult(first);
		}

		if (pageSize != null) {
			criteria.setMaxResults(pageSize);
		}

		criteria.addOrder(Order.asc("nome"));

		return criteria.list();
	}

}
