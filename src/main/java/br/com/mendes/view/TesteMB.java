package br.com.mendes.view;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartSeries;

@ManagedBean(name = "testeMB")
@ViewScoped
public class TesteMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3941744580190950048L;

	private CartesianChartModel categoryModel;

	private CartesianChartModel linearModel;

	public TesteMB() {
		createCategoryModel();
		createLinearModel();
	}

	public CartesianChartModel getCategoryModel() {
		return this.categoryModel;
	}

	public CartesianChartModel getLinearModel() {
		return this.linearModel;
	}

	private void createCategoryModel() {
		this.categoryModel = new CartesianChartModel();

		ChartSeries boys = new ChartSeries();
		boys.setLabel("Boys");

		boys.set("2004", 120);
		boys.set("2005", 100);
		boys.set("2006", 44);
		boys.set("2007", 150);
		boys.set("2008", 25);

		ChartSeries girls = new ChartSeries();
		girls.setLabel("Girls");

		girls.set("2004", 52);
		girls.set("2005", 60);
		girls.set("2006", 110);
		girls.set("2007", 135);
		girls.set("2008", 120);

		this.categoryModel.addSeries(boys);
		this.categoryModel.addSeries(girls);
	}

	private void createLinearModel() {
		this.linearModel = new CartesianChartModel();

		LineChartSeries series1 = new LineChartSeries();
		series1.setLabel("Series 1");

		series1.set(1, 2);
		series1.set(2, 1);
		series1.set(3, 3);
		series1.set(4, 6);
		series1.set(5, 8);

		LineChartSeries series2 = new LineChartSeries();
		series2.setLabel("Series 2");
		series2.set(1, 6);
		series2.set(2, 6);
		series2.setShowLine(false);
		this.linearModel.addSeries(series2);

		series2 = new LineChartSeries();
		series2.set(3, 4);
		series2.set(4, 4);
		this.linearModel.addSeries(series2);

		this.linearModel.addSeries(series1);
	}
}