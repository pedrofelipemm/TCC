package br.com.mendes.service;

import java.util.List;

import br.com.mendes.model.Endereco;

public interface EnderecoService {

	public abstract Endereco obterEnderecoPorCod(Long codEndereco);

	public abstract List<Endereco> obterTodosEnderecos();

	public abstract void criarEndereco(Endereco endereco);
}
