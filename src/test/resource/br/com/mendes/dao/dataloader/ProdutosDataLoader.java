package br.com.mendes.dao.dataloader;

import java.util.ArrayList;
import java.util.List;

import br.com.mendes.model.CategoriaProduto;
import br.com.mendes.model.Produto;

public class ProdutosDataLoader {

	public static List<Produto> createProdutos() {
		List<Produto> produtos = new ArrayList<Produto>();

		produtos.add(new Produto(CategoriaProduto.INSTRUMENTO, "Violao Mendes 1", 1000D, 2000D));
		produtos.add(new Produto(CategoriaProduto.INSTRUMENTO, "Violao Mendes 2", 2000D, 3000D));
		produtos.add(new Produto(CategoriaProduto.INSTRUMENTO, "Violao Mendes 3", 3000D, 4000D));
		produtos.add(new Produto(CategoriaProduto.INSTRUMENTO, "Violao Mendes 4", 4000D, 5000D));
		produtos.add(new Produto(CategoriaProduto.INSTRUMENTO, "Violao Mendes 5", 5000D, 6000D));
		produtos.add(new Produto(CategoriaProduto.INSTRUMENTO, "Violao Mendes 6", 6000D, 7000D));

		return produtos;
	}
}
