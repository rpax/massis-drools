package com.massisframework.massis.dasi.apps.robots.leader.info;

public class HelloAnswerInfo {
	
	private String uuid;
	private Object sender;
	public HelloAnswerInfo(String uuid, Object sender) {
		super();
		this.uuid = uuid;
		this.sender = sender;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Object getSender() {
		return sender;
	}
	public void setSender(Object sender) {
		this.sender = sender;
	}
	
	

}
