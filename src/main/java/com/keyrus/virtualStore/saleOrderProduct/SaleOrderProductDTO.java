package com.keyrus.virtualStore.saleOrderProduct;

import com.keyrus.virtualStore.product.ProductModel;

import java.io.Serializable;

public class SaleOrderProductDTO implements Serializable {

    private ProductModel product;
    private int quantity;

    public SaleOrderProductDTO(){}

    public SaleOrderProductDTO(SaleOrderProductModel saleOrderProduct){

        this.product = saleOrderProduct.getSaleOrderProductId().getProduct();
        this.quantity = saleOrderProduct.getQuantity();
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
}
