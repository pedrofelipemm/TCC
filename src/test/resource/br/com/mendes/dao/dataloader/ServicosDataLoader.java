package br.com.mendes.dao.dataloader;

import java.util.ArrayList;
import java.util.List;

import br.com.mendes.model.Servico;
import br.com.mendes.model.TipoServico;

public class ServicosDataLoader {

	public static List<Servico> createServicos() {
		List<Servico> servicos = new ArrayList<Servico>();

		servicos.add(new Servico("Setup 1", 100D, 150D, TipoServico.SETUP));
		servicos.add(new Servico("Setup 2", 100D, 150D, TipoServico.SETUP));
		servicos.add(new Servico("Setup 3", 100D, 150D, TipoServico.SETUP));

		servicos.add(new Servico("Colagem 1", 100D, 150D, TipoServico.COLAGEM));
		servicos.add(new Servico("Colagem 2", 100D, 150D, TipoServico.COLAGEM));
		servicos.add(new Servico("Colagem 3", 100D, 150D, TipoServico.COLAGEM));

		return servicos;
	}

}
