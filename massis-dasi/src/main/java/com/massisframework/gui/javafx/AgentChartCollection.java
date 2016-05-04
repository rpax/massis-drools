package com.massisframework.gui.javafx;

import java.util.ArrayList;
import java.util.List;

import com.massisframework.massis.model.agents.HighLevelController;

import javafx.scene.chart.Chart;

public abstract class AgentChartCollection<T extends HighLevelController> {

	protected T agent;
	protected List<Chart> charts;

	public AgentChartCollection(T agent)
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
