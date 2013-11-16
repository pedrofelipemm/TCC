package br.com.mendes.model;

import java.util.ArrayList;
import java.util.List;

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

	public static List<CategoriaProduto> getEnums(String value) {

		if (value == null) {
			return null;
		}

		List<CategoriaProduto> tipos = new ArrayList<CategoriaProduto>();
		for (CategoriaProduto categoria : values()) {
			if (StringUtils.containsIgnoreCase(categoria.getDescricao(), value)) {
				tipos.add(categoria);
			}
		}

		return tipos;
	}
}
