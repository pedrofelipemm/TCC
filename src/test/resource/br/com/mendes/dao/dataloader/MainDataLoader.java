package br.com.mendes.dao.dataloader;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.mendes.model.Cliente;
import br.com.mendes.model.Endereco;
import br.com.mendes.model.Feedback;
import br.com.mendes.model.Item;
import br.com.mendes.model.ItemPedido;
import br.com.mendes.model.MetaEspecifica;
import br.com.mendes.model.MetaGeral;
import br.com.mendes.model.Pedido;
import br.com.mendes.model.Produto;
import br.com.mendes.model.Servico;

public class MainDataLoader {

	private static List<Cliente> clientes;
	private static List<Endereco> enderecos;
	private static List<Pedido> pedidos;
	private static List<Produto> produtos;
	private static List<Servico> servicos;
	private static List<MetaEspecifica> metasEspecificas;
	private static List<MetaGeral> metasGerais;
	private static List<Feedback> feedbacks;

	public static void main(String[] args) {

		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
				"classpath:/applicationContext-dataloader.xml");

		SessionFactory sf = null;
		Session session = null;
		Transaction tx = null;

		boolean commit = false;

		try {
			sf = ctx.getBean(SessionFactory.class);
			session = sf.openSession();
			tx = session.beginTransaction();

			load(session);

			commit = true;

		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			if (commit) {
				tx.commit();
			} else {
				tx.rollback();
			}
			if (session != null) {
				session.close();
			}
			if (sf != null) {
				sf.close();
			}
		}
	}

	public static void load(Session session) {
		if (session.createCriteria(Endereco.class).list().isEmpty()) {
			createEndereco(session);
		}

		if (session.createCriteria(Cliente.class).list().isEmpty()) {
			createCliente(session);
		}

		if (session.createCriteria(Produto.class).list().isEmpty()) {
			createProduto(session);
		}

		if (session.createCriteria(Servico.class).list().isEmpty()) {
			createServico(session);
		}

		if (session.createCriteria(MetaEspecifica.class).list().isEmpty()) {
			createMetaEspecifica(session);
		}

		if (session.createCriteria(MetaGeral.class).list().isEmpty()) {
			createMetaGeral(session);
		}

		if (session.createCriteria(Pedido.class).list().isEmpty()) {
			createPedidoEItens(session);
		}

		if (session.createCriteria(Feedback.class).list().isEmpty()) {
			createFeedback(session);
		}

		System.out.println("\n\n ");
		System.out.println("###############################");
		System.out.println("# The data has been inserted! #");
		System.out.println("###############################");
	}

	@SuppressWarnings("unchecked")
	private static void createFeedback(Session session) {
		feedbacks = FeedbackDataLoader.createFeedbacks(session.createCriteria(Pedido.class).list());

		for (Feedback feedback : feedbacks) {
			session.save(feedback);
		}
	}

	private static void createMetaGeral(Session session) {
		metasGerais = MetasGeraisDataLoader.createMetasGerais();

		for (MetaGeral metaGeral : metasGerais) {
			session.save(metaGeral);
		}

	}

	@SuppressWarnings("unchecked")
	private static void createMetaEspecifica(Session session) {
		metasEspecificas = MetasEspecificasDataLoader.createMetasEspecificas(
				session.createCriteria(Item.class).list());

		for (MetaEspecifica metaEspecifica : metasEspecificas) {
			session.save(metaEspecifica);
		}

	}

	private static void createServico(Session session) {
		servicos = ServicosDataLoader.createServicos();

		for (Servico servico : servicos) {
			session.save(servico);
		}
	}

	private static void createProduto(Session session) {
		produtos = ProdutosDataLoader.createProdutos();

		for (Produto produto : produtos) {
			session.save(produto);
		}

	}

	@SuppressWarnings("unchecked")
	public static void createCliente(Session session) {
		clientes = ClientesDataLoader.createClientes();
		List<Endereco> enderecos = session.createCriteria(Endereco.class).list();

		for (int i = 0; i < clientes.size(); i++) {
			clientes.get(i).setEndereco(enderecos.get(i));
			session.save(clientes.get(i));
		}
	}

	public static void createEndereco(Session session) {
		enderecos = EnderecosDataLoader.createEnderecos();

		for (Endereco endereco : enderecos) {
			session.save(endereco);
		}
	}

	@SuppressWarnings("unchecked")
	public static void createPedidoEItens(Session session) {
		pedidos = PedidosDataLoader.createPedidos(session.createCriteria(Cliente.class).list(),
				session.createCriteria(Produto.class).list(), session.createCriteria(Servico.class).list());

		for (Pedido pedido : pedidos) {
			session.save(pedido);

			for (ItemPedido itemPedido : pedido.getItensPedido()) {
				itemPedido.setPedido(pedido);
				session.save(itemPedido);
			}
		}

	}

}
