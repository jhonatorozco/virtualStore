package com.keyrus.virtualStore.saleOrder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Central interface used to define CRUD operations on the SaleOrderModel
 * @author Jhonatan Orozco
 * @version 1
 */

@Repository
public interface ISaleOrderRepository extends JpaRepository<SaleOrderModel,Long>{

}

