package br.com.mendes.view;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import org.springframework.dao.DataIntegrityViolationException;

import br.com.mendes.model.Cliente;
import br.com.mendes.model.Endereco;
import br.com.mendes.service.ClienteService;
import br.com.mendes.service.EnderecoService;
import br.com.mendes.utils.MBUtil;
import br.com.mendes.view.lazy.ClientesLazyDataModel;

@ManagedBean(name = "clienteMB")
@ViewScoped
public class ClienteMB implements Serializable {

	private static final long serialVersionUID = 7948132687109359178L;

	private Cliente cliente;

	private Endereco endereco;

	private ClientesLazyDataModel clientesLazyDataModel;

	@ManagedProperty(name = "clienteService", value = "#{clienteService}")
	private ClienteService clienteService;

	@ManagedProperty(name = "enderecoService", value = "#{enderecoService}")
	private EnderecoService enderecoService;

	@PostConstruct
	public void iniciar() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();

		if (!flash.isEmpty()) {
			this.cliente = (Cliente) flash.get("cliente");
			this.endereco = (Endereco) flash.get("endereco");
		} else {
			this.cliente = new Cliente();
			this.endereco = new Endereco();
		}

		this.clientesLazyDataModel = new ClientesLazyDataModel(this.clienteService);
	}

	public ClienteMB() {
	}

	public String iniciarEdicao(Long codCliente) {

		this.cliente = this.clienteService.obterClientePorCod(codCliente);
		this.endereco = this.cliente.getEndereco();

		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("cliente", this.cliente);
		flash.put("endereco", this.endereco);

		return "/paginas/cadastroCliente.xhtml";
	}

	public void salvarCliente() {

		this.enderecoService.criarEndereco(this.endereco);

		this.cliente.setEndereco(this.endereco);

		if (this.cliente.getDataCadastro() == null) {
			this.cliente.setDataCadastro(new Date());
		}

		try {
			this.clienteService.criarCliente(this.cliente);
			MBUtil.addInfo("Cadastrado com sucesso.");

			limparDados();
		} catch (DataIntegrityViolationException exception) {
			exception.printStackTrace();

			String msg = exception.getMessage().substring(exception.getMessage().indexOf("Detalhe"),
					exception.getMessage().indexOf(".;"));

			MBUtil.addError(msg);
		}

	}

	public void pesquisa() {
		this.clientesLazyDataModel.setRowIndex(-1);
	}

	public void limparDados() {
		this.cliente = new Cliente();
		this.endereco = new Endereco();
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Endereco getEndereco() {
		return this.endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public ClienteService getClienteService() {
		return this.clienteService;
	}

	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	public EnderecoService getEnderecoService() {
		return this.enderecoService;
	}

	public void setEnderecoService(EnderecoService enderecoService) {
		this.enderecoService = enderecoService;
	}

	public ClientesLazyDataModel getClientesLazyDataModel() {
		return this.clientesLazyDataModel;
	}

	public void setClientesLazyDataModel(ClientesLazyDataModel clientesLazyDataModel) {
		this.clientesLazyDataModel = clientesLazyDataModel;
	}

}
