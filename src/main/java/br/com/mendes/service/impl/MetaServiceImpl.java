package br.com.mendes.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mendes.dto.QtdePeriodoDTO;
import br.com.mendes.model.Item;
import br.com.mendes.model.MetaEspecifica;
import br.com.mendes.model.MetaGeral;
import br.com.mendes.model.TipoMetaGeral;
import br.com.mendes.model.dao.MetaEspecificaDAO;
import br.com.mendes.model.dao.MetaGeralDAO;
import br.com.mendes.service.MetaService;

@Service
public class MetaServiceImpl implements MetaService {

	@Autowired
	private MetaGeralDAO metaGeralDAO;

	@Autowired
	private MetaEspecificaDAO metaEspeficicaDAO;
	
	@Override
	@Transactional
	public MetaGeral criarMetaGeral(MetaGeral meta) {
		
		if(meta==null)
			return null;
		if(meta.getValor()==null)
			return null;
		
		MetaGeral metaAtual = metaGeralDAO.obterMetaAtual(meta.getTipo());
		
		if(metaAtual!=null && metaAtual.getValor().equals(meta.getValor()))
			return null;
		
		meta.setCodMeta(null);
		
		meta.setDataInicio(new Date());
		
		return metaGeralDAO.saveUpdateGetEntity(meta);
	}
	
	@Override
	@Transactional
	public MetaEspecifica criarMetaEspecifica(MetaEspecifica meta) {
		return metaEspeficicaDAO.saveUpdateGetEntity(meta);
	}
	
	@Override
	@Transactional
	public MetaEspecifica criarMetaEspecifica(Double valor, Item item) {
		
		
		MetaEspecifica metaEspecifica  = metaEspeficicaDAO.obterMetaAtual(item.getCod());
		
		if(metaEspecifica != null && valor.equals(metaEspecifica.getValor()))		
			return metaEspecifica;
		
		metaEspecifica = new MetaEspecifica();
		metaEspecifica.setDataInicio(new Date());
		metaEspecifica.setItem(item);
		metaEspecifica.setValor(valor);
	
		return metaEspeficicaDAO.saveUpdateGetEntity(metaEspecifica);
	}
	
	@Override
	@Transactional
	public MetaGeral criarMetaGeral(Double valor , TipoMetaGeral tipo) {
		MetaGeral meta  = new MetaGeral();
		meta.setDataInicio(new Date());
		meta.setValor(valor);
		meta.setTipo(tipo);
		return metaGeralDAO.saveUpdateGetEntity(meta);
	}

	@Override
	@Transactional
	public MetaGeral obterMetaGeralAtual(TipoMetaGeral tipo) {
		return metaGeralDAO.obterMetaAtual(tipo);
	}
	
	@Override
	@Transactional
	public MetaEspecifica obterMetaEspecificaAtual(Long codItem) {
		return metaEspeficicaDAO.obterMetaAtual(codItem);
	}

	@Override
	public List<QtdePeriodoDTO> obterMetaGeralAtual(TipoMetaGeral tipo,
			Date dataInicial) {
		return metaGeralDAO.obterMetaGeralAtual(tipo, dataInicial);
	}

	@Override
	@Transactional
	public Long obterMetaGeralNoAnoMes(TipoMetaGeral tipoMetaGeral, Integer ano,Integer mes) {
		
		Calendar data = new GregorianCalendar();
		data.set(Calendar.YEAR, ano);
		data.set(Calendar.MONTH, (mes-1));
		data.set(Calendar.DAY_OF_MONTH, data.getMaximum(Calendar.DAY_OF_MONTH));
		data.set(Calendar.HOUR_OF_DAY, data.getMaximum(Calendar.HOUR_OF_DAY));
		
		return metaGeralDAO.obterMetaGeralNoAnoMes(tipoMetaGeral, data.getTime());
	}

	@Override
	@Transactional(readOnly=true)
	public List<QtdePeriodoDTO> obterMetasGeraisNoPeriodo(
			TipoMetaGeral tipoMetaGeral, List<QtdePeriodoDTO> periodos) {
		
		for(QtdePeriodoDTO periodo : periodos) {
			Long qtde = this.obterMetaGeralNoAnoMes(tipoMetaGeral, periodo.getAno(), periodo.getMes());
			periodo.setQtde(qtde.doubleValue());
		}
		
		return periodos;
	}

	@Override
	@Transactional(readOnly=true)
	public List<QtdePeriodoDTO> obterMetasEspecificasNoPeriodo(Long codItem,
			List<QtdePeriodoDTO> periodos) {
		

		for(QtdePeriodoDTO periodo : periodos) {
			Long qtde = this.obterMetaEspecificaNoAnoMes(codItem, periodo.getAno(), periodo.getMes());
			periodo.setQtde(qtde.doubleValue());
		}
		
		return periodos;
	}

	@Override
	@Transactional(readOnly=true)
	public Long obterMetaEspecificaNoAnoMes(Long codItem, Integer ano,
			Integer mes) {
		
		Calendar data = new GregorianCalendar();
		data.set(Calendar.YEAR, ano);
		data.set(Calendar.MONTH, (mes-1));
		data.set(Calendar.DAY_OF_MONTH, data.getMaximum(Calendar.DAY_OF_MONTH));
		data.set(Calendar.HOUR_OF_DAY, data.getMaximum(Calendar.HOUR_OF_DAY));
		
		return metaEspeficicaDAO.obterMetaEspecificaNoAnoMes(codItem, data.getTime());
	}
}
