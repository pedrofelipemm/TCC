package br.com.mendes.model.dao;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;

import br.com.mendes.model.Produto;

public interface ProdutoDAO extends DAO<Produto, Long> {

	List<Produto> getAllSorted();

	List<Produto> obterTodosProdutosPaginados(Integer first, Integer pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters);

}
