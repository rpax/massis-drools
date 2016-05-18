package com.massisframework.logging;

public interface LogLine {
	public long getTick();
	public String getLevel();
	public String getMessage();
}
