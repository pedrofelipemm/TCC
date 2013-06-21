package br.com.mendes.view;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.mendes.model.CategoriaProduto;
import br.com.mendes.model.Meta;
import br.com.mendes.model.Produto;
import br.com.mendes.service.MetaService;
import br.com.mendes.service.ProdutoService;
import br.com.mendes.utils.MBUtil;


@Scope(value="request")
@Controller("produtoMB")
public class ProdutoMB implements Serializable{
	
	private static final long serialVersionUID = -8900938210077927756L;

	private Produto produto;
	
	private List<Produto> produtos;
	
	private List<CategoriaProduto> categoriasProduto;
	
	private Double valorMeta;
	
	@Autowired 
	private ProdutoService produtoService;
	
	@Autowired 
	private MetaService metaService;
	
	@PostConstruct
	public void iniciar() {
		produtos = produtoService.obterTodosProduto();
		
    	categoriasProduto = Arrays.asList(CategoriaProduto.values());
    	
    	resetDados();
	}
	
	public void resetDados() {
		produto = new  Produto();
		valorMeta = null;
	}
        
	public String iniciarEdicao(Long codProduto) {
		
		produto = produtoService.obterProdutoPorCod(codProduto);
		
		Meta meta = metaService.obterMetaEspecificaAtual(codProduto);
		
		if(meta!=null)
			valorMeta = meta.getValor();
		
		return "/paginas/cadastroProduto.xhtml";
	}
	

    public void salvarProduto() {

    	Produto produtoSalvo = produtoService.criarProduto(produto);
    	
    	if(valorMeta!=null)
    		metaService.criarMetaEspecifica(valorMeta, produtoSalvo);
    	
    	MBUtil.addInfo("Cadastrado com sucesso.");  
    	
    	resetDados();
    }

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}


	public List<Produto> getProdutos() {
		return produtos;
	}


	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<CategoriaProduto> getCategoriasProduto() {
		return categoriasProduto;
	}

	public void setCategoriasProduto(List<CategoriaProduto> categoriasProduto) {
		this.categoriasProduto = categoriasProduto;
	}
	
	public Double getValorMeta() {
		return valorMeta;
	}
	
	public void setValorMeta(Double valorMeta) {
		this.valorMeta = valorMeta;
	}
}
