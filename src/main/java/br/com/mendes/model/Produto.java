package br.com.mendes.model;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * 
 * @author Pedro
 */
@Entity
@DiscriminatorValue(value = "PRODUTO")
public class Produto extends Item implements Serializable {
	
	private static final long serialVersionUID = -3961577072959721645L;
	
	@Enumerated(EnumType.STRING)
	private CategoriaProduto categoria;

	public CategoriaProduto getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaProduto categoria) {
		this.categoria = categoria;
	}

}
