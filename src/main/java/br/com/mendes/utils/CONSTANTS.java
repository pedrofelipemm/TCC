package br.com.mendes.utils;

public enum CONSTANTS {
	METAS("metas"),
	NOME("nome"),
	SOBRENOME("sobrenome");

	private String descricao;

	private CONSTANTS() {
	}

	private CONSTANTS(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
