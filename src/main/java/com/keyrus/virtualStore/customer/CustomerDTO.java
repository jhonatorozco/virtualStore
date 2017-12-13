package com.keyrus.virtualStore.customer;


import com.keyrus.virtualStore.order.OrderDTO;

import java.io.Serializable;
import java.util.List;
/**
 * Data transfer object for CustomerModel entity
 * @author Jhonatan Orozco
 * @version 1
 */
public class CustomerDTO implements Serializable{

    private String name;
    private String email;
    private String address;
    private List<OrderDTO> orders;

    public CustomerDTO(){}

    public CustomerDTO (CustomerModel customer){

        this.name = customer.getName();
        this.email = customer.getEmail();
        this.address = customer.getAddress();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<OrderDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDTO> orders) {
        this.orders = orders;
    }
}
