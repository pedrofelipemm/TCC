package br.com.mendes.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

@Entity
public class Cliente implements Serializable {

	private static final long serialVersionUID = 8074799849728052316L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codCliente;

	@NotNull
	@Column(nullable = false)
	private String nome;

	@NotNull
	@Column(nullable = false)
	private String sobrenome;

	@OneToOne
	private Endereco endereco;

	private String telefone;

	private String celular;

	@NotNull
	@Column(nullable = false, unique = true)
	private String rg;

	@CPF
	@NotNull
	@Column(nullable = false, unique = true)
	private String cpf;

	@OneToMany(mappedBy = "cliente")
	private List<Feedback> feedbacks;

	@Temporal(TemporalType.DATE)
	private Date dataNascimento;

	@Temporal(TemporalType.DATE)
	private Date dataCadastro;

	@OneToMany(mappedBy = "cliente")
	private List<Pedido> pedidos;

	public Cliente() {
	}

	public Cliente(String nome, String sobrenome, String rg, String cpf, Date dataCadastro) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.rg = rg;
		this.cpf = cpf;
		this.dataCadastro = dataCadastro;
	}

	public Cliente(Long codCliente) {
		this.codCliente = codCliente;
	}

	public Cliente(Long codCliente, String nome, String sobrenome,
			Endereco endereco, String telefone, String celular, String rg,
			String cpf, Date dataNascimento, Date dataCadastro) {
		this.codCliente = codCliente;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.telefone = telefone;
		this.endereco = endereco;
		this.celular = celular;
		this.rg = rg;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.dataCadastro = dataCadastro;
	}

	public Cliente(Long codCliente, String nome, String sobrenome) {
		this.codCliente = codCliente;
		this.nome = nome;
		this.sobrenome = sobrenome;
	}

	public Cliente(String nome, String sobrenome, String telefone, String celular, String rg, String cpf,
			Date dataNascimento, Date dataCadastro) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.telefone = telefone;
		this.celular = celular;
		this.rg = rg;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.dataCadastro = dataCadastro;
	}

	public Long getCodCliente() {
		return this.codCliente;
	}

	public void setCodCliente(Long codCliente) {
		this.codCliente = codCliente;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return this.sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return this.celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getRg() {
		return this.rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<Feedback> getFeedbacks() {
		return this.feedbacks;
	}

	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	public Date getDataNascimento() {
		return this.dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Date getDataCadastro() {
		return this.dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Endereco getEndereco() {
		return this.endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Pedido> getPedidos() {
		return this.pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result)
				+ ((this.codCliente == null) ? 0 : this.codCliente.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Cliente other = (Cliente) obj;
		if (this.codCliente == null) {
			if (other.codCliente != null) {
				return false;
			}
		} else if (!this.codCliente.equals(other.codCliente)) {
			return false;
		}
		return true;
	}

}
