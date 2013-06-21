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

	@ManyToOne
	private Cliente cliente;

	@Temporal(TemporalType.DATE)
	private Date dataEmissao;

	@OneToMany(mappedBy="pedido")
	private List<ItemPedido> itensPedido;

	private Double valorTotal;

	private StatusPedido status;

	public Pedido() {
	}



	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}


	public List<ItemPedido> getItensPedido() {
		return itensPedido;
	}

	public void setItensPedido(List<ItemPedido> itensPedido) {
		this.itensPedido = itensPedido;
	}

	public Long getCod() {
		return cod;
	}

	public void setCod(Long cod) {
		this.cod = cod;
	}



	public StatusPedido getStatus() {
		return status;
	}



	public void setStatus(StatusPedido status) {
		this.status = status;
	}



	@Override
	public String toString() {
		return "Pedido [cod=" + cod + ", cliente=" + cliente + ", dataEmissao="
				+ dataEmissao + ", valorTotal=" + valorTotal + ", status=" + status + "]";
	}

	
}
