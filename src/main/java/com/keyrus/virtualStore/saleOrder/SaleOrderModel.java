package com.keyrus.virtualStore.saleOrder;

import com.keyrus.virtualStore.client.ClientModel;
import com.sun.deploy.util.SessionState;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "saleOrder")
public class SaleOrderModel implements Serializable{
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "clientId",referencedColumnName = "id")
    private ClientModel client;

    @Column(name = "totalPrice")
    private float totalPrice;

    @Column(name = "id")
    private Date saleOrderDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClientModel getClient() {
        return client;
    }

    public void setClient(ClientModel client) {
        this.client = client;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getSaleOrderDate() {
        return saleOrderDate;
    }

    public void setSaleOrderDate(Date saleOrderDate) {
        this.saleOrderDate = saleOrderDate;
    }
}
