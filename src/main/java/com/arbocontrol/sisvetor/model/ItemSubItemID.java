package com.arbocontrol.sisvetor.model;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.math.BigInteger;
@Data
@Embeddable
public class ItemSubItemID implements Serializable {
    private BigInteger item_id;
    private BigInteger sub_item_id;
}
