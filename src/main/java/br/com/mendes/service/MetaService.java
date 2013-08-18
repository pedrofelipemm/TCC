package br.com.mendes.service;

import java.util.Date;
import java.util.List;

import br.com.mendes.dto.QtdePeriodoDTO;
import br.com.mendes.model.Item;
import br.com.mendes.model.MetaEspecifica;
import br.com.mendes.model.MetaGeral;
import br.com.mendes.model.TipoMetaGeral;

public interface MetaService {

	public abstract void criarMetaGeral(MetaGeral meta);

	public abstract void criarMetaEspecifica(MetaEspecifica meta);

	public abstract void criarMetaGeral(Double valor , TipoMetaGeral tipo);

	public abstract void criarMetaEspecifica(Double valor , Item item);

	public abstract MetaGeral obterMetaGeralAtual(TipoMetaGeral tipo);

	public abstract MetaEspecifica obterMetaEspecificaAtual(Long codItem);

	public abstract List<QtdePeriodoDTO> obterMetaGeralAtual(TipoMetaGeral cliente,
			Date dataInicial);

	public abstract Long obterMetaGeralNoAnoMes(TipoMetaGeral cliente, Integer ano, Integer mes);

	public abstract List<QtdePeriodoDTO> obterMetasGeraisNoPeriodo(TipoMetaGeral cliente,
			List<QtdePeriodoDTO> periodos);

	public abstract List<QtdePeriodoDTO> obterMetasEspecificasNoPeriodo(Long codItem,
			List<QtdePeriodoDTO> periodos);

	public abstract Long obterMetaEspecificaNoAnoMes(Long codItem, Integer ano, Integer mes);
}
