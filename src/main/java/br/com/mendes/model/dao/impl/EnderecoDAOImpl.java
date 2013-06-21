package br.com.mendes.model.dao.impl;


import org.springframework.stereotype.Repository;

import br.com.mendes.model.Endereco;
import br.com.mendes.model.dao.EnderecoDAO;

@Repository
public class EnderecoDAOImpl extends DAOImpl<Endereco,Long> implements EnderecoDAO {

	private static final long serialVersionUID = -6322663553984401973L;

}
