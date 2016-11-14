package com.jamp.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.jamp.app.domain.LogEvent;

@RepositoryRestResource(collectionResourceRel = "logEvent", path = "logEvent")
public interface LogEventRepository extends MongoRepository<LogEvent, String>{

}
