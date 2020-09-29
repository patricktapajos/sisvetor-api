package com.arbocontrol.sisvetor.model;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigInteger;

@Entity
@Data
public class ItemSubItem {
    @Id
    private ItemSubItemID id;
}
