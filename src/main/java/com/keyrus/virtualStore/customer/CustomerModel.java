package com.keyrus.virtualStore.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.keyrus.virtualStore.saleOrder.SaleOrderModel;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customer")
public class CustomerModel {
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic(optional = false)
    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Basic(optional = false)
    @Column(name = "password")
    private String password;

    @Column(name = "address")
    private String address;

    @JsonIgnore
    @OneToMany(mappedBy = "customerOrder", cascade = CascadeType.ALL)
    private List<SaleOrderModel> orders;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<SaleOrderModel> getOrders() {
        return orders;
    }

    public void setOrders(List<SaleOrderModel> orders) {
        this.orders = orders;
    }
}
