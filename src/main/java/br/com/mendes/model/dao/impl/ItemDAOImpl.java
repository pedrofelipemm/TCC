package br.com.mendes.model.dao.impl;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import br.com.mendes.model.Item;
import br.com.mendes.model.TipoItem;
import br.com.mendes.model.dao.ItemDAO;

@Repository
public class ItemDAOImpl extends DAOImpl<Item,Long> implements ItemDAO {

	private static final long serialVersionUID = 2157614205573395922L;

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> buscarPorTipoECLiente(Long codCliente, TipoItem tipoItem) {
		
		StringBuilder hql = new StringBuilder();
		
		hql.append(" select distinct i as tipo from ItemPedido ip " + 
				" join ip.item i " +
				" join ip.pedido p " +
				" join p.cliente c ");  
				
		hql.append(" where c.id=:codCliente");
		
		
		if(tipoItem!=null)
			hql.append(" and i.class=:tipoItem");
				
		Query query = getSession().createQuery(hql.toString());
		
		query.setParameter("codCliente", codCliente);
		
		if(tipoItem!=null)
			query.setParameter("tipoItem", tipoItem.name());
						
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> buscarTodos(TipoItem tipoItem) {
		
		StringBuilder hql = new StringBuilder();
		
		hql.append(" select distinct i as tipo from Item i ");
		
		if(tipoItem!=null)
			hql.append(" where i.class=:tipoItem");		
		
		Query query = getSession().createQuery(hql.toString());
				
		if(tipoItem!=null)
			query.setParameter("tipoItem", tipoItem.name());
						
		return query.list();
	}

	@Override
	public Long obterMetaEspecificaNoAnoMes(Long codItem, Integer ano,
			Integer mes) {
		
		StringBuilder hql = new StringBuilder();
		
		hql.append(" select sum(ip.quantidade) " +
				   " from  ItemPedido ip " +
				   " join ip.item item " +
				   " join ip.pedido pedido " +
				   " where item.cod=:codItem " +
				   " and year(pedido.dataEmissao)=:ano " +
				   " and month(pedido.dataEmissao)=:mes ");
		
		Query query = getSession().createQuery(hql.toString());
		
		query.setParameter("codItem", codItem);
		
		query.setParameter("ano", ano);
		
		query.setParameter("mes", mes);
							
		return query.uniqueResult()==null?0L: (Long) query.uniqueResult();
	}

	@Override
	public Long obterQtdeGeralNoAnoMes(TipoItem tipoItem, Integer ano,
			Integer mes) {
		
		StringBuilder hql = new StringBuilder();
		
		hql.append(" select sum(ip.quantidade) " +
				   " from  ItemPedido ip " +
				   " join ip.item item " +
				   " join ip.pedido pedido " +
				   " where item.class=:tipoItem " +
				   " and year(pedido.dataEmissao)=:ano " +
				   " and month(pedido.dataEmissao)=:mes ");
		
		Query query = getSession().createQuery(hql.toString());
		
		query.setParameter("tipoItem", tipoItem.name());
		
		query.setParameter("ano", ano);
		
		query.setParameter("mes", mes);
							
		return query.uniqueResult()==null?0L: (Long) query.uniqueResult();
	}

	@Override
	public Double obterQtdeGeralValorNoAnoMes(TipoItem tipoItem, Integer ano,
			Integer mes) {
		
		StringBuilder hql = new StringBuilder();
		
		hql.append(" select sum(ip.quantidade*item.precoVenda) " +
				   " from  ItemPedido ip " +
				   " join ip.item item " +
				   " join ip.pedido pedido " +
				   " where item.class=:tipoItem " +
				   " and year(pedido.dataEmissao)=:ano " +
				   " and month(pedido.dataEmissao)=:mes ");
		
		Query query = getSession().createQuery(hql.toString());
		
		query.setParameter("tipoItem", tipoItem.name());
		
		query.setParameter("ano", ano);
		
		query.setParameter("mes", mes);
							
		return query.uniqueResult()==null?0.0: (Double) query.uniqueResult();
	}

}
