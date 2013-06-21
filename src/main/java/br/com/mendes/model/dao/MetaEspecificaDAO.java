package br.com.mendes.model.dao;

import java.util.Date;

import br.com.mendes.model.MetaEspecifica;


public interface MetaEspecificaDAO extends DAO<MetaEspecifica, Long>{

	MetaEspecifica obterMetaAtual(Long codItem);

	Long obterMetaEspecificaNoAnoMes(Long codItem, Date time);

}
