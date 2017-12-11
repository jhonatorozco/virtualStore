package com.keyrus.virtualStore.saleOrderProduct;

import com.keyrus.virtualStore.product.ProductModel;

import java.io.Serializable;

public class SaleOrderProductDTO implements Serializable {


    private Long id;
    private int quantity;
    private ProductModel product;

    public SaleOrderProductDTO(){}

    public SaleOrderProductDTO(SaleOrderProductModel saleOrderProduct){

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
