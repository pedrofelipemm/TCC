package br.com.mendes.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * 
 * @author Pedro
 */
@Entity
public class ItemPedido implements Serializable {

	private static final long serialVersionUID = -6324851405792712125L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cod;

	@ManyToOne
	private Item item;

	@ManyToOne
	private Pedido pedido;

	private int quantidade;

	public Double getTotal() {
		if (this.item == null || this.item.getPrecoVenda() == null || this.quantidade <= 0) {
			return null;
		}

		return this.item.getPrecoVenda() * this.quantidade;
	}

	public ItemPedido() {
	}

	public ItemPedido(Long cod, Item item, Pedido pedido, int quantidade) {
		this.cod = cod;
		this.item = item;
		this.pedido = pedido;
		this.quantidade = quantidade;
	}

	public ItemPedido(Item item, int quantidade) {
		this.item = item;
		this.quantidade = quantidade;
	}

	public int getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Pedido getPedido() {
		return this.pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Long getCod() {
		return this.cod;
	}

	public void setCod(Long cod) {
		this.cod = cod;
	}

	@Override
	public String toString() {
		return "ItemPedido [cod=" + this.cod + ", item=" + this.item + ", pedido="
				+ this.pedido + ", quantidade=" + this.quantidade + "]";
	}

}
