package com.massisframework.gui.javafx;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.controlsfx.control.CheckListView;

import com.massisframework.gui.DrawableLayer;
import com.massisframework.massis.displays.SimulationDisplay;
import com.massisframework.massis.displays.floormap.layers.DrawableFloor;
import com.massisframework.massis.model.agents.HighLevelController;
import com.massisframework.massis.model.building.SimulationObject;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class AppController implements SimulationDisplay {

	// Good, ol' MASSIS 2D view
	@FXML
	public AnchorPane simulationFrameWrapper;

	// ============================================
	@FXML
	private ScrollPane layersListPane;
	@FXML
	private ComboBox<DrawableFloor> floorCombobox;
	private ObservableList<DrawableFloor> floorList;

	private CheckListView<DrawableLayerWrapper> layerListView;
	private ObservableList<DrawableLayerWrapper> layersList;
	// ============================================
	@FXML
	private VBox chartVBox;
	@FXML
	private TableView<HighLevelController> agentsTable;
	@FXML
	private TableColumn<SimulationLoggable, Integer> agentsTableColumnId;
	@FXML
	private TableColumn<SimulationLoggable, String> agentsTableColumnClassName;

	private LogController logController;
	/*
	 * Per agent
	 */
	// Logs
	@FXML
	private TableView<LogLine> agentLog;
	@FXML
	private TableColumn<LogLine, String> agentLogLevelColumn;
	@FXML
	private TableColumn<LogLine, String> agentLogLevelMessage;
	@FXML
	private TableColumn<LogLine, Long> tickColumn;
	// Statistics

	private Simulation2DGUI sim2dGUI;
	// private Graphics2DLayerRepaintManager<DrawableFloor>
	// layersRepaintManager;

	private PanAndZoomCanvas pzc;

	private SimChartManager chartManager;

	private Map<Class<? extends HighLevelController>, Class<? extends AgentChartCollection<?>>> chartHandlers;
	private Map<HighLevelController, AgentChartCollection<?>> charts;

	public AppController()
	{

	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize()
	{
		this.chartHandlers = new HashMap<>();
		this.charts = new HashMap<>();
		this.chartManager = new VboxChartManager(chartVBox);
		this.logController = new LogController(
				this.agentLogLevelColumn,
				this.agentLogLevelMessage,
				this.tickColumn);
	}

	public void setData(Simulation2DGUI sim2dGUI)
	{
		this.sim2dGUI = sim2dGUI;
		this.configureTable();
		this.configureLayerList();
		this.configureSimulation2DGraphics();
		this.configureLogging();
		this.configureCharts();
	}

	private void configureCharts()
	{

		this.sim2dGUI.getBuilding().getScheduledControllers().forEach(hlc -> {
			if (this.chartHandlers.containsKey(hlc.getClass()))
			{
				Class<? extends AgentChartCollection<?>> clazz = this.chartHandlers
						.get(hlc.getClass());
				if (clazz != null)
				{
					try
					{
						AgentChartCollection<?> ch = clazz
								.getConstructor(hlc.getClass())
								.newInstance(hlc);
						this.charts.put(hlc, ch);
						this.sim2dGUI.getTick().addListener(
								(obs, o, n) -> ch.update(n.longValue()));
					}
					catch (Exception e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		// De momento
		this.agentsTable.getSelectionModel().selectedItemProperty()
				.addListener(
						(obs, o, hlc) -> {
							this.chartManager.clearCharts();
							if (charts.get(hlc) != null)
							{
								this.chartManager.addCharts(charts.get(hlc).getCharts());
							}

						});
	}

	private void configureLogging()
	{
		this.sim2dGUI.getBuilding().getScheduledControllers().stream()
				.filter(SimulationLoggable.class::isInstance)
				.map(SimulationLoggable.class::cast)
				.forEach(sc -> sc.addLoggerProvider(this.logController));

		this.agentsTable.getSelectionModel().selectedItemProperty()
				.addListener((obs, o, n) -> {
					if (n != null && n instanceof SimulationLoggable)
					{
						this.logController
								.setLoggedObject(((SimulationLoggable) n));
					}
				});
	}

	private void configureTable()
	{
		agentsTableColumnId.setCellValueFactory(
				cellData -> new ReadOnlyIntegerWrapper(0).asObject());
		agentsTableColumnClassName.setCellValueFactory(
				cellData -> new ReadOnlyStringWrapper(
						cellData.getValue().getClass().getSimpleName()));

		ObservableList<HighLevelController> agents = FXCollections
				.observableArrayList();
		for (HighLevelController hlc : sim2dGUI.getBuilding()
				.getScheduledControllers())
		{
			if (hlc != null)
				if (!hlc.getClass().isAnonymousClass())
				{
					agents.add(hlc);
				}
		}
		agentsTable.setItems(agents);
	}

	private void configureLayerList()
	{

		this.floorList = FXCollections.observableArrayList();
		this.sim2dGUI
				.getBuilding()
				.getFloors()
				.stream()
				.map(DrawableFloor::new)
				.forEach(this.floorList::add);
		this.floorCombobox = new ComboBox<DrawableFloor>(this.floorList);

		this.floorCombobox
				.setCellFactory(ComboBoxListCell.forListView(this.floorList));
		this.layersList = FXCollections.observableArrayList();
		Arrays.stream(this.sim2dGUI.getLayers())
				.map(DrawableLayerWrapper::new)
				.forEach(this.layersList::add);

		this.layerListView = new CheckListView<>(layersList);
		layerListView.setCellFactory(
				CheckBoxListCell.forListView(c -> c.isEnabled()));
		this.layersListPane.setContent(this.layerListView);

	}

	private void configureSimulation2DGraphics()
	{
		// Transform. Width

		this.pzc = new PanAndZoomCanvas(
				this.simulationFrameWrapper.getWidth(),
				this.simulationFrameWrapper.getHeight(),
				this.floorList.get(0).getMinX(),
				this.floorList.get(0).getMaxX());

		this.simulationFrameWrapper.getChildren().add(pzc);

		pzc.widthProperty().bind(this.simulationFrameWrapper.widthProperty());
		pzc.heightProperty().bind(this.simulationFrameWrapper.heightProperty());
		pzc.toFront();
		pzc.onRender(g2d -> {
			DrawableLayer<DrawableFloor>[] layers = sim2dGUI.getLayers();
			for (int i = 0; i < layers.length; i++)
			{
				final DrawableFloor df = this.floorCombobox.getSelectionModel()
						.getSelectedItem();
				if (layers[i].isEnabled())
				{
					layers[i].draw(df, g2d);
				}
			}
		});
		this.floorCombobox.getSelectionModel().selectFirst();
		pzc.startActiveRendering();
	}

	private class DrawableLayerWrapper {
		DrawableLayer<DrawableFloor> layer;
		BooleanProperty enabled;

		public DrawableLayerWrapper(DrawableLayer<DrawableFloor> layer)
		{
			this.layer = layer;
			this.enabled = new SimpleBooleanProperty(layer.isEnabled());
			this.enabled.addListener((obs, o, n) -> {
				this.layer.setEnabled(n);
				AppController.this.pzc.render();
			});
		}

		public BooleanProperty isEnabled()
		{
			return this.enabled;
		}

		public String toString()
		{
			return this.layer.getName();
		}
	}

	@Override
	public void animate(SimulationObject obj)
	{
		this.pzc.render();
	}

	@Override
	public boolean isDisplayEnabled()
	{
		return true;
	}

	public <T extends HighLevelController> void addChartHandler(
			Class<T> agentClass,
			Class<? extends AgentChartCollection<T>> handler)
	{
		this.chartHandlers.put(agentClass, handler);
	}

}
