package br.com.mendes.dao.dataloader;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import br.com.mendes.model.MetaGeral;
import br.com.mendes.model.TipoMetaGeral;

public class MetasGeraisDataLoader {

	public static List<MetaGeral> createMetasGerais() {
		List<MetaGeral> metas = new ArrayList<MetaGeral>();

		criarMetasClientes(metas);
		criarMetasEmail(metas);
		criarMetasPessoa(metas);
		criarMetasTelefone(metas);
		criarMetasProdutos(metas);
		criarMetasServicos(metas);

		return metas;
	}

	private static void criarMetasTelefone(List<MetaGeral> metas) {
		metas.add(new MetaGeral(5D, new GregorianCalendar(2013, 0, 1).getTime(), TipoMetaGeral.FEEDBACK_TELEFONE));
		metas.add(new MetaGeral(7D, new GregorianCalendar(2013, 3, 1).getTime(), TipoMetaGeral.FEEDBACK_TELEFONE));
		metas.add(new MetaGeral(5D, new GregorianCalendar(2013, 6, 1).getTime(), TipoMetaGeral.FEEDBACK_TELEFONE));
		metas.add(new MetaGeral(3D, new GregorianCalendar(2013, 9, 1).getTime(), TipoMetaGeral.FEEDBACK_TELEFONE));
		metas.add(new MetaGeral(5D, new GregorianCalendar(2013, 10, 1).getTime(), TipoMetaGeral.FEEDBACK_TELEFONE));
	}

	private static void criarMetasPessoa(List<MetaGeral> metas) {
		metas.add(new MetaGeral(5D, new GregorianCalendar(2013, 0, 1).getTime(), TipoMetaGeral.FEEDBACK_PESSOAL));
		metas.add(new MetaGeral(7D, new GregorianCalendar(2013, 3, 1).getTime(), TipoMetaGeral.FEEDBACK_PESSOAL));
		metas.add(new MetaGeral(5D, new GregorianCalendar(2013, 6, 1).getTime(), TipoMetaGeral.FEEDBACK_PESSOAL));
		metas.add(new MetaGeral(3D, new GregorianCalendar(2013, 9, 1).getTime(), TipoMetaGeral.FEEDBACK_PESSOAL));
		metas.add(new MetaGeral(5D, new GregorianCalendar(2013, 10, 1).getTime(), TipoMetaGeral.FEEDBACK_PESSOAL));
	}

	private static void criarMetasEmail(List<MetaGeral> metas) {
		metas.add(new MetaGeral(5D, new GregorianCalendar(2013, 0, 1).getTime(), TipoMetaGeral.FEEDBACK_EMAIL));
		metas.add(new MetaGeral(7D, new GregorianCalendar(2013, 3, 1).getTime(), TipoMetaGeral.FEEDBACK_EMAIL));
		metas.add(new MetaGeral(5D, new GregorianCalendar(2013, 6, 1).getTime(), TipoMetaGeral.FEEDBACK_EMAIL));
		metas.add(new MetaGeral(3D, new GregorianCalendar(2013, 9, 1).getTime(), TipoMetaGeral.FEEDBACK_EMAIL));
		metas.add(new MetaGeral(5D, new GregorianCalendar(2013, 10, 1).getTime(), TipoMetaGeral.FEEDBACK_EMAIL));
	}

	private static void criarMetasServicos(List<MetaGeral> metas) {
		metas.add(new MetaGeral(5D, new GregorianCalendar(2013, 0, 1).getTime(), TipoMetaGeral.SERVICO));
		metas.add(new MetaGeral(7D, new GregorianCalendar(2013, 3, 1).getTime(), TipoMetaGeral.SERVICO));
		metas.add(new MetaGeral(5D, new GregorianCalendar(2013, 6, 1).getTime(), TipoMetaGeral.SERVICO));
		metas.add(new MetaGeral(3D, new GregorianCalendar(2013, 9, 1).getTime(), TipoMetaGeral.SERVICO));
		metas.add(new MetaGeral(5D, new GregorianCalendar(2013, 10, 1).getTime(), TipoMetaGeral.SERVICO));
	}

	private static void criarMetasProdutos(List<MetaGeral> metas) {
		metas.add(new MetaGeral(5D, new GregorianCalendar(2013, 0, 1).getTime(), TipoMetaGeral.PRODUTO));
		metas.add(new MetaGeral(7D, new GregorianCalendar(2013, 3, 1).getTime(), TipoMetaGeral.PRODUTO));
		metas.add(new MetaGeral(5D, new GregorianCalendar(2013, 6, 1).getTime(), TipoMetaGeral.PRODUTO));
		metas.add(new MetaGeral(3D, new GregorianCalendar(2013, 9, 1).getTime(), TipoMetaGeral.PRODUTO));
		metas.add(new MetaGeral(5D, new GregorianCalendar(2013, 10, 1).getTime(), TipoMetaGeral.PRODUTO));
	}

	private static void criarMetasClientes(List<MetaGeral> metas) {
		metas.add(new MetaGeral(5D, new GregorianCalendar(2013, 0, 1).getTime(), TipoMetaGeral.CLIENTE));
		metas.add(new MetaGeral(7D, new GregorianCalendar(2013, 3, 1).getTime(), TipoMetaGeral.CLIENTE));
		metas.add(new MetaGeral(5D, new GregorianCalendar(2013, 6, 1).getTime(), TipoMetaGeral.CLIENTE));
		metas.add(new MetaGeral(3D, new GregorianCalendar(2013, 9, 1).getTime(), TipoMetaGeral.CLIENTE));
		metas.add(new MetaGeral(5D, new GregorianCalendar(2013, 10, 1).getTime(), TipoMetaGeral.CLIENTE));
	}

}
