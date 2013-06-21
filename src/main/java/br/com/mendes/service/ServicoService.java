package br.com.mendes.service;

import java.util.List;

import br.com.mendes.model.Servico;

public interface ServicoService {

    Servico obterServicoPorCod(Long codServico);
    
    List<Servico> obterTodosServicos();
    
    Servico criarServico(Servico servico);
}
