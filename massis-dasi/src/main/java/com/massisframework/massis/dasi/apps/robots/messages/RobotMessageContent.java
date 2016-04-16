package com.massisframework.massis.dasi.apps.robots.messages;

public class RobotMessageContent<T> {

	private String uuid;
	private T info;

	public RobotMessageContent(T info, String uuid)
	{
		super();
		this.uuid = uuid;
		this.info = info;
	}

	public T getInfo()
	{
		return info;
	}

	public void setInfo(T info)
	{
		this.info = info;
	}

	public String getUuid()
	{
		return uuid;
	}

	public void setUuid(String uuid)
	{
		this.uuid = uuid;
	}

}
