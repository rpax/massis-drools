package com.massisframework.massis.dasi.logger;

import javax.swing.DefaultListModel;

import com.massisframework.massis.dasi.apps.robots.RobotAgent;

public class LogicaLoggerWindow {
	
	public static void addAgent(LoggerWindow ventana,RobotAgent robot)
	{
		((DefaultListModel<RobotAgent>) ventana.getListaAgentes().getModel()).addElement(robot);
		ventana.addTab(robot.toString());
	}
	
	public static void appendInfo(LoggerWindow ventana, String id, String texto, String tipo)
	{
		ventana.appendInfo(id, texto, tipo);
	}
}
