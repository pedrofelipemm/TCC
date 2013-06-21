package br.com.mendes.model.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import br.com.mendes.model.Cliente;
import br.com.mendes.model.dao.ClienteDAO;

@Repository
public class ClienteDAOImpl extends DAOImpl<Cliente,Long> implements ClienteDAO {
		
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
	
	
	
}
