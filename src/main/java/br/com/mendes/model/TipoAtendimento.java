package br.com.mendes.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public enum TipoAtendimento {

	EMAIL("Email"),
	PESSOAL("Pessoalmente"),
	TELEFONE("Telefone");

	private String descricao;

	private TipoAtendimento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public String getLabel() {
		return this.descricao;
	}

	public String getName() {
		return this.name();
	}

	public static List<TipoAtendimento> getEnums(String value) {

		if (value == null) {
			return null;
		}

		List<TipoAtendimento> tipos = new ArrayList<TipoAtendimento>();
		for (TipoAtendimento tipo : values()) {
			if (StringUtils.containsIgnoreCase(tipo.getDescricao(), value)) {
				tipos.add(tipo);
			}
		}

		return tipos;
	}
}
