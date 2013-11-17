package br.com.mendes.utils;

public enum CONSTANTS {
	CATEGORIA("categoria"),
	CLIENTENOME("cliente.nome"),
	CUSTO("custo"),
	DATAEMISSAO("dataEmissao"),
	METAS("metas"),
	NOME("nome"),
	NOTA("nota"),
	PEDIDOCLIENTE("pedido.cliente"),
	PRECOVENDA("precoVenda"),
	SOBRENOME("sobrenome"),
	TIPOATENDIMENTO("tipoAtendimento"),
	TIPOSERVICO("tipoServico"),
	VALORTOTAL("valorTotal");

	private String descricao;

	private CONSTANTS(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}

}
