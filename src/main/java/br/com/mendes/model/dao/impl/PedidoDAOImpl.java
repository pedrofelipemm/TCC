package br.com.mendes.model.dao.impl;

import org.springframework.stereotype.Repository;

import br.com.mendes.model.Pedido;
import br.com.mendes.model.dao.PedidoDAO;

@Repository
public class PedidoDAOImpl extends DAOImpl<Pedido, Long> implements
		PedidoDAO {

	private static final long serialVersionUID = 9051943387825326329L;

}
