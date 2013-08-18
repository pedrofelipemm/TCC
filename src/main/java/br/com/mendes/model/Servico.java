package br.com.mendes.model;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue(value = "SERVICO")
public class Servico extends Item implements Serializable {

	private static final long serialVersionUID = -3132222079509387646L;

	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoServico tipoServico;

	public TipoServico getTipoServico() {
		return this.tipoServico;
	}

	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}
}
