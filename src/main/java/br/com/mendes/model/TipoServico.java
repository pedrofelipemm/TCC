package br.com.mendes.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public enum TipoServico {

	SETUP("SetUp"),
	TRASTE("Traste"),
	ESCALOPAGEM("Escalopagem"),
	TROCACORA("Troca de Corda"),
	COLAGEM("Colagem"),
	ENCORDAMENTO("Encordamento");

	private String descricao;

	private TipoServico(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public static List<TipoServico> getEnums(String value) {

		if (value == null) {
			return null;
		}

		List<TipoServico> tipos = new ArrayList<TipoServico>();
		for (TipoServico tipo : values()) {
			if (StringUtils.containsIgnoreCase(tipo.getDescricao(), value)) {
				tipos.add(tipo);

			}
		}

		return tipos;
	}
}
