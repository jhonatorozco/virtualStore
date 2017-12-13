package com.keyrus.virtualStore.orderProduct;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Central interface used to define CRUD operations on the OrderProductModel
 * @author Jhonatan Orozco
 * @version 1
 */

@Repository
public interface IOrderProductRepository extends JpaRepository<OrderProductModel,Long>{
}
