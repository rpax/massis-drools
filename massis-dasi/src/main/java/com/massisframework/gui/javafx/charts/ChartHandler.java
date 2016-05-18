package com.massisframework.gui.javafx.charts;

import java.util.List;

import javafx.scene.chart.Chart;

public interface ChartHandler {

	public List<Chart> getCharts();

	public void update(long tick);
}
