package br.com.mendes.view;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.mendes.model.Pedido;
import br.com.mendes.service.PedidoService;

public class PedidosLazyDataModel extends LazyDataModel<Pedido> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2201525350085836463L;

	private PedidoService pedidoService;
	private List<Pedido> pedidos;

	public PedidosLazyDataModel(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}

	@Override
	public List<Pedido> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {

		this.pedidos = this.pedidoService.obterTodosPedidosPaginados(first, pageSize, sortField, sortOrder, filters);

		setRowCount(this.pedidoService.countBy().intValue());

		return this.pedidos;
	}

	@Override
	public Object getRowKey(Pedido object) {
		return object.getCod();
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
	public Pedido getRowData(String rowKey) {

		if (!"null".equalsIgnoreCase(rowKey)) {
			Long id = Long.valueOf(rowKey);

			for (Pedido pedido : this.pedidos) {
				if (id.equals(pedido.getCod())) {
					return pedido;
				}
			}
		}

		return null;
	}

	public PedidoService getPedidoService() {
		return this.pedidoService;
	}

	public void setPedidoService(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}

	public List<Pedido> getPedidos() {
		return this.pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

}
