package com.ciena.sca.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection="EventLog")
public class EventLog {

	@Id
	private String id;
	private String actor;
	private EventType logType;
	private Date logDate;
	private String logData;

	// create default ctor used for Jackson mapping
	public EventLog() {}

	public EventLog(EventType logType, String logData) {
		this.logType = logType;
		this.logData = logData;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getActor() {
		return actor;
	}
	public void setActor(String actor) {
		this.actor = actor;
	}
	public EventType getLogType() {
		return logType;
	}
	public void setLogType(EventType logType) {
		this.logType = logType;
	}
	public Date getLogDate() {
		return logDate;
	}
	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}
	public String getLogData() {
		return logData;
	}
	public void setLogData(String logData) {
		this.logData = logData;
	}
	
}
