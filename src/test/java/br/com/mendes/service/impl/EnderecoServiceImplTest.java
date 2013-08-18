package br.com.mendes.service.impl;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.mendes.model.Endereco;
import br.com.mendes.model.Estado;
import br.com.mendes.model.dao.impl.DAOImplTest;
import br.com.mendes.service.EnderecoService;

public class EnderecoServiceImplTest extends DAOImplTest{

	@Autowired
	EnderecoService enderecoService;

	Endereco endereco;

	@Before
	public void setUp() throws Exception {
		this.endereco = new Endereco("Rua Família musa", 31, "Jardim América", "Casa Branca", "13700-000", Estado.SP, "Brasil");

		save(this.endereco);
	}

	@Test
	public void testObterEnderecoPorCod() {
		Endereco endereco = this.enderecoService.obterEnderecoPorCod(this.endereco.getCodEndereco());

		Assert.assertNotNull(endereco);
		Assert.assertEquals(this.endereco, endereco);
	}

	@Test
	public void testObterTodosEnderecos() {
		List<Endereco> enderecos = this.enderecoService.obterTodosEnderecos();

		Assert.assertNotNull(enderecos);
		Assert.assertTrue(!enderecos.isEmpty());
	}

}
