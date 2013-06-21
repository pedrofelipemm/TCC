package br.com.mendes.model.dao.impl;

import java.security.InvalidParameterException;
import java.util.Date;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import br.com.mendes.model.MetaEspecifica;
import br.com.mendes.model.dao.MetaEspecificaDAO;

@Repository
public class MetaEspecificaDAOImpl extends DAOImpl<MetaEspecifica,Long> implements MetaEspecificaDAO {
	
	private static final long serialVersionUID = 4532555171286969916L;

	@Override
	public MetaEspecifica obterMetaAtual(Long codItem) {
		
		if(codItem==null)
			throw new InvalidParameterException();
		
		StringBuilder hql = new StringBuilder();
		
		hql.append(" select m from MetaEspecifica m " +
				   " join m.item i " +
				   " where i.cod=:codItem " +
				   " and m.dataInicio=(select max(_m.dataInicio) from MetaEspecifica _m join _m.item _i where _i.cod=:codItem )");		
		
		Query query = getSession().createQuery(hql.toString());
				
		query.setParameter("codItem", codItem);
						
		return (MetaEspecifica) query.uniqueResult();
	}

	@Override
	public Long obterMetaEspecificaNoAnoMes(Long codItem, Date time) {
		
		StringBuilder hql = new StringBuilder();
		
		hql.append(" select m.valor " +
				   " from  MetaEspecifica m " +
				   " join m.item item " +
				   " where item.cod=:cod " +
				   " and m.dataInicio<=:ultimoDiaHoraMes " +
				   " order by m.dataInicio desc");
		
		Query query = getSession().createQuery(hql.toString());
		
		query.setParameter("ultimoDiaHoraMes", time);
		query.setParameter("cod", codItem);
		
		query.setMaxResults(1);
							
		return query.uniqueResult()==null?0L: ((Double) query.uniqueResult()).longValue();
	}

}
