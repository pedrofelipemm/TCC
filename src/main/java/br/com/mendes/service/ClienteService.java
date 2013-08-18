package br.com.mendes.service;

import java.io.Serializable;
import java.util.List;

import br.com.mendes.dto.QtdePeriodoDTO;
import br.com.mendes.model.Cliente;

public interface ClienteService extends Serializable {

	public abstract Cliente obterClientePorCod(Long codCliente);

	public abstract List<Cliente> obterTodosCliente();

	public abstract void criarCliente(Cliente cliente);

	public abstract Long obterQtdeClientesNoAnoMes(Integer ano, Integer mes);

	public abstract List<QtdePeriodoDTO> obterQtdesClientesNosPeriodos(
			List<QtdePeriodoDTO> periodos);
}
