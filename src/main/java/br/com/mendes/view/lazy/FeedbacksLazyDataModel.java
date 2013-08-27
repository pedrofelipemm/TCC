package br.com.mendes.view.lazy;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.mendes.model.Feedback;
import br.com.mendes.service.FeedbackService;

public class FeedbacksLazyDataModel extends LazyDataModel<Feedback> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6167515914877475702L;

	private FeedbackService feedbackService;
	private String filter;
	private List<Feedback> feedbacks;

	public FeedbacksLazyDataModel() {
	}

	public FeedbacksLazyDataModel(FeedbackService feedbackService) {
		this.feedbackService = feedbackService;
	}

	@Override
	public List<Feedback> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {

		this.feedbacks = this.feedbackService.obterTodosFeedbacksPaginados(this.filter, first, pageSize);

		setRowCount(this.feedbackService.countBy(this.filter).intValue());

		return this.feedbacks;
	}

	@Override
	public Object getRowKey(Feedback object) {
		return object.getCodFeedback();
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
	public Feedback getRowData(String rowKey) {

		if (!"null".equalsIgnoreCase(rowKey)) {
			Long id = Long.valueOf(rowKey);

			for (Feedback feedback : this.feedbacks) {
				if (id.equals(feedback.getCodFeedback())) {
					return feedback;
				}
			}
		}

		return null;
	}

	public FeedbackService getFeedbackService() {
		return this.feedbackService;
	}

	public void setFeedbackService(FeedbackService feedbackService) {
		this.feedbackService = feedbackService;
	}

	public String getFilter() {
		return this.filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public List<Feedback> getFeedbacks() {
		return this.feedbacks;
	}

	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

}
