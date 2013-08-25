package br.com.mendes.model.dao;

import java.util.List;

import br.com.mendes.model.Servico;

public interface ServicoDAO extends DAO<Servico, Long> {

	List<Servico> getAllSorted();

	Long countBy(String filter);

	List<Servico> obterTodosServicosPaginados(String filter, Integer first, Integer pageSize);

}
