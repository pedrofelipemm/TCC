package br.com.mendes.dao.dataloader;

import java.util.ArrayList;
import java.util.List;

import br.com.mendes.model.Servico;
import br.com.mendes.model.TipoServico;

public class ServicosDataLoader {

	public static List<Servico> createServicos() {
		List<Servico> servicos = new ArrayList<Servico>();

		servicos.add(new Servico("Setup Violino", 50D, 70D, TipoServico.SETUP));
		servicos.add(new Servico("Setup Guitarra", 550D, 80D, TipoServico.SETUP));

		servicos.add(new Servico("Troca de Traste Jumbo", 150D, 190D, TipoServico.TRASTE));
		servicos.add(new Servico("Troca de Traste Médio", 160D, 210D, TipoServico.TRASTE));

		servicos.add(new Servico("Colagem Corpo Violão", 30D, 55D, TipoServico.COLAGEM));
		servicos.add(new Servico("Colagem Cavalete Reforçado", 400D, 70D, TipoServico.COLAGEM));

		servicos.add(new Servico("Encordamento Baixo", 60D, 95D, TipoServico.ENCORDAMENTO));
		servicos.add(new Servico("Encordamento Guitarra", 30D, 60D, TipoServico.ENCORDAMENTO));

		return servicos;
	}

}
