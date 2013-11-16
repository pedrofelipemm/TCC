package br.com.mendes.service;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;

import br.com.mendes.model.Produto;

public interface ProdutoService {

	public abstract Produto obterProdutoPorCod(Long codProduto);

	public abstract List<Produto> obterTodosProduto();

	public abstract void criarProduto(Produto produto);

	public abstract List<Produto> obterTodosProdutosPaginados(Integer first, Integer pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters);

	public abstract Long countBy();

}
