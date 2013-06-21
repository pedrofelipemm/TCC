package br.com.mendes.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mendes.dto.QtdePeriodoDTO;
import br.com.mendes.model.Cliente;
import br.com.mendes.model.dao.ClienteDAO;
import br.com.mendes.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

	private static final long serialVersionUID = -2496241306168858374L;
	
	@Autowired
	private ClienteDAO clienteDAO;

	@Override
	@Transactional
	public Cliente obterClientePorCod(Long codCliente) {
		return clienteDAO.getByCod(codCliente);
	}

	@Override
	@Transactional
	public List<Cliente> obterTodosCliente() {
		return clienteDAO.getAll();
	}

	@Override
	@Transactional
	public Cliente criarCliente(Cliente cliente) {
		return clienteDAO.saveUpdateGetEntity(cliente);
	}
	
	@Override
	@Transactional
	public Long obterQtdeClientesNoAnoMes(Integer ano, Integer mes) {
		return clienteDAO.obterQtdeClientesNoAnoMes(ano, mes);
	}

	@Override
	@Transactional(readOnly=true)
	public List<QtdePeriodoDTO> obterQtdesClientesNosPeriodos(
			List<QtdePeriodoDTO> periodos) {
		
		for(QtdePeriodoDTO periodo : periodos) {
			Long qtde = this.obterQtdeClientesNoAnoMes(periodo.getAno(), periodo.getMes());
			periodo.setQtde(qtde.doubleValue());
		}
				
		return periodos;
	}
}
