package br.com.mendes.view.lazy;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.mendes.model.Produto;
import br.com.mendes.service.ProdutoService;

public class ProdutosLazyDataModel extends LazyDataModel<Produto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3522757856213883063L;

	private ProdutoService produtoService;
	private List<Produto> produtos;

	public ProdutosLazyDataModel(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	@Override
	public List<Produto> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {

		this.produtos = this.produtoService.obterTodosProdutosPaginados(first, pageSize, sortField, sortOrder, filters);

		setRowCount(this.produtoService.countBy().intValue());

		return this.produtos;
	}

	@Override
	public Object getRowKey(Produto object) {
		return object.getCod();
	}

	@Override
	public void setRowIndex(int rowIndex) {

		if (rowIndex == -1 || getPageSize() == 0) {
			super.setRowIndex(-1);
		} else {
			super.setRowIndex(rowIndex % getPageSize());
		}
	}

	@Override
	public Produto getRowData(String rowKey) {

		if (!"null".equalsIgnoreCase(rowKey)) {
			Long id = Long.valueOf(rowKey);

			for (Produto produto : this.produtos) {
				if (id.equals(produto.getCod())) {
					return produto;
				}
			}
		}

		return null;
	}

	public ProdutoService getProdutoService() {
		return this.produtoService;
	}

	public void setProdutoService(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	public List<Produto> getProdutos() {
		return this.produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

}
