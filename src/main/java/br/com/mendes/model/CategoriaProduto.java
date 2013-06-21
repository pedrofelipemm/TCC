package br.com.mendes.model;

public enum CategoriaProduto {

	INSTRUMENTO("Instrumento"),
	ELETRONICO("Eletr�nico");
	
	private String descricao;
	
	private CategoriaProduto(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
