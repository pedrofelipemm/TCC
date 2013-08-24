package br.com.mendes.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author Pedro
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TIPO_META", discriminatorType = DiscriminatorType.STRING)
public abstract class Meta implements Serializable {

	private static final long serialVersionUID = -6898740632390285434L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codMeta;

	@Column(nullable = false)
	private Double valor;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInicio;

	public Meta() {
	}

	public Meta(Double valor, Date dataInicio) {
		this.valor = valor;
		this.dataInicio = dataInicio;
	}

	public Long getCodMeta() {
		return this.codMeta;
	}

	public void setCodMeta(Long codMeta) {
		this.codMeta = codMeta;
	}

	public Double getValor() {
		return this.valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Date getDataInicio() {
		return this.dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

}
