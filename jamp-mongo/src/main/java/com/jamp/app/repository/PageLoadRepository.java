package com.jamp.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.jamp.app.domain.PageLoadEvent;

@RepositoryRestResource(collectionResourceRel = "pageLoad", path = "pageLoad")
public interface PageLoadRepository extends MongoRepository<PageLoadEvent, String>{
	
}
