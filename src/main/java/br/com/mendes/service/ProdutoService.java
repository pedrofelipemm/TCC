package br.com.mendes.service;

import java.util.List;

import br.com.mendes.model.Produto;

public interface ProdutoService {

    Produto obterProdutoPorCod(Long codProduto);
    
    List<Produto> obterTodosProduto();
    
    Produto criarProduto(Produto produto);
}
