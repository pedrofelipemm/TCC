package br.com.mendes.view.lazy;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.mendes.model.Servico;
import br.com.mendes.service.ServicoService;

public class ServicosLazyDataModel extends LazyDataModel<Servico> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7931989888098275285L;

	private ServicoService servicoService;
	private List<Servico> servicos;

	public ServicosLazyDataModel(ServicoService servicoService) {
		this.servicoService = servicoService;
	}

	@Override
	public List<Servico> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {

		this.servicos = this.servicoService.obterTodosServicosPaginados(first, pageSize, sortField, sortOrder, filters);

		setRowCount(this.servicoService.countBy().intValue());

		return this.servicos;
	}

	@Override
	public Object getRowKey(Servico object) {
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
	public Servico getRowData(String rowKey) {

		if (!"null".equalsIgnoreCase(rowKey)) {
			Long id = Long.valueOf(rowKey);

			for (Servico servico : this.servicos) {
				if (id.equals(servico.getCod())) {
					return servico;
				}
			}
		}

		return null;
	}

	public ServicoService getServicoService() {
		return this.servicoService;
	}

	public void setServicoService(ServicoService servicoService) {
		this.servicoService = servicoService;
	}

	public List<Servico> getServicos() {
		return this.servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

}
