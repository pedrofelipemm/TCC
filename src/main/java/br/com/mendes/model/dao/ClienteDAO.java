package br.com.mendes.model.dao;

import java.util.List;

import br.com.mendes.model.Cliente;

public interface ClienteDAO extends DAO<Cliente, Long> {

	Long obterQtdeClientesNoAnoMes(Integer ano, Integer mes);

	List<Cliente> getAllSorted();

	List<Cliente> obterTodosClientesPaginados(String filter, Integer first, Integer pageSize);

	Long countBy(String filter);

}
