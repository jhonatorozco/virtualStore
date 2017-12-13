package com.keyrus.virtualStore.orderProduct;

import com.keyrus.virtualStore.product.ProductModel;

import java.io.Serializable;

/**
 * Data transfer object for OrderProductModel entity
 * @author Jhonatan Orozco
 * @version 1
 */

public class OrderProductDTO implements Serializable {


    private Long id;
    private int quantity;
    private ProductModel product;

    public OrderProductDTO(){}

    public OrderProductDTO(OrderProductModel saleOrderProduct){

        this.product = saleOrderProduct.getProduct();
        this.quantity = saleOrderProduct.getQuantity();
        this.id = saleOrderProduct.getId();
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
