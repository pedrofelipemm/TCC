package br.com.mendes.view.lazy;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.mendes.model.Cliente;
import br.com.mendes.service.ClienteService;

public class ClientesLazyDataModel extends LazyDataModel<Cliente> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8761899819547702615L;

	private ClienteService clienteService;
	private String filter;
	private List<Cliente> clientes;

	public ClientesLazyDataModel(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@Override
	public List<Cliente> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {

		this.clientes = this.clienteService.obterTodosClientesPaginados(this.filter, first, pageSize);

		setRowCount(this.clienteService.countBy(this.filter).intValue());

		return this.clientes;
	}

	@Override
	public Object getRowKey(Cliente object) {
		return object.getCodCliente();
	}

	@Override
	public void setRowIndex(int rowIndex) {

		if (rowIndex == -1 || getPageSize() == 0) {
			super.setRowIndex(-1);
		} else {
			super.setRowIndex(rowIndex % getPageSize());
		}
	}

	@Override
	public Cliente getRowData(String rowKey) {

		if (!"null".equalsIgnoreCase(rowKey)) {
			Long id = Long.valueOf(rowKey);

			for (Cliente cliente : this.clientes) {
				if (id.equals(cliente.getCodCliente())) {
					return cliente;
				}
			}
		}

		return null;
	}

	public ClienteService getClienteService() {
		return this.clienteService;
	}

	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	public List<Cliente> getClientes() {
		return this.clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public String getFilter() {
		return this.filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

}
