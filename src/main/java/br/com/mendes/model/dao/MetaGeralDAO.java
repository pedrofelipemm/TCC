package br.com.mendes.model.dao;

import java.util.Date;
import java.util.List;

import br.com.mendes.dto.QtdePeriodoDTO;
import br.com.mendes.model.MetaGeral;
import br.com.mendes.model.TipoMetaGeral;


public interface MetaGeralDAO extends DAO<MetaGeral, Long>{

	MetaGeral obterMetaAtual(TipoMetaGeral tipo);

	List<QtdePeriodoDTO> obterMetaGeralAtual(TipoMetaGeral tipo,
			Date dataInicial);

	Long obterMetaGeralNoAnoMes(TipoMetaGeral tipoMetaGeral, Date time);


}
