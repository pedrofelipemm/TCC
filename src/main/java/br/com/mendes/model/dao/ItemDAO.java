package br.com.mendes.model.dao;

import java.util.List;

import br.com.mendes.model.Item;
import br.com.mendes.model.TipoItem;


public interface ItemDAO extends DAO<Item, Long>{

	List<Item> buscarPorTipoECLiente(Long codCliente, TipoItem tipoItem);

	List<Item> buscarTodos(TipoItem tipoItem);

	Long obterMetaEspecificaNoAnoMes(Long codItem, Integer ano, Integer mes);

	Long obterQtdeGeralNoAnoMes(TipoItem tipoItem, Integer ano, Integer mes);

	Double obterQtdeGeralValorNoAnoMes(TipoItem tipoItem, Integer ano, Integer mes);

}
