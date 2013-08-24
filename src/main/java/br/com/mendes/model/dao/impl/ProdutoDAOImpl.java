package br.com.mendes.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
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

}
