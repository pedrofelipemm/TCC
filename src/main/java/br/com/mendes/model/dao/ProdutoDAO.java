package br.com.mendes.model.dao;

import java.util.List;

import br.com.mendes.model.Produto;

public interface ProdutoDAO extends DAO<Produto, Long> {

	List<Produto> getAllSorted();

	List<Produto> obterTodosProdutosPaginados(String filter, Integer first, Integer pageSize);

	Long countBy(String filter);

}
