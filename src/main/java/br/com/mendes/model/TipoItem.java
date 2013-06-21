package br.com.mendes.model;

public enum TipoItem {

	PRODUTO("Produto"),
	SERVICO("Servi�o");
	
	private String descricao;
	
	private TipoItem(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
