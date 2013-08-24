package br.com.mendes.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * 
 * @author Pedro
 */
@Entity
@DiscriminatorValue(value = "ESPECIFICA")
public class MetaEspecifica extends Meta implements Serializable {

	private static final long serialVersionUID = 3618918025061048331L;

	@ManyToOne
	private Item item;

	public MetaEspecifica() {
	}

	public MetaEspecifica(Double valor, Date dataInicio, Item item) {
		super(valor, dataInicio);
		this.item = item;
	}

	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

}
