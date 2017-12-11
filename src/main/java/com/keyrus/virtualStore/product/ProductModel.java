package com.keyrus.virtualStore.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.keyrus.virtualStore.saleOrderProduct.SaleOrderProductModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Java Transfer Object for product table.
 */
@Entity
@Table(name ="product")
public class ProductModel implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic(optional = false)
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Basic(optional = false)
    @Column(name = "price")
    private float price;

    @Basic(optional = false)
    @Column(name = "availableQuantity")
    private int availableQuantity;

    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<SaleOrderProductModel> productsOrder;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public List<SaleOrderProductModel> getProductsOrder() {
        return productsOrder;
    }

    public void setProductsOrder(List<SaleOrderProductModel> productsOrder) {
        this.productsOrder = productsOrder;
    }
}
