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
@DiscriminatorValue(value = "SERVICO")
public class Servico extends Item implements Serializable {

	private static final long serialVersionUID = -3132222079509387646L;

	@Enumerated(EnumType.STRING)
	private TipoServico tipoServico;

	public TipoServico getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}
}
