package br.com.mendes.model.dao;

import br.com.mendes.model.Cliente;


public interface ClienteDAO extends DAO<Cliente, Long>{
	
	Long obterQtdeClientesNoAnoMes(Integer ano, Integer mes);

}
