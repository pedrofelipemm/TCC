package br.com.mendes.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mendes.dto.QtdePeriodoDTO;
import br.com.mendes.model.Item;
import br.com.mendes.model.TipoItem;
import br.com.mendes.model.dao.ItemDAO;
import br.com.mendes.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	
	private static final long serialVersionUID = 2705497057114522401L;
	
	@Autowired
	private ItemDAO itemDAO;

	@Override
	@Transactional
	public List<Item> obterTodosItens() {
		return itemDAO.getAll();
	}
	
	@Override
	@Transactional
	public List<Item> buscarPorTipoECLiente(Long codCliente, TipoItem tipoItem) {
		return itemDAO.buscarPorTipoECLiente(codCliente, tipoItem);
	}

	@Override
	@Transactional
	public List<Item> buscarTodos(TipoItem tipoItem) {
		return itemDAO.buscarTodos(tipoItem);
	}
	
	@Override
	@Transactional
	public Item buscarPorCodigo(Long cod) {
		return itemDAO.getByCod(cod);
	}

	@Override
	@Transactional
	public List<QtdePeriodoDTO> obterQtdesItensEspecificosNosPeriodos(
			Long codItem, List<QtdePeriodoDTO> periodos) {
		

		for(QtdePeriodoDTO periodo : periodos) {
			Long qtde = this.obterQtdeItensEspecificosNoMesAno(codItem, periodo.getAno(), periodo.getMes());
			periodo.setQtde(qtde.doubleValue());
		}
		
		return periodos;
	}

	@Override
	@Transactional
	public Long obterQtdeItensEspecificosNoMesAno(Long codItem, Integer ano,
			Integer mes) {
		
		return itemDAO.obterMetaEspecificaNoAnoMes(codItem, ano, mes);
	}

	@Override
	@Transactional
	public List<QtdePeriodoDTO> obterQtdesItensGeralNosPeriodos(
			TipoItem tipoItem, List<QtdePeriodoDTO> periodos) {
		
		for(QtdePeriodoDTO periodo : periodos) {
			Long qtde = this.obterQtdeItensGeralNoMesAno(tipoItem, periodo.getAno(), periodo.getMes());
			periodo.setQtde(qtde.doubleValue());
		}
		
		return periodos;
		
	}

	@Override
	@Transactional
	public Long obterQtdeItensGeralNoMesAno(TipoItem tipoItem,
			Integer ano, Integer mes) {
		return itemDAO.obterQtdeGeralNoAnoMes(tipoItem, ano, mes);
	}

	@Override
	@Transactional
	public List<QtdePeriodoDTO> obterQtdesItensGeralValorNosPeriodos(
			TipoItem tipoItem, List<QtdePeriodoDTO> periodos) {
		
		for(QtdePeriodoDTO periodo : periodos) {
			Double qtde = this.obterQtdeItensGeralValorNoMesAno(tipoItem, periodo.getAno(), periodo.getMes());
			periodo.setQtde(qtde);
		}
		
		return periodos;
	}

	@Transactional
	private Double obterQtdeItensGeralValorNoMesAno(TipoItem tipoItem,
			Integer ano, Integer mes) {
		return itemDAO.obterQtdeGeralValorNoAnoMes(tipoItem, ano, mes);
	}
	
}
