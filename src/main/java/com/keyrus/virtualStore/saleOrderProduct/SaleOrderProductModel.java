package com.keyrus.virtualStore.saleOrderProduct;

import com.keyrus.virtualStore.product.ProductModel;
import com.keyrus.virtualStore.saleOrder.SaleOrderModel;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "saleOrderProduct")
public class SaleOrderProductModel implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "saleOrderId")
    @ManyToOne(optional = false)
    private SaleOrderModel saleOrder;

    @JoinColumn(name = "productId")
    @ManyToOne(optional = false)
    private ProductModel product;

    @Column(name = "quantity")
    @Basic(optional = false)
    private int quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SaleOrderModel getSaleOrder() {
        return saleOrder;
    }

    public void setSaleOrder(SaleOrderModel saleOrder) {
        this.saleOrder = saleOrder;
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
