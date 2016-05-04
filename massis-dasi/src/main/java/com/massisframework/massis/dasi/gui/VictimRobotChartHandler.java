package com.massisframework.massis.dasi.gui;

import java.util.ArrayList;
import java.util.List;

import com.massisframework.gui.javafx.AgentChartCollection;
import com.massisframework.massis.dasi.apps.robots.victim.VictimRobot;

import javafx.application.Platform;
import javafx.scene.chart.Chart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class VictimRobotChartHandler
		extends AgentChartCollection<VictimRobot> {

	private LineChart<Number, Number> energyChart;
	private long tick;
	private Runnable updateRun;

	public VictimRobotChartHandler(VictimRobot vr)
	{
		super(vr);
		energyChart = new LineChart<Number, Number>(new NumberAxis(),
				new NumberAxis());
		energyChart.getXAxis().setLabel("Simulation tick");
		energyChart.setTitle("Energy Over Time");
		energyChart.getData().add(new XYChart.Series<Number, Number>());
		this.charts.add(energyChart);
		energyChart.setCreateSymbols(false);
		updateRun = () -> {this.energyChart.getData().get(0).getData().add(new XYChart.Data<>(tick, this.agent.getEnergy()));};
	}

	@Override
	public List<Chart> getCharts()
	{
		return this.charts;
	}

	@Override
	public void update(long tick)
	{
		this.tick = tick;

		Platform.runLater(this.updateRun);
	}

}
