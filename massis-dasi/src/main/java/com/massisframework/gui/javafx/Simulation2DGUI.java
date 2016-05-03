package com.massisframework.gui.javafx;

import java.io.IOException;

import com.massisframework.gui.DrawableLayer;
import com.massisframework.massis.displays.SimulationDisplay;
import com.massisframework.massis.displays.floormap.layers.DrawableFloor;
import com.massisframework.massis.model.agents.HighLevelController;
import com.massisframework.massis.model.building.Building;
import com.massisframework.massis.model.building.SimulationObject;
import com.massisframework.massis.sim.Simulation;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Simulation2DGUI extends Stage
		implements SimulationDisplay {

	/**
	 * 
	 */
	private Building building;
	private DrawableLayer<DrawableFloor>[] layers;
	private Scene scene;
	private AppController controller;
	private LongProperty tick;

	public Simulation2DGUI(
			String title, Simulation simulation,
			DrawableLayer<DrawableFloor>[] layers)
	{

		this.tick = new SimpleLongProperty();
		this.titleProperty().set(title);
		this.building = simulation.getBuilding();
		simulation.schedule.scheduleRepeating(
				(ss) -> this.tick.set(ss.schedule.getSteps()));
		this.layers = layers;
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(
				Simulation2DGUI.class.getResource("RootLayout.fxml"));
		try
		{
			this.scene = new Scene((BorderPane) loader.load());
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.controller = loader.getController();

	}

	public void start()
	{
		controller.setData(this);

		this.setScene(scene);

		this.show();
	}

	@Override
	public void animate(SimulationObject obj)
	{
		this.controller.animate(obj);
	}

	@Override
	public boolean isDisplayEnabled()
	{
		return this.isShowing();
	}

	public DrawableLayer<DrawableFloor>[] getLayers()
	{
		return layers;
	}

	public Building getBuilding()
	{
		return this.building;
	}

	public LongProperty getTick()
	{
		return tick;
	}

	public <T extends HighLevelController> void addChartHandler(
			Class<T> agentClass,
			Class<? extends AgentChartCollection<T>> handler)
	{
		this.controller.addChartHandler(agentClass, handler);
	}
}
