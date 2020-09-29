package com.arbocontrol.sisvetor.repository;

import com.arbocontrol.sisvetor.model.ItemSubItem;
import com.arbocontrol.sisvetor.model.ItemSubItemID;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.List;

public interface ItemSubItemRepository extends CrudRepository<ItemSubItem, ItemSubItemID> {}
