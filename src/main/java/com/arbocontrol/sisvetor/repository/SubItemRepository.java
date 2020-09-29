package com.arbocontrol.sisvetor.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.arbocontrol.sisvetor.entity.SubItem;
import java.math.BigInteger;

//Remove @RepositoryRestResource below to disable auto REST api:
@RepositoryRestResource
public interface SubItemRepository extends PagingAndSortingRepository<SubItem, BigInteger> {
    @Query("FROM SubItem i WHERE LOWER(i.nome) like %:nome% ")
    Page<SubItem> findAllByNome(@Param("nome") String nome, Pageable pageable);
}