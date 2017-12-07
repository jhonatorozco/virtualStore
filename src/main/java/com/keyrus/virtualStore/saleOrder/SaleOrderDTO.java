package com.keyrus.virtualStore.saleOrder;

import com.keyrus.virtualStore.customer.CustomerModel;
import com.keyrus.virtualStore.product.ProductModel;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SaleOrderDTO implements Serializable {

    private Long id;

    private CustomerModel customerOrder;

    private float totalPrice;

    private Date saleOrderDate;

    private List<ProductModel> products;

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

    public List<ProductModel> getProducts() {
        return products;
    }

    public void setProducts(List<ProductModel> products) {
        this.products = products;
    }
}
