package com.keyrus.virtualStore.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.keyrus.virtualStore.customer.CustomerModel;
import com.keyrus.virtualStore.orderProduct.OrderProductModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * Class that represents the order table in the database.
 * @author Jhonatan Orozco
 * @version 1
 */

@Entity
@Table(name = "saleOrder")
public class OrderModel implements Serializable{
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

    @Column(name = "orderDate")
    private Date orderDate;

    @JsonIgnore
    @OneToMany(mappedBy = "order", cascade = CascadeType.REMOVE)
    private List<OrderProductModel> productsOrder;

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

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public List<OrderProductModel> getProductsOrder() {
        return productsOrder;
    }

    public void setProductsOrder(List<OrderProductModel> productsOrder) {
        this.productsOrder = productsOrder;
    }
}
