package br.com.mendes.model;

import java.io.Serializable;

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
       
	public Item getItem() {
		return item;
	}


	public void setItem(Item item) {
		this.item = item;
	}
    
}
