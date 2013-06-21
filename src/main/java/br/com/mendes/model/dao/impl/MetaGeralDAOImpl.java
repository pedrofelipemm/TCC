package br.com.mendes.model.dao.impl;

import java.security.InvalidParameterException;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import br.com.mendes.dto.QtdePeriodoDTO;
import br.com.mendes.model.MetaGeral;
import br.com.mendes.model.TipoMetaGeral;
import br.com.mendes.model.dao.MetaGeralDAO;

@Repository
public class MetaGeralDAOImpl extends DAOImpl<MetaGeral,Long> implements MetaGeralDAO {

	private static final long serialVersionUID = 2478166995437747144L;

	@Override
	public MetaGeral obterMetaAtual(TipoMetaGeral tipo) {
		
		if(tipo==null)
			throw new InvalidParameterException();
		
		StringBuilder hql = new StringBuilder();
		
		hql.append(" select m from MetaGeral m " +
				   " where m.tipo=:tipo " +
				   " and m.dataInicio=(select max(mi.dataInicio) from MetaGeral mi where mi.tipo=:tipo )");		
		
		Query query = getSession().createQuery(hql.toString());
				
		query.setParameter("tipo", tipo);
						
		return (MetaGeral) query.uniqueResult();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<QtdePeriodoDTO> obterMetaGeralAtual(TipoMetaGeral tipo,
			Date dataInicial) {

		StringBuilder hql = new StringBuilder();
		
		hql.append(" select year(m.dataInicio) as ano, " +
				   "        month(m.dataInicio) as mes, " +
				   "        count(m.dataInicio) as qtde  " +
				   " from  MetaGeral m 					 " +
				   " where  m.tipo=:tipo " +
				   " and m.dataInicio>=:dataInicial " +
				   " and m.dataInicio=(select max(mi.dataInicio) from MetaGeral mi " +
				   					    "where mi.tipo=:tipo and mi.dataInicio>=:dataInicial" +
				   					    "and year(mi.dataInicio)=year(m.dataInicio) and month(mi.dataInicio)=month(m.dataInicio) )" +
				   " order by year(m.dataInicio), month(m.dataInicio) ");
		
		Query query = getSession().createQuery(hql.toString());
		
		query.setParameter("dataInicial", dataInicial);
		query.setParameter("tipo", tipo);
		
		query.setResultTransformer(Transformers.aliasToBean(QtdePeriodoDTO.class));
		
		return query.list();
		
	}

	@Override
	public Long obterMetaGeralNoAnoMes(TipoMetaGeral tipoMetaGeral, Date ultimoDiaHoraMes) {

		StringBuilder hql = new StringBuilder();
		
		hql.append(" select m.valor " +
				   " from  MetaGeral m 					 " +
				   " where m.tipo=:tipoMetaGeral " +
				   " and m.dataInicio<=:ultimoDiaHoraMes " +
				   " order by m.dataInicio desc");
		
		Query query = getSession().createQuery(hql.toString());
		
		query.setParameter("ultimoDiaHoraMes", ultimoDiaHoraMes);
		query.setParameter("tipoMetaGeral", tipoMetaGeral);
		
		query.setMaxResults(1);
							
		return query.uniqueResult()==null?0L: ((Double) query.uniqueResult()).longValue();
		
	
	}

}
