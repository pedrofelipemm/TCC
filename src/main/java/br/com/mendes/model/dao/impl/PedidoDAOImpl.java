package br.com.mendes.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
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

}
