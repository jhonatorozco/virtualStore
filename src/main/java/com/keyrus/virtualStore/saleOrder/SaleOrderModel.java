package com.keyrus.virtualStore.saleOrder;

import com.keyrus.virtualStore.customer.CustomerModel;

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

    @JoinColumn(name = "customerId")
    @ManyToOne(optional = false)
    private CustomerModel customerOrder;

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

    public CustomerModel getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerModel customerOrder) {
        this.customerOrder = customerOrder;
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
