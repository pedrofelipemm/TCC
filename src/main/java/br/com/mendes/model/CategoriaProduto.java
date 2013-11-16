package br.com.mendes.model;

import org.apache.commons.lang3.StringUtils;

public enum CategoriaProduto {

	INSTRUMENTO("Instrumento"),
	ELETRONICO("Eletrônico");

	private String descricao;

	private CategoriaProduto(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public static CategoriaProduto getEnum(String value) {

		if (value == null) {
			throw new IllegalArgumentException();
		}

		for (CategoriaProduto categoriaProduto : values()) {
			if (StringUtils.containsIgnoreCase(categoriaProduto.getDescricao(), value)) {
				return categoriaProduto;
			}
		}

		return null;
	}
}
