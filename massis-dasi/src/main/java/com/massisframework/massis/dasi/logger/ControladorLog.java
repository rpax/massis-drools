package com.massisframework.massis.dasi.logger;

import com.massisframework.massis.dasi.apps.robots.RobotAgent;

public class ControladorLog {
	private static ControladorLog f;
	private static LoggerWindow veLog;
	
	public static ControladorLog getInstance()
	{
		if(f==null)
		{	
			f = new ControladorLog();
			veLog = new LoggerWindow();
			veLog.setVisible(true);
		}
		return f;
	}
	
	public  void addAgent(RobotAgent robot)
	{
		LogicaLoggerWindow.addAgent(veLog, robot);
	}
	public  void appendInfo(String id, String texto, String tipo)
	{
		LogicaLoggerWindow.appendInfo(veLog,id,texto,tipo);
	}
}
