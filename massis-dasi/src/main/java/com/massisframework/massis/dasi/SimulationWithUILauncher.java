package com.massisframework.massis.dasi;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;
import javax.xml.parsers.DocumentBuilderFactory;

import com.massisframework.gui.DrawableLayer;
import com.massisframework.gui.javafx.Simulation2DGUI;
import com.massisframework.massis.dasi.apps.robots.victim.VictimRobot;
import com.massisframework.massis.dasi.gui.VictimRobotChartHandler;
import com.massisframework.massis.displays.floormap.layers.ConnectionsLayer;
import com.massisframework.massis.displays.floormap.layers.CrowdDensityLayer;
import com.massisframework.massis.displays.floormap.layers.DoorLayer;
import com.massisframework.massis.displays.floormap.layers.DrawableFloor;
import com.massisframework.massis.displays.floormap.layers.PathFinderLayer;
import com.massisframework.massis.displays.floormap.layers.PathLayer;
import com.massisframework.massis.displays.floormap.layers.PeopleIDLayer;
import com.massisframework.massis.displays.floormap.layers.PeopleLayer;
import com.massisframework.massis.displays.floormap.layers.QTLayer;
import com.massisframework.massis.displays.floormap.layers.RadioLayer;
import com.massisframework.massis.displays.floormap.layers.RoomsLabelLayer;
import com.massisframework.massis.displays.floormap.layers.RoomsLayer;
import com.massisframework.massis.displays.floormap.layers.VisibleAgentsLines;
import com.massisframework.massis.displays.floormap.layers.VisionRadioLayer;
import com.massisframework.massis.displays.floormap.layers.WallLayer;
import com.massisframework.massis.sim.Simulation;
import com.massisframework.massis.sim.SimulationWithUI;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import sim.display.Console;
import sim.display.GUIState;

public class SimulationWithUILauncher {

	static
	{
		System.setProperty("com.eteks.sweethome3d.no3D", "true");
	}

	/**
	 * @param args
	 *            the building file path
	 */
	public static void main(String[] args)
	{
		String buildingFilePath = null;
		try
		{
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			System.out.println(factory);
			buildingFilePath = "simplehouse.sh3d";
		}
		catch (final Exception e)
		{
			Logger.getLogger(SimulationWithUILauncher.class.getName())
					.log(Level.SEVERE, "Error when loading home. Exiting", e);
			System.exit(-1);
		}
		/*
		 * Not needed, in this example. We are not going to load any kind of
		 * resources during the simulation.
		 */
		final String resourceFolderPath = "";

		final Simulation simState = new Simulation(System.currentTimeMillis(),
				buildingFilePath, resourceFolderPath, null);

		/**
		 * Basic Layers. Can be added more, or removed.
		 */
		@SuppressWarnings("unchecked")
		final DrawableLayer<DrawableFloor>[] floorMapLayers = new DrawableLayer[] {
				new RoomsLayer(true),
				new RoomsLabelLayer(false),
				new VisionRadioLayer(false),
				new CrowdDensityLayer(false),
				new WallLayer(true),
				new DoorLayer(true),
				new ConnectionsLayer(false),
				new PathLayer(false),
				new PeopleLayer(true),
				new RadioLayer(true),
				new PathFinderLayer(false),
				new PeopleIDLayer(false),
				new VisibleAgentsLines(false),
				new QTLayer(false)
		};

		final GUIState vid = new SimulationWithUI(simState, floorMapLayers);

		final Console c = new Console(vid);

		c.setIncrementSeedOnStop(false);
		//
		c.pressPlay();
		c.pressPause();
		SwingUtilities.invokeLater(() -> {
			new JFXPanel();
			Platform.runLater(() -> {
				
				Simulation2DGUI s2dgui = new Simulation2DGUI(
						"Simulation 2D GUI 1.1",
						simState, floorMapLayers);
				simState.getBuilding().registerDisplays(s2dgui);
				s2dgui.addChartHandler(VictimRobot.class, VictimRobotChartHandler.class);
				s2dgui.start();
				c.setVisible(true);

			});
		});

	}

}
