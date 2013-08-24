package br.com.mendes.dao.dataloader;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.mendes.model.MetaGeral;
import br.com.mendes.model.TipoMetaGeral;

public class MetasGeraisDataLoader {

	public static List<MetaGeral> createMetasGerais() {
		List<MetaGeral> metas = new ArrayList<MetaGeral>();

		metas.add(new MetaGeral(10D, new Date(), TipoMetaGeral.CLIENTE));
		metas.add(new MetaGeral(10D, new Date(), TipoMetaGeral.FEEDBACK_EMAIL));
		metas.add(new MetaGeral(10D, new Date(), TipoMetaGeral.FEEDBACK_PESSOAL));
		metas.add(new MetaGeral(10D, new Date(), TipoMetaGeral.FEEDBACK_TELEFONE));
		metas.add(new MetaGeral(10D, new Date(), TipoMetaGeral.PRODUTO));
		metas.add(new MetaGeral(10D, new Date(), TipoMetaGeral.SERVICO));

		return metas;
	}

}
