package br.com.mendes.dto;

import java.io.Serializable;
import java.util.List;

public class ItemValorDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5214666843794369162L;

	private String descricao;
	private List<Integer> valores;
	private Integer meta;

	public ItemValorDTO(String descricao, List<Integer> valores, Integer meta) {
		this.descricao = descricao;
		this.valores = valores;
		this.meta = meta;
	}

	public ItemValorDTO() {
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Integer> getValores() {
		return this.valores;
	}

	public void setValores(List<Integer> valores) {
		this.valores = valores;
	}

	public Integer getMeta() {
		return this.meta;
	}

	public void setMeta(Integer meta) {
		this.meta = meta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.descricao == null) ? 0 : this.descricao.hashCode());
		result = prime * result + ((this.meta == null) ? 0 : this.meta.hashCode());
		result = prime * result + ((this.valores == null) ? 0 : this.valores.hashCode());
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
		ItemValorDTO other = (ItemValorDTO) obj;
		if (this.descricao == null) {
			if (other.descricao != null) {
				return false;
			}
		} else if (!this.descricao.equals(other.descricao)) {
			return false;
		}
		if (this.meta == null) {
			if (other.meta != null) {
				return false;
			}
		} else if (!this.meta.equals(other.meta)) {
			return false;
		}
		if (this.valores == null) {
			if (other.valores != null) {
				return false;
			}
		} else if (!this.valores.equals(other.valores)) {
			return false;
		}
		return true;
	}

}
