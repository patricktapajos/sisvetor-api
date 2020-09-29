package com.arbocontrol.sisvetor.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.arbocontrol.sisvetor.entity.Item;
import java.math.BigInteger;
import java.util.Map;

//Remove @RepositoryRestResource below to disable auto REST api:
@RepositoryRestResource
public interface ItemRepository extends PagingAndSortingRepository<Item, BigInteger> {}