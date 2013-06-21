package br.com.mendes.model;

public enum TipoAtendimento {

	EMAIL("Email"),
	PESSOAL("Pessoalmente"),
	TELEFONE("Telefone");
	
	private String descricao;
	
	private TipoAtendimento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public String getLabel() {
		return descricao;
	}
	
	public String getName() {
		return this.name();
	}
}
