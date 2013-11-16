package br.com.mendes.utils;

public enum CONSTANTS {
	METAS("metas"),
	NOME("nome"),
	SOBRENOME("sobrenome"),
	CUSTO("custo"),
	PRECOVENDA("precoVenda"),
	CATEGORIA("categoria");

	private String descricao;

	private CONSTANTS(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}

}
