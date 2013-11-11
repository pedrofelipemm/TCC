package br.com.mendes.dao.dataloader;

import java.util.ArrayList;
import java.util.List;

import br.com.mendes.model.CategoriaProduto;
import br.com.mendes.model.Produto;

public class ProdutosDataLoader {

	public static List<Produto> createProdutos() {
		List<Produto> produtos = new ArrayList<Produto>();

		produtos.add(new Produto(CategoriaProduto.INSTRUMENTO, "Violao Mendes S", 1000D, 1200D));
		produtos.add(new Produto(CategoriaProduto.INSTRUMENTO, "Violao Mendes GT", 2000D, 2400D));
		produtos.add(new Produto(CategoriaProduto.INSTRUMENTO, "Guitarra Mendes M", 3000D, 3900D));
		produtos.add(new Produto(CategoriaProduto.INSTRUMENTO, "Guitarra Mendes RX", 4000D, 5200D));
		produtos.add(new Produto(CategoriaProduto.INSTRUMENTO, "Baixo Mendes Y4", 5000D, 5400D));
		produtos.add(new Produto(CategoriaProduto.INSTRUMENTO, "Baixo Mendes GT6", 6000D, 7800D));

		return produtos;
	}
}
