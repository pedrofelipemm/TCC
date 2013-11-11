package br.com.mendes.dao.dataloader;

import java.util.ArrayList;
import java.util.List;

import br.com.mendes.model.Endereco;
import br.com.mendes.model.Estado;

public class EnderecosDataLoader {

	public static List<Endereco> createEnderecos() {
		List<Endereco> enderecos = new ArrayList<Endereco>();

		while (enderecos.size() < 40) {
			enderecos.add(new Endereco("Rua Familia Musa", 31, "Jardim América", "Casa Branca", "13.700-000", Estado.SP, "Brasil"));
			enderecos.add(new Endereco("Rua Familia Romano", 71, "Centro", "Mococa", "13.779-000", Estado.SP, "Brasil"));
			enderecos.add(new Endereco("Rua Antonio Cristovan", 356, "Centro", "Casa Branca", "13.700-000", Estado.SP, "Brasil"));
			enderecos.add(new Endereco("Rua dos Ipês", 56, "Centro", "São José", "12.300-000", Estado.SP, "Brasil"));
			enderecos.add(new Endereco("Av. Jorge Washington", 393, "Second Square", "New York", "98052-7329 ", Estado.IL, "USA"));
		}

		return enderecos;
	}

}
