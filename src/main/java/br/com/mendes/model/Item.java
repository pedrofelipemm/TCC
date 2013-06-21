package br.com.mendes.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TIPO_ITEM", discriminatorType = DiscriminatorType.STRING)
public class Item implements Serializable {

	private static final long serialVersionUID = 6625146880168473723L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cod;

	@OneToMany(mappedBy = "item")
	private List<Feedback> feedbacks;

	private String nome;

	private Double precoCusto;

	private Double precoVenda;
	
	@OneToMany(mappedBy="item")
	private List<MetaEspecifica> metas;

	@OneToMany(mappedBy = "item")
	private List<ItemPedido> itensPedido;

	public Item() {
		
	}
	
	public Item(Long codItem) {
		this.cod = codItem;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPrecoCusto() {
		return precoCusto;
	}

	public void setPrecoCusto(Double precoCusto) {
		this.precoCusto = precoCusto;
	}

	public Double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(Double precoVenda) {
		this.precoVenda = precoVenda;
	}

	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	public List<MetaEspecifica> getMetas() {
		return metas;
	}

	public void setMetas(List<MetaEspecifica> metas) {
		this.metas = metas;
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

	@Override
	public String toString() {
		return "Item [cod=" + cod + ", nome=" + nome + ", precoCusto="
				+ precoCusto + ", precoVenda=" + precoVenda + "]";
	}

	
	
	
}
