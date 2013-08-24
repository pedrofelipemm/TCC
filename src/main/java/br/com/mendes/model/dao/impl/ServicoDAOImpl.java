package br.com.mendes.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import br.com.mendes.model.Servico;
import br.com.mendes.model.dao.ServicoDAO;

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

}
