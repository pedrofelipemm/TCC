package br.com.mendes.model.dao.impl;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import br.com.mendes.model.ItemPedido;
import br.com.mendes.model.Pedido;
import br.com.mendes.model.dao.ItemPedidoDAO;

@Repository
public class ItemPedidoDAOImpl extends DAOImpl<ItemPedido,Long> implements ItemPedidoDAO {

	private static final long serialVersionUID = -2655776406220270159L;

	@SuppressWarnings("unchecked")
	@Override
	public List<Pedido> obterPedidoPorCliente(Long codCliente) {

		StringBuilder hql = new StringBuilder();
		
		hql.append(" select p from Pedido p " +
				   " join p.cliente c ");
		
		if(codCliente!=null)
			hql.append(" where c.codCliente=:codCliente ");		
		
		Query query = getSession().createQuery(hql.toString());
				
		if(codCliente!=null)
			query.setParameter("codCliente", codCliente);
						
		return query.list();
	}



}
