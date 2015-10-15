package com.ciena.sca.domain;

public enum EventType {
	EVENT_SECURITY ("Security"),
	EVENT_USER_ADMINISTATION ("User Admin"),
	EVENT_USER_CREATED("User created"),
	EVENT_USER_MODIFIED("User modified"),
	EVENT_USER_DELETED("User deleted"),
	EVENT_MEF_SERVICE_CREATED("MEF Service created"),
	EVENT_MEF_SERVICE_MODIFIED("MEF Service modified"),
	EVENT_MEF_SERVICE_DELETED("MEF Service deleted");
	
	private final String type;
	
	private EventType(String type)		{this.type = type;}
	public String getType()				{return this.type;}
}
