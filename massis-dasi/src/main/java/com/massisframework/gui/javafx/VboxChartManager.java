package com.massisframework.gui.javafx;

import com.massisframework.gui.javafx.charts.SimChartManager;

import javafx.scene.chart.Chart;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class VboxChartManager implements SimChartManager {

	private int nCharts;
	private VBox vbox;

	public VboxChartManager(VBox vbox)
	{
		this.nCharts = 0;
		this.vbox = vbox;
	}

	@Override
	public void addChart(Chart chart)
	{
		HBox hbox = null;
		if (this.nCharts % 2 == 0)
		{
			hbox = new HBox();
			this.vbox.getChildren().add(hbox);
		}
		else
		{
			hbox = (HBox) this.vbox.getChildren().get(this.vbox.getChildren().size() - 1);
		}
		hbox.getChildren().add(chart);

		this.nCharts++;
	}

	@Override
	public void clearCharts()
	{
		this.vbox.getChildren().clear();
		this.nCharts = 0;
	}

}
