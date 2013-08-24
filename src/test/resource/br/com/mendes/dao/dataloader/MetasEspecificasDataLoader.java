package br.com.mendes.dao.dataloader;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.mendes.model.Item;
import br.com.mendes.model.MetaEspecifica;

public class MetasEspecificasDataLoader {

	public static List<MetaEspecifica> createMetasEspecificas(List<? extends Item> itens) {
		List<MetaEspecifica> metas = new ArrayList<MetaEspecifica>();

		for (Item item : itens) {
			metas.add(new MetaEspecifica(10D, new Date(), item));
		}

		return metas;
	}

}
