package br.com.mendes.model;

public enum TipoItem {

	PRODUTO("Produto"),
	SERVICO("Serviço");

	private String descricao;

	private TipoItem(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}

}
