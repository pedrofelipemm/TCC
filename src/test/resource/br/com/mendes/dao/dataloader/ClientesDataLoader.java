package br.com.mendes.dao.dataloader;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import br.com.mendes.model.Cliente;
import br.com.mendes.utils.GeradorDeCPF;

public abstract class ClientesDataLoader {

	public static List<Cliente> createClientes() {
		List<Cliente> clientes = new ArrayList<Cliente>();
		List<String> cpfs = criarListaDeCPFeCNPJ();
		List<String> nomes = criarListaNomes();
		List<String> sobrenomes = criarListaSobrenomes();
		List<String> telefones = criarListaTelefones();
		List<String> celulares = criarListaTelefones();
		List<String> rgs = criarListaRGs();
		List<Date> datasNacimento = criarListaDatasNascimento();
		List<Date> datasCadastro = criarListaDatasCadastro();

		while (clientes.size() < 40) {
			clientes.add(new Cliente(nomes.remove(0), sobrenomes.remove(0), telefones.remove(0), celulares.remove(0), rgs.remove(0), cpfs.remove(0),
					datasNacimento.remove(0), datasCadastro.remove(0)));
		}

		return clientes;
	}

	private static List<Date> criarListaDatasCadastro() {
		List<Date> lista = new ArrayList<Date>();

		lista.add(new GregorianCalendar(2013, 10, 01).getTime());
		lista.add(new GregorianCalendar(2013, 10, 01).getTime());
		lista.add(new GregorianCalendar(2013, 10, 01).getTime());
		lista.add(new GregorianCalendar(2013, 9, 01).getTime());
		lista.add(new GregorianCalendar(2013, 9, 01).getTime());
		lista.add(new GregorianCalendar(2013, 9, 01).getTime());
		lista.add(new GregorianCalendar(2013, 8, 01).getTime());
		lista.add(new GregorianCalendar(2013, 8, 01).getTime());
		lista.add(new GregorianCalendar(2013, 8, 01).getTime());
		lista.add(new GregorianCalendar(2013, 8, 01).getTime());
		lista.add(new GregorianCalendar(2013, 8, 01).getTime());
		lista.add(new GregorianCalendar(2013, 7, 01).getTime());
		lista.add(new GregorianCalendar(2013, 7, 01).getTime());
		lista.add(new GregorianCalendar(2013, 6, 01).getTime());
		lista.add(new GregorianCalendar(2013, 4, 01).getTime());
		lista.add(new GregorianCalendar(2013, 4, 01).getTime());
		lista.add(new GregorianCalendar(2013, 4, 01).getTime());
		lista.add(new GregorianCalendar(2013, 3, 01).getTime());
		lista.add(new GregorianCalendar(2013, 3, 01).getTime());
		lista.add(new GregorianCalendar(2013, 2, 01).getTime());
		lista.add(new GregorianCalendar(2013, 2, 01).getTime());
		lista.add(new GregorianCalendar(2013, 2, 01).getTime());
		lista.add(new GregorianCalendar(2013, 2, 01).getTime());
		lista.add(new GregorianCalendar(2013, 2, 01).getTime());
		lista.add(new GregorianCalendar(2013, 2, 01).getTime());
		lista.add(new GregorianCalendar(2013, 2, 01).getTime());
		lista.add(new GregorianCalendar(2013, 2, 01).getTime());
		lista.add(new GregorianCalendar(2013, 1, 01).getTime());
		lista.add(new GregorianCalendar(2013, 1, 01).getTime());
		lista.add(new GregorianCalendar(2013, 1, 01).getTime());
		lista.add(new GregorianCalendar(2013, 1, 01).getTime());
		lista.add(new GregorianCalendar(2013, 1, 01).getTime());
		lista.add(new GregorianCalendar(2013, 1, 01).getTime());
		lista.add(new GregorianCalendar(2013, 0, 01).getTime());
		lista.add(new GregorianCalendar(2013, 0, 01).getTime());
		lista.add(new GregorianCalendar(2013, 0, 01).getTime());
		lista.add(new GregorianCalendar(2013, 0, 01).getTime());
		lista.add(new GregorianCalendar(2013, 0, 01).getTime());
		lista.add(new GregorianCalendar(2013, 0, 01).getTime());
		lista.add(new GregorianCalendar(2013, 0, 01).getTime());

		return lista;
	}

	private static List<Date> criarListaDatasNascimento() {
		List<Date> lista = new ArrayList<Date>();

		while (lista.size() < 40) {
			lista.add(new GregorianCalendar().getTime());
		}
		return lista;
	}

