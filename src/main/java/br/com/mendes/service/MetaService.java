package br.com.mendes.service;

import java.util.Date;
import java.util.List;

import br.com.mendes.dto.QtdePeriodoDTO;
import br.com.mendes.model.Item;
import br.com.mendes.model.Meta;
import br.com.mendes.model.MetaEspecifica;
import br.com.mendes.model.MetaGeral;
import br.com.mendes.model.TipoMetaGeral;

public interface MetaService {

	MetaGeral criarMetaGeral(MetaGeral meta);

	MetaEspecifica criarMetaEspecifica(MetaEspecifica meta);

	Meta criarMetaGeral(Double valor , TipoMetaGeral tipo);

	MetaEspecifica criarMetaEspecifica(Double valor , Item item);

	MetaGeral obterMetaGeralAtual(TipoMetaGeral tipo);

	MetaEspecifica obterMetaEspecificaAtual(Long codItem);

	List<QtdePeriodoDTO> obterMetaGeralAtual(TipoMetaGeral cliente,
			Date dataInicial);

	Long obterMetaGeralNoAnoMes(TipoMetaGeral cliente, Integer ano, Integer mes);

	List<QtdePeriodoDTO> obterMetasGeraisNoPeriodo(TipoMetaGeral cliente,
			List<QtdePeriodoDTO> periodos);

	List<QtdePeriodoDTO> obterMetasEspecificasNoPeriodo(Long codItem,
			List<QtdePeriodoDTO> periodos);

	Long obterMetaEspecificaNoAnoMes(Long codItem, Integer ano, Integer mes);
}
