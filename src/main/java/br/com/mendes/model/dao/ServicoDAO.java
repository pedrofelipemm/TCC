package br.com.mendes.model.dao;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;

import br.com.mendes.model.Servico;

public interface ServicoDAO extends DAO<Servico, Long> {

	List<Servico> getAllSorted();

	List<Servico> obterTodosServicosPaginados(Integer first, Integer pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters);

}
