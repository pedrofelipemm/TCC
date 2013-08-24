package br.com.mendes.model.dao;

import java.util.List;

import br.com.mendes.model.Produto;

public interface ProdutoDAO extends DAO<Produto, Long> {

	List<Produto> getAllSorted();

}
