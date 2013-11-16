package br.com.mendes.model.dao;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;

import br.com.mendes.model.Cliente;

public interface ClienteDAO extends DAO<Cliente, Long> {

	Long obterQtdeClientesNoAnoMes(Integer ano, Integer mes);

	List<Cliente> getAllSorted();

	List<Cliente> obterTodosClientesPaginados(Integer first, Integer pageSize, SortOrder sortOrder, String sortField, Map<String, String> filters);

}
