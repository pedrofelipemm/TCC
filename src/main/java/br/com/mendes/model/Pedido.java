package br.com.mendes.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author Pedro
 */
@Entity
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1962890005427364591L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cod;

	@NotNull
	@ManyToOne
	private Cliente cliente;

	@Temporal(TemporalType.DATE)
	private Date dataEmissao;

	@OneToMany(mappedBy = "pedido")
	private List<ItemPedido> itensPedido;

	@NotNull
	private Double valorTotal;

	private StatusPedido status;

	public Pedido() {
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getDataEmissao() {
		return this.dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Double getValorTotal() {
		return this.valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public List<ItemPedido> getItensPedido() {
		return this.itensPedido;
	}

	public void setItensPedido(List<ItemPedido> itensPedido) {
		this.itensPedido = itensPedido;
	}

	public Long getCod() {
		return this.cod;
	}

	public void setCod(Long cod) {
		this.cod = cod;
	}

	public StatusPedido getStatus() {
		return this.status;
	}

	public void setStatus(StatusPedido status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Pedido [cod=" + this.cod + ", cliente=" + this.cliente + ", dataEmissao="
				+ this.dataEmissao + ", valorTotal=" + this.valorTotal + ", status=" + this.status + "]";
	}

}
