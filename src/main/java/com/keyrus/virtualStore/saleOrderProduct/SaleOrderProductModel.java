package com.keyrus.virtualStore.saleOrderProduct;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "saleOrderProduct")
public class SaleOrderProductModel implements Serializable {

    @EmbeddedId
    private SaleOrderProductIdentity saleOrderProductId;

    @Column(name = "quantity")
    @Basic(optional = false)
    private int quantity;

    public SaleOrderProductIdentity getSaleOrderProductId() {
        return saleOrderProductId;
    }

    public void setSaleOrderProductId(SaleOrderProductIdentity saleOrderProductId) {
        this.saleOrderProductId = saleOrderProductId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
