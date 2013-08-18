package br.com.mendes.model;

import java.io.Serializable;

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

	public CategoriaProduto getCategoria() {
		return this.categoria;
	}

	public void setCategoria(CategoriaProduto categoria) {
		this.categoria = categoria;
	}

}
