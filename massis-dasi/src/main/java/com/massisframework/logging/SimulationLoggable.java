package com.massisframework.logging;

public interface SimulationLoggable {

	public void info(String msg, String tipo);

	public void addLoggerProvider(LoggerProvider lp);
}
