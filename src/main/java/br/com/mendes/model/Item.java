package br.com.mendes.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

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

	@NotNull
	@Column(nullable = false)
	private String nome;

	@NotNull
	@Column(nullable = false)
	private Double custo;

	@NotNull
	@Column(nullable = false)
	private Double precoVenda;

	@OneToMany(mappedBy = "item")
	private List<MetaEspecifica> metas;

	@OneToMany(mappedBy = "item")
	private List<ItemPedido> itensPedido;

	public Item() {
	}

	public Item(String nome, Double custo, Double precoVenda) {
		this.nome = nome;
		this.custo = custo;
		this.precoVenda = precoVenda;
	}

	public Item(Long codItem) {
		this.cod = codItem;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPrecoVenda() {
		return this.precoVenda;
	}

	public void setPrecoVenda(Double precoVenda) {
		this.precoVenda = precoVenda;
	}

	public List<Feedback> getFeedbacks() {
		return this.feedbacks;
	}

	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	public List<MetaEspecifica> getMetas() {
		return this.metas;
	}

	public void setMetas(List<MetaEspecifica> metas) {
		this.metas = metas;
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

	@Override
	public String toString() {
		return "Item [cod=" + this.cod + ", nome=" + this.nome + ", custo="
				+ this.custo + ", precoVenda=" + this.precoVenda + "]";
	}

	public Double getCusto() {
		return this.custo;
	}

	public void setCusto(Double custo) {
		this.custo = custo;
	}

}