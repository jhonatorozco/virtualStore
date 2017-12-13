package com.keyrus.virtualStore.order;

import com.keyrus.virtualStore.customer.CustomerModel;
import com.keyrus.virtualStore.orderProduct.OrderProductDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Data transfer object for OrderModel entity
 * @author Jhonatan Orozco
 * @version 1
 */

public class OrderDTO implements Serializable {

    private Long id;
    private CustomerModel customerOrder;
    private float totalPrice;
    private Date orderDate;
    private List<OrderProductDTO> products;

    public OrderDTO(){}

    public OrderDTO(OrderModel orderModel){

        this.id = orderModel.getId();
        this.totalPrice = orderModel.getTotalPrice();
        this.customerOrder = orderModel.getCustomerOrder();
        this.orderDate = orderModel.getOrderDate();
        this.products = new ArrayList<>();
    }

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

    public List<OrderProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<OrderProductDTO> products) {
        this.products = products;
    }
}
