package br.com.mendes.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Endereco implements Serializable {

	private static final long serialVersionUID = 8013789718232084977L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codEndereco;

	@NotNull
	@Column(nullable = false)
	private String logradouro;

	@NotNull
	@Column(nullable = false)
	private Integer numero;

	@NotNull
	@Column(nullable = false)
	private String bairro;

	@NotNull
	@Column(nullable = false)
	private String cidade;

	@NotNull
	@Column(nullable = false)
	private String cep;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Estado estado;

	@NotNull
	@Column(nullable = false)
	private String pais;

	public Endereco() {
	}

	public Endereco(String logradouro, Integer numero, String bairro, String cidade, String cep, Estado estado, String pais) {
		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
		this.cep = cep;
		this.estado = estado;
		this.pais = pais;
	}

	public Long getCodEndereco() {
		return this.codEndereco;
	}

	public void setCodEndereco(Long codEndereco) {
		this.codEndereco = codEndereco;
	}

	public String getLogradouro() {
		return this.logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public Integer getNumero() {
		return this.numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return this.bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return this.cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getCep() {
		return this.cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.bairro == null) ? 0 : this.bairro.hashCode());
		result = (prime * result) + ((this.cep == null) ? 0 : this.cep.hashCode());
		result = (prime * result) + ((this.cidade == null) ? 0 : this.cidade.hashCode());
		result = (prime * result)
				+ ((this.codEndereco == null) ? 0 : this.codEndereco.hashCode());
		result = (prime * result) + ((this.estado == null) ? 0 : this.estado.hashCode());
		result = (prime * result)
				+ ((this.logradouro == null) ? 0 : this.logradouro.hashCode());
		result = (prime * result) + ((this.numero == null) ? 0 : this.numero.hashCode());
		result = (prime * result) + ((this.pais == null) ? 0 : this.pais.hashCode());
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
		Endereco other = (Endereco) obj;
		if (this.bairro == null) {
			if (other.bairro != null) {
				return false;
			}
		} else if (!this.bairro.equals(other.bairro)) {
			return false;
		}
		if (this.cep == null) {
			if (other.cep != null) {
				return false;
			}
		} else if (!this.cep.equals(other.cep)) {
			return false;
		}
		if (this.cidade == null) {
			if (other.cidade != null) {
				return false;
			}
		} else if (!this.cidade.equals(other.cidade)) {
			return false;
		}
		if (this.codEndereco == null) {
			if (other.codEndereco != null) {
				return false;
			}
		} else if (!this.codEndereco.equals(other.codEndereco)) {
			return false;
		}
		if (this.estado == null) {
			if (other.estado != null) {
				return false;
			}
		} else if (!this.estado.equals(other.estado)) {
			return false;
		}
		if (this.logradouro == null) {
			if (other.logradouro != null) {
				return false;
			}
		} else if (!this.logradouro.equals(other.logradouro)) {
			return false;
		}
		if (this.numero == null) {
			if (other.numero != null) {
				return false;
			}
		} else if (!this.numero.equals(other.numero)) {
			return false;
		}
		if (this.pais == null) {
			if (other.pais != null) {
				return false;
			}
		} else if (!this.pais.equals(other.pais)) {
			return false;
		}
		return true;
	}


}