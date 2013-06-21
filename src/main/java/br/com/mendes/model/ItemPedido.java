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

    public ItemPedido() {
    }

    public ItemPedido(Long cod, Item item, Pedido pedido, int quantidade) {
        this.cod=cod;
        this.item=item;
        this.pedido=pedido;
        this.quantidade = quantidade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Long getCod() {
		return cod;
	}

	public void setCod(Long cod) {
		this.cod = cod;
	}

	@Override
	public String toString() {
		return "ItemPedido [cod=" + cod + ", item=" + item + ", pedido="
				+ pedido + ", quantidade=" + quantidade + "]";
	}
    
    
}

