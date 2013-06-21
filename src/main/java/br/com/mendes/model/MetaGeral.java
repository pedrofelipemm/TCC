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
@DiscriminatorValue(value = "GERAL")
public class MetaGeral extends Meta implements Serializable {
   	
	private static final long serialVersionUID = 2019817085394162949L;
	
	@Enumerated(EnumType.STRING)
	private TipoMetaGeral tipo;
	
	public MetaGeral(){
		 
	}
	
	public MetaGeral(TipoMetaGeral tipoMetaGeral) {
		this.tipo = tipoMetaGeral;
	}
   
	public TipoMetaGeral getTipo() {
		return tipo;
	}

	public void setTipo(TipoMetaGeral tipo) {
		this.tipo = tipo;
	}

    
}
