package com.massisframework.gui.javafx.charts;

import java.util.ArrayList;
import java.util.List;

import com.massisframework.massis.model.agents.HighLevelController;

import javafx.scene.chart.Chart;

public abstract class AbstractAgentChartHandler<T extends HighLevelController> implements ChartHandler{

	protected T agent;
	protected List<Chart> charts;

	public AbstractAgentChartHandler(T agent)
	{
		this.agent = agent;
		this.charts = new ArrayList<>();
	}

	public List<Chart> getCharts()
	{
		return this.charts;
	}

	public abstract void update(long tick);
}
