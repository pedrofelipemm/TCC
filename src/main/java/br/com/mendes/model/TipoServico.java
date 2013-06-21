package br.com.mendes.model;

public enum TipoServico {

	SETUP("SetUp"),
	TRASTE("Traste"),
	ESCALOPAGEM("Escalopagem"),
	TROCACORA("Troca de Corda"),
	COLAGEM("Colagem");
	
	private String descricao;
	
	private TipoServico(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
