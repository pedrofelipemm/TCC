package br.com.mendes.service;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;

import br.com.mendes.model.Servico;

public interface ServicoService {

	public abstract Servico obterServicoPorCod(Long codServico);

	public abstract List<Servico> obterTodosServicos();

	public abstract void criarServico(Servico servico);

	public abstract Long countBy();

	public abstract List<Servico> obterTodosServicosPaginados(Integer first, Integer pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters);
}
