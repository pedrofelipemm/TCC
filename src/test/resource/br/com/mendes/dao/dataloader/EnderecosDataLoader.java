package br.com.mendes.dao.dataloader;

import java.util.ArrayList;
import java.util.List;

import br.com.mendes.model.Endereco;
import br.com.mendes.model.Estado;

public class EnderecosDataLoader {

	public static List<Endereco> createEnderecos() {
		List<Endereco> enderecos = new ArrayList<Endereco>();

		enderecos.add(new Endereco("Rua Familia Musa", 31, "Jardim América", "Casa Branca", "13.700-000", Estado.SP, "Brasil"));
		enderecos.add(new Endereco("Rua Familia Romano", 71, "Centro", "Mococa", "13.779-000", Estado.SP, "Brasil"));
		enderecos.add(new Endereco("Rua Antonio Cristovan", 356, "Centro", "Casa Branca", "13.700-000", Estado.SP, "Brasil"));

		return enderecos;
	}

}
