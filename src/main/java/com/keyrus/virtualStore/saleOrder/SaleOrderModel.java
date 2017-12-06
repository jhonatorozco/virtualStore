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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "clientId")
    @ManyToOne(optional = false)
    private ClientModel clientOrder;

    @Column(name = "totalPrice")
    private float totalPrice;

    @Column(name = "saleOrderDate")
    private Date saleOrderDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClientModel getClientOrder() {
        return clientOrder;
    }

    public void setClientOrder(ClientModel clientOrder) {
        this.clientOrder = clientOrder;
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
