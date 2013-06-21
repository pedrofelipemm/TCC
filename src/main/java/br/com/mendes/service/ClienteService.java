package br.com.mendes.service;

import java.io.Serializable;
import java.util.List;

import br.com.mendes.dto.QtdePeriodoDTO;
import br.com.mendes.model.Cliente;

public interface ClienteService extends Serializable {

    Cliente obterClientePorCod(Long codCliente);
    
    List<Cliente> obterTodosCliente();
    
    Cliente criarCliente(Cliente cliente);

	Long obterQtdeClientesNoAnoMes(Integer ano, Integer mes);

	List<QtdePeriodoDTO> obterQtdesClientesNosPeriodos(
			List<QtdePeriodoDTO> periodos);
}
