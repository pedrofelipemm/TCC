package br.com.mendes.service;

import java.util.List;

import br.com.mendes.model.Produto;

public interface ProdutoService {

	public abstract Produto obterProdutoPorCod(Long codProduto);

	public abstract List<Produto> obterTodosProduto();

	public abstract void criarProduto(Produto produto);

	public abstract List<Produto> obterTodosProdutosPaginados(String filter, int first, int pageSize);

	public abstract Long countBy(String filter);
}
