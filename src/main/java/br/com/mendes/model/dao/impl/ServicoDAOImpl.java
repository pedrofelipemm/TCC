package br.com.mendes.model.dao.impl;


import org.springframework.stereotype.Repository;

import br.com.mendes.model.Servico;
import br.com.mendes.model.dao.ServicoDAO;

@Repository
public class ServicoDAOImpl extends DAOImpl<Servico,Long> implements ServicoDAO {

	private static final long serialVersionUID = 2409967233524119615L;

}
