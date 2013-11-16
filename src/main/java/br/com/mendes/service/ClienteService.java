package br.com.mendes.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;

import br.com.mendes.dto.QtdePeriodoDTO;
import br.com.mendes.model.Cliente;

public interface ClienteService extends Serializable {

	public abstract Cliente obterClientePorCod(Long codCliente);

	public abstract List<Cliente> obterTodosCliente();

	public abstract List<Cliente> obterTodosClientesPaginados(Integer first, Integer pageSize, SortOrder sortOrder, String sortField, Map<String, String> filters);

	public abstract void criarCliente(Cliente cliente);

	public abstract Long obterQtdeClientesNoAnoMes(Integer ano, Integer mes);

	public abstract List<QtdePeriodoDTO> obterQtdesClientesNosPeriodos(
			List<QtdePeriodoDTO> periodos);

	public abstract Long countBy();

}
