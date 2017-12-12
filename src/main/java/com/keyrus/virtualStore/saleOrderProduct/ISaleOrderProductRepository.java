package com.keyrus.virtualStore.saleOrderProduct;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Central interface used to define CRUD operations on the SaleOrderProductModel
 * @author Jhonatan Orozco
 * @version 1
 */

@Repository
public interface ISaleOrderProductRepository extends JpaRepository<SaleOrderProductModel,Long>{
}
