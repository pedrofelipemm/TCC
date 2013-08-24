package br.com.mendes.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mendes.model.Produto;
import br.com.mendes.model.dao.ProdutoDAO;
import br.com.mendes.service.ProdutoService;

@Service("produtoService")
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	private ProdutoDAO produtoDAO;

	@Override
	@Transactional
	public Produto obterProdutoPorCod(Long codProduto) {
		return this.produtoDAO.getByCod(codProduto);
	}

	@Override
	@Transactional
	public List<Produto> obterTodosProduto() {
		return this.produtoDAO.getAllSorted();
	}

	@Override
	@Transactional
	public void criarProduto(Produto produto) {
		this.produtoDAO.saveUpdateGetEntity(produto);
	}
}
