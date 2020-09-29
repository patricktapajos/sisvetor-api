package com.arbocontrol.sisvetor.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.arbocontrol.sisvetor.model.Item;

import java.math.BigInteger;

//Remove @RepositoryRestResource below to disable auto REST api:
@RepositoryRestResource
public interface ItemRepository extends CrudRepository<Item, BigInteger> {
    @Query(value = "DELETE FROM item_sub_item WHERE item_id = :itemId and sub_item_id = :subId", nativeQuery = true)
    void deleteItemSubItem(@Param("itemId") BigInteger item_id, @Param("subId") BigInteger sub_item_id);
}