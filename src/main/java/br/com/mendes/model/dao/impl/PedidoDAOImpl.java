package br.com.mendes.model.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
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

import br.com.mendes.model.Pedido;
import br.com.mendes.model.dao.PedidoDAO;
import br.com.mendes.utils.CONSTANTS;

@Repository
public class PedidoDAOImpl extends DAOImpl<Pedido, Long> implements
		PedidoDAO {

	private static final long serialVersionUID = 9051943387825326329L;

	@Override
	@SuppressWarnings("unchecked")
	public List<Pedido> getAllSorted() {
		Criteria criteria = getSession().createCriteria(Pedido.class, "pedido");

		criteria.createAlias("pedido.cliente", "cliente");

		criteria.addOrder(Order.asc(CONSTANTS.PEDIDOCLIENTE.getDescricao()));

		return criteria.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Pedido> obterTodosPedidosPaginados(Integer first, Integer pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {

		Criteria criteria = getSession().createCriteria(Pedido.class, "pedido");
		criteria.createAlias(CONSTANTS.PEDIDOCLIENTE.getDescricao(), "cliente");

		if (filters != null && !filters.isEmpty()) {

			for (Entry<String, String> entry : filters.entrySet()) {

				if (entry.getKey().equals(CONSTANTS.CLIENTENOME.getDescricao())) {

					List<Criterion> predicates = new ArrayList<Criterion>();

					predicates.add(Restrictions.ilike(entry.getKey(), entry.getValue() + "%", MatchMode.ANYWHERE));
					predicates.add(Restrictions.ilike(entry.getKey().replaceAll(CONSTANTS.NOME.getDescricao(), CONSTANTS.SOBRENOME.getDescricao()),
							entry.getValue() + "%", MatchMode.ANYWHERE));

					criteria.add(Restrictions.or(predicates.toArray(new Criterion[0])));

				} else if (entry.getKey().equals(CONSTANTS.DATAEMISSAO.getDescricao())) {

					if (entry.getValue().replaceAll("_", "").length() == 10) {

						Calendar data = Calendar.getInstance();
						data.set(Integer.parseInt(entry.getValue().substring(6, 10)), Integer.parseInt(entry.getValue().substring(3, 5)) - 1,
								Integer.parseInt(entry.getValue().substring(0, 2)));

						criteria.add(Restrictions.eq(entry.getKey(), data.getTime()));

					}

				} else if (entry.getKey().equals(CONSTANTS.VALORTOTAL.getDescricao())) {

					try {
						criteria.add(Restrictions.eq(CONSTANTS.VALORTOTAL.getDescricao(), new Double(entry.getValue())));
					} catch (NumberFormatException exception) {
						criteria.add(Restrictions.isNull(entry.getKey()));
					}

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
			criteria.addOrder(Order.desc("dataEmissao"));
		} else {
			criteria.addOrder(sortOrder.equals(SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField));
		}

		return criteria.list();
	}

}
