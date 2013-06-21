package br.com.mendes.service;

import java.util.List;

import br.com.mendes.model.Endereco;

public interface EnderecoService {

	Endereco obterEnderecoPorCod(Long codEndereco);

	List<Endereco> obterTodosEnderecos();

	Endereco criarEndereco(Endereco endereco);
}
