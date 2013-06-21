package br.com.mendes.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author Pedro
 */
@Entity
public class Feedback implements Serializable {

	private static final long serialVersionUID = -152013219739879171L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codFeedback;

	@ManyToOne(optional = false)
	private Cliente cliente;

	@ManyToOne(optional = false)
	private Item item;

	@Enumerated(EnumType.STRING)
	private TipoAtendimento tipoAtendimento;

	private Double nota;

	private String observacao;

	@Temporal(TemporalType.DATE)
	private Date dataFeedback;

	public Feedback() {
	}

	public Feedback(Long codFeedback, Cliente cliente, Item item, Double nota, TipoAtendimento tipoAtendimento,
			String observacao, Date dataFeedback) {
		this.codFeedback = codFeedback;
		this.cliente = cliente;
		this.setItem(item);
		this.nota = nota;
		this.tipoAtendimento = tipoAtendimento;
		this.observacao = observacao;
		this.dataFeedback = dataFeedback;
	}

	public Long getCodFeedback() {
		return codFeedback;
	}

	public void setCodFeedback(Long codFeedback) {
		this.codFeedback = codFeedback;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

	public TipoAtendimento getTipoAtendimento() {
		return tipoAtendimento;
	}

	public void setTipoAtendimento(TipoAtendimento tipoAtendimento) {
		this.tipoAtendimento = tipoAtendimento;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Date getDataFeedback() {
		return dataFeedback;
	}

	public void setDataFeedback(Date dataFeedback) {
		this.dataFeedback = dataFeedback;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	
	
}
