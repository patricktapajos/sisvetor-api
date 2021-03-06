package com.arbocontrol.sisvetor.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigInteger;

@Entity
@NoArgsConstructor
@Data
public class ItemSubItem {
    @Id
    private ItemSubItemID id;
    @Column(insertable = false, updatable = false)
    private BigInteger item_id;
    @Column(insertable = false, updatable = false)
    private BigInteger sub_item_id;
}