	private static List<String> criarListaRGs() {
		List<String> lista = new ArrayList<String>();
		Random random = new Random();
		StringBuilder numero = new StringBuilder();

		while (lista.size() < 40) {
			numero.append(random.nextInt(10)).append(random.nextInt(10));
			numero.append(".");
			numero.append(random.nextInt(10)).append(random.nextInt(10)).append(random.nextInt(10));
			numero.append(".");
			numero.append(random.nextInt(10)).append(random.nextInt(10)).append(random.nextInt(10));
			numero.append("-");
			numero.append(random.nextInt(10));

			lista.add(numero.toString());
			numero.setLength(0);
		}

		return lista;
	}

	private static List<String> criarListaTelefones() {
		List<String> lista = new ArrayList<String>();
		Random random = new Random();
		StringBuilder numero = new StringBuilder();

		while (lista.size() < 40) {
			numero.append("(");
			numero.append(random.nextInt(10)).append(random.nextInt(10));
			numero.append(") ");
			numero.append(random.nextInt(10)).append(random.nextInt(10)).append(random.nextInt(10)).append(random.nextInt(10));
			numero.append("-");
			numero.append(random.nextInt(10)).append(random.nextInt(10)).append(random.nextInt(10)).append(random.nextInt(10));

			lista.add(numero.toString());
			numero.setLength(0);
		}

		return lista;
	}

	private static List<String> criarListaSobrenomes() {
		List<String> lista = new ArrayList<String>();

		lista.add("Marques");
		lista.add("Moreira");
		lista.add("Batista");
		lista.add("Trevsan");
		lista.add("Devitto");
		lista.add("Curti");
		lista.add("Caetano");
		lista.add("Genari");
		lista.add("Sakai");
		lista.add("Genari");
		lista.add("Paiva");
		lista.add("Osse");
		lista.add("Vechiatti");
		lista.add("Beato");
		lista.add("Estival");
		lista.add("Barione");
		lista.add("Souza");
		lista.add("Cruz");
		lista.add("Castoldi");
		lista.add("Santos");
		lista.add("Pereira");
		lista.add("Galli");
		lista.add("Moraes");
		lista.add("Loffi");
		lista.add("Lombardi");
		lista.add("Ludwig");
		lista.add("Carolina");
		lista.add("Martins");
		lista.add("Martini");
		lista.add("Ribeiro");
		lista.add("Andreazzi");
		lista.add("Ferreira");
		lista.add("Mechilão");
		lista.add("Mafra");
		lista.add("Parra");
		lista.add("Oliveira");
		lista.add("Bartier");
		lista.add("Porfirio");
		lista.add("Martinatti");
		lista.add("Camargo");

		return lista;
	}

	private static List<String> criarListaNomes() {
		List<String> lista = new ArrayList<String>();

		lista.add("Guilherme");
		lista.add("João");
		lista.add("Gabriel");
		lista.add("Diego");
		lista.add("Diogo");
		lista.add("Gustavo");
		lista.add("Rodrigo");
		lista.add("Vitor");
		lista.add("Lucas");
		lista.add("Eduardo");
		lista.add("Henrique");
		lista.add("Carlos");
		lista.add("Enzo");
		lista.add("Bruno");
		lista.add("Leonardo");
		lista.add("Igor");
		lista.add("Sofia");
		lista.add("Amanda");
		lista.add("Júlia");
		lista.add("Yasmin");
		lista.add("Beatriz");
		lista.add("Bruna");
		lista.add("Lara");
		lista.add("Larissa");
		lista.add("Maria");
		lista.add("Isabela");
		lista.add("Carolina");
		lista.add("Luana");
		lista.add("Camila");
		lista.add("Ana");
		lista.add("Laura");
		lista.add("Letícia");
		lista.add("Gabriele");
		lista.add("Pérola");
		lista.add("Ana");
		lista.add("Raquel");
		lista.add("Stela");
		lista.add("Magda");
		lista.add("Ágata");
		lista.add("Guilherme");

		return lista;
	}

	public static List<String> criarListaDeCPFeCNPJ() {

		List<String> lista = new ArrayList<String>();
		String cpf;

		while (lista.size() < 40) {
			cpf = GeradorDeCPF.geraCPF();

			if (!lista.contains(cpf)) {
				lista.add(formataCPF(cpf));
			}

		}
		return lista;
	}

	public static String formataCPF(String cpf) {
		return cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) +
				"-" + cpf.substring(9, 11);
	}
}
