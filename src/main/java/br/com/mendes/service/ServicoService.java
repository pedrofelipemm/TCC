package br.com.mendes.service;

import java.util.List;

import br.com.mendes.model.Servico;

public interface ServicoService {

	public abstract Servico obterServicoPorCod(Long codServico);

	public abstract List<Servico> obterTodosServicos();

	public abstract void criarServico(Servico servico);

	public abstract Long countBy(String filter);

	public abstract List<Servico> obterTodosServicosPaginados(String filter, Integer first, Integer pageSize);
}
