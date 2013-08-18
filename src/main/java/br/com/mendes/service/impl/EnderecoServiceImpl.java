package br.com.mendes.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mendes.model.Endereco;
import br.com.mendes.model.dao.EnderecoDAO;
import br.com.mendes.service.EnderecoService;


@Service("enderecoService")
public class EnderecoServiceImpl implements EnderecoService {

	@Autowired
	private EnderecoDAO enderecoDAO;

	@Override
	@Transactional
	public Endereco obterEnderecoPorCod(Long codEndereco) {
		return this.enderecoDAO.getByCod(codEndereco);
	}

	@Override
	@Transactional
	public List<Endereco> obterTodosEnderecos() {
		return this.enderecoDAO.getAll();
	}

	@Override
	@Transactional
	public void criarEndereco(Endereco endereco) {
		this.enderecoDAO.saveUpdateGetEntity(endereco);
	}
}
