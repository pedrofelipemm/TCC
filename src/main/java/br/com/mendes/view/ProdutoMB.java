package br.com.mendes.view;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import br.com.mendes.model.CategoriaProduto;
import br.com.mendes.model.Meta;
import br.com.mendes.model.Produto;
import br.com.mendes.service.MetaService;
import br.com.mendes.service.ProdutoService;
import br.com.mendes.utils.MBUtil;
import br.com.mendes.view.lazy.ProdutosLazyDataModel;

@ManagedBean(name = "produtoMB")
@ViewScoped
public class ProdutoMB implements Serializable {

	private static final long serialVersionUID = -8900938210077927756L;

	private Produto produto;

	private List<Produto> produtos;

	private ProdutosLazyDataModel produtosLazyDataModel;

	private List<CategoriaProduto> categoriasProduto;

	private Double valorMeta;

	@ManagedProperty(name = "produtoService", value = "#{produtoService}")
	private ProdutoService produtoService;

	@ManagedProperty(name = "metaService", value = "#{metaService}")
	private MetaService metaService;

	@PostConstruct
	public void iniciar() {
		this.produtos = this.produtoService.obterTodosProduto();
		this.produtosLazyDataModel = new ProdutosLazyDataModel(this.produtoService);
		this.categoriasProduto = Arrays.asList(CategoriaProduto.values());

		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();

		if (flash.isEmpty()) {
			resetDados();
		} else {
			this.produto = (Produto) flash.get("produto");
			this.valorMeta = (Double) flash.get("valorMeta");
		}

	}

	public void resetDados() {
		this.produto = new Produto();
		this.valorMeta = null;
	}

	public String iniciarEdicao(Long codProduto) {

		this.produto = this.produtoService.obterProdutoPorCod(codProduto);

		Meta meta = this.metaService.obterMetaEspecificaAtual(codProduto);

		if (meta != null) {
			this.valorMeta = meta.getValor();
		}

		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();

		flash.put("produto", this.produto);
		flash.put("valorMeta", this.valorMeta);

		return "/paginas/cadastroProduto.xhtml";
	}

	public void salvarProduto() {

		this.produtoService.criarProduto(this.produto);

		if ((this.valorMeta != null) && (this.valorMeta > 0)) {
			this.metaService.criarMetaEspecifica(this.valorMeta, this.produto);
		}

		MBUtil.addInfo("Cadastrado com sucesso.");

		resetDados();
	}

	public void pesquisa() {
		this.produtosLazyDataModel.setRowIndex(-1);
	}

	public Produto getProduto() {
		return this.produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutos() {
		return this.produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<CategoriaProduto> getCategoriasProduto() {
		return this.categoriasProduto;
	}

	public void setCategoriasProduto(List<CategoriaProduto> categoriasProduto) {
		this.categoriasProduto = categoriasProduto;
	}

	public Double getValorMeta() {
		return this.valorMeta;
	}

	public void setValorMeta(Double valorMeta) {
		this.valorMeta = valorMeta;
	}

	public ProdutoService getProdutoService() {
		return this.produtoService;
	}

	public void setProdutoService(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	public ProdutosLazyDataModel getProdutosLazyDataModel() {
		return this.produtosLazyDataModel;
	}

	public void setProdutosLazyDataModel(ProdutosLazyDataModel produtosLazyDataModel) {
		this.produtosLazyDataModel = produtosLazyDataModel;
	}

	public MetaService getMetaService() {
		return this.metaService;
	}

	public void setMetaService(MetaService metaService) {
		this.metaService = metaService;
	}

}
