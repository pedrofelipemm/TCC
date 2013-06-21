package br.com.mendes.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mendes.model.Servico;
import br.com.mendes.model.dao.ServicoDAO;
import br.com.mendes.service.ServicoService;

@Service
public class ServicoServiceImpl implements ServicoService {

	@Autowired
	private ServicoDAO servicoDAO;

	@Override
	@Transactional
	public Servico obterServicoPorCod(Long codServico) {
		return servicoDAO.getByCod(codServico);
	}

	@Override
	@Transactional
	public List<Servico> obterTodosServicos() {
		return servicoDAO.getAll();
	}

	@Override
	@Transactional
	public Servico criarServico(Servico servico) {
		return servicoDAO.saveUpdateGetEntity(servico);
	}
}
