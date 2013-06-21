package br.com.mendes.model.dao.impl;

import org.springframework.stereotype.Repository;

import br.com.mendes.model.Produto;
import br.com.mendes.model.dao.ProdutoDAO;

@Repository
public class ProdutoDAOImpl extends DAOImpl<Produto,Long> implements ProdutoDAO {

	private static final long serialVersionUID = 3201421256269456694L;

}
