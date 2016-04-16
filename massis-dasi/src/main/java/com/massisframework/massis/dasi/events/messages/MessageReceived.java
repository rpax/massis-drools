package com.massisframework.massis.dasi.events.messages;

import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Role.Type;

@Role(Type.EVENT)
public class MessageReceived<S, D>  {

	private S sender;
	private D data;

	public S getSender()
	{
		return sender;
	}

	public void setSender(S sender)
	{
		this.sender = sender;
	}

	public D getData()
	{
		return data;
	}

	public void setData(D data)
	{
		this.data = data;
	}

	public MessageReceived(S sender, D data)
	{
		super();
		this.sender = sender;
		this.data = data;
	}

	
}
