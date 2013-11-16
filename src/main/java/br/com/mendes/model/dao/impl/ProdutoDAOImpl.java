package br.com.mendes.model.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.primefaces.model.SortOrder;
import org.springframework.stereotype.Repository;

import br.com.mendes.model.CategoriaProduto;
import br.com.mendes.model.Produto;
import br.com.mendes.model.dao.ProdutoDAO;
import br.com.mendes.utils.CONSTANTS;

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
	public List<Produto> obterTodosProdutosPaginados(Integer first, Integer pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {

		Criteria criteria = getSession().createCriteria(Produto.class, "produto");

		if (filters != null && !filters.isEmpty()) {

			for (Entry<String, String> entry : filters.entrySet()) {

				if (entry.getKey().equals(CONSTANTS.CUSTO.getDescricao()) || entry.getKey().equals(CONSTANTS.PRECOVENDA.getDescricao())) {

					try {
						Double valor = new Double(entry.getValue());
						criteria.add(Restrictions.eq(entry.getKey(), valor));
					} catch (NumberFormatException exception) {
						criteria.add(Restrictions.isNull(entry.getKey()));
					}

				} else if (entry.getKey().equals(CONSTANTS.CATEGORIA.getDescricao())) {

					criteria.add(Restrictions.eq(entry.getKey(), CategoriaProduto.getEnum(entry.getValue())));

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
