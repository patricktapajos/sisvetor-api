package com.arbocontrol.sisvetor.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.arbocontrol.sisvetor.model.Item;

import java.math.BigInteger;

//Remove @RepositoryRestResource below to disable auto REST api:
@RepositoryRestResource
public interface ItemRepository extends CrudRepository<Item, BigInteger>{}