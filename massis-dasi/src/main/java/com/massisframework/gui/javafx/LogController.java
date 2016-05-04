package com.massisframework.gui.javafx;

import java.util.HashMap;
import java.util.Map;

import javafx.beans.property.ReadOnlyLongWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class LogController implements LoggerProvider {

	private Map<Object, ObservableList<LogLine>> logs;
	private TableColumn<LogLine, String> agentLogLevelColumn;
	private TableColumn<LogLine, String> agentLogLevelMessage;
	private TableColumn<LogLine, Long> tickColumn;
	private TableView<LogLine> agentLog;

	public LogController(TableColumn<LogLine, String> agentLogLevelColumn,
			TableColumn<LogLine, String> agentLogLevelMessage,
			TableColumn<LogLine, Long> tickColumn)
	{
		this.logs = new HashMap<>();
		this.agentLog = agentLogLevelColumn.getTableView();
		this.agentLogLevelColumn = agentLogLevelColumn;
		this.agentLogLevelMessage = agentLogLevelMessage;
		this.tickColumn = tickColumn;
		// TODO memory footprint
		this.agentLogLevelColumn.setCellValueFactory(
				cd -> new ReadOnlyStringWrapper(cd.getValue().getLevel()));
		this.agentLogLevelMessage.setCellValueFactory(
				cd -> new ReadOnlyStringWrapper(cd.getValue().getMessage()));
		this.tickColumn.setCellValueFactory(
				cd -> new ReadOnlyLongWrapper(cd.getValue().getTick())
						.asObject());
	}

	public void setLoggedObject(Object sender)
	{
		//
		this.agentLog.setItems(this.getLogsFrom(sender));
	}

	@Override
	public void info(Object sender, long tick, String type, String msg)
	{

		this.getLogsFrom(sender).add(new LogLineImpl(tick, type, msg));
	}

	private ObservableList<LogLine> getLogsFrom(Object sender)
	{
		ObservableList<LogLine> list = this.logs.get(sender);
		if (list == null)
		{
			list = FXCollections.observableArrayList();
			this.logs.put(sender, list);
		}
		return list;
	}

	private static class LogLineImpl implements LogLine {

		private String level;
		private String msg;
		private long tick;

		public LogLineImpl(long tick, String level, String msg)
		{
			this.level = level;
			this.msg = msg;
			this.tick = tick;
		}

		@Override
		public String getLevel()
		{
			return this.level;
		}

		@Override
		public String getMessage()
		{
			return this.msg;
		}

		@Override
		public long getTick()
		{
			return this.tick;
		}

	}
}
