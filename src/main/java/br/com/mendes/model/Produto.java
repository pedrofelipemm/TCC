package br.com.mendes.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue(value = "PRODUTO")
public class Produto extends Item implements Serializable {

	private static final long serialVersionUID = -3961577072959721645L;

	@NotNull
	@Enumerated(EnumType.STRING)
	private CategoriaProduto categoria;

	public Produto() {
	}

	public Produto(CategoriaProduto categoria, String nome, Double custo, Double precoVenda) {
		super(nome, custo, precoVenda);
		this.categoria = categoria;
	}

	public Produto(CategoriaProduto categoria, String nome, Double custo, Double precoVenda, List<MetaEspecifica> metas) {
		super(nome, custo, precoVenda, metas);
		this.categoria = categoria;
	}

	public CategoriaProduto getCategoria() {
		return this.categoria;
	}

	public void setCategoria(CategoriaProduto categoria) {
		this.categoria = categoria;
	}

}
