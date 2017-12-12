package com.keyrus.virtualStore.saleOrder;

import com.keyrus.virtualStore.customer.CustomerModel;
import com.keyrus.virtualStore.saleOrderProduct.SaleOrderProductDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Data transfer object for SaleOrderModel entity
 * @author Jhonatan Orozco
 * @version 1
 */

public class SaleOrderDTO implements Serializable {

    private Long id;
    private CustomerModel customerOrder;
    private float totalPrice;
    private Date saleOrderDate;
    private List<SaleOrderProductDTO> products;

    public SaleOrderDTO(){}

    public SaleOrderDTO(SaleOrderModel saleOrderModel){

        this.id = saleOrderModel.getId();
        this.totalPrice = saleOrderModel.getTotalPrice();
        this.customerOrder = saleOrderModel.getCustomerOrder();
        this.saleOrderDate = saleOrderModel.getSaleOrderDate();
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

    public Date getSaleOrderDate() {
        return saleOrderDate;
    }

    public void setSaleOrderDate(Date saleOrderDate) {
        this.saleOrderDate = saleOrderDate;
    }

    public List<SaleOrderProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<SaleOrderProductDTO> products) {
        this.products = products;
    }
}
