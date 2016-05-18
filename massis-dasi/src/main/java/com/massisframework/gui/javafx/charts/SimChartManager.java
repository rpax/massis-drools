package com.massisframework.gui.javafx.charts;



import java.util.List;

import javafx.scene.chart.Chart;

public interface SimChartManager {

	void addChart(Chart chart);

	default void addCharts(List<Chart> charts)
	{
		charts.forEach(this::addChart);
	}

	void clearCharts();

}