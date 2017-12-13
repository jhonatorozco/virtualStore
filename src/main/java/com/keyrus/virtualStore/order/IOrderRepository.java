package com.keyrus.virtualStore.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Central interface used to define CRUD operations on the OrderModel
 * @author Jhonatan Orozco
 * @version 1
 */

@Repository
public interface IOrderRepository extends JpaRepository<OrderModel,Long>{

}

