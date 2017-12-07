package com.keyrus.virtualStore.saleOrder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.keyrus.virtualStore.customer.CustomerModel;
import com.keyrus.virtualStore.saleOrderProduct.SaleOrderProductModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

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

    @JsonIgnore
    @OneToMany(mappedBy = "saleOrder", cascade = CascadeType.ALL)
    private List<SaleOrderProductModel> productsOrder;

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

    public List<SaleOrderProductModel> getProductsOrder() {
        return productsOrder;
    }

    public void setProductsOrder(List<SaleOrderProductModel> productsOrder) {
        this.productsOrder = productsOrder;
    }
}
