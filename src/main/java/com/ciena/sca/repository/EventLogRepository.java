package com.ciena.sca.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.RepositoryDefinition;

import com.ciena.sca.domain.EventLog;


@RepositoryDefinition(domainClass = EventLog.class, idClass = String.class)
public interface EventLogRepository {
	EventLog save(EventLog e);

	EventLog findOne(String eventId);

	List<EventLog> findAll();
		
	Page<EventLog> findAll(Pageable p);	
	
	Long count();

	void delete(EventLog e);

	boolean exists(String eventId);

}