package com.keyrus.virtualStore.orderProduct;

import com.keyrus.virtualStore.product.ProductModel;
import com.keyrus.virtualStore.order.OrderModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
/**
 * Class that represents the orderProduct table in the database.
 * @author Jhonatan Orozco
 * @version 1
 */

@Entity
@Table(name = "orderProduct")
public class OrderProductModel implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "orderId")
    @ManyToOne(optional = false)
    private OrderModel order;

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

    public OrderModel getSaleOrder() {
        return order;
    }

    public void setSaleOrder(OrderModel order) {
        this.order = order;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderProductModel that = (OrderProductModel) o;
        return quantity == that.quantity &&
                Objects.equals(id, that.id) &&
                Objects.equals(order, that.order) &&
                Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, order, product, quantity);
    }
}
