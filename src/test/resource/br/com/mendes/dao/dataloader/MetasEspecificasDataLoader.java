package br.com.mendes.dao.dataloader;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import br.com.mendes.model.Item;
import br.com.mendes.model.MetaEspecifica;

public class MetasEspecificasDataLoader {

	public static List<MetaEspecifica> createMetasEspecificas(List<? extends Item> itens) {
		List<MetaEspecifica> metas = new ArrayList<MetaEspecifica>();
		List<Date> datas = criarDatasMetas();
		List<Double> valores = criarValoresMetas();
		int x = 0;

		for (Item item : itens) {
			while (x < 5) {
				metas.add(new MetaEspecifica(valores.get(x), datas.get(x), item));
				x++;
			}
			x = 0;
		}

		return metas;
	}

	private static List<Double> criarValoresMetas() {
		List<Double> valores = new ArrayList<Double>();

		valores.add(5D);
		valores.add(7D);
		valores.add(5D);
		valores.add(3D);
		valores.add(5D);

		return valores;
	}

	private static List<Date> criarDatasMetas() {
		List<Date> datas = new ArrayList<Date>();

		datas.add(new GregorianCalendar(2013, 0, 1).getTime());
		datas.add(new GregorianCalendar(2013, 3, 1).getTime());
		datas.add(new GregorianCalendar(2013, 6, 1).getTime());
		datas.add(new GregorianCalendar(2013, 9, 1).getTime());
		datas.add(new GregorianCalendar(2013, 10, 1).getTime());

		return datas;
	}

}
