package com.keyrus.virtualStore.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Central interface used to define CRUD operations on the ProductModel
 * @author Jhonatan Orozco
 * @version 1
 */

@Repository
public interface IProductRepository extends JpaRepository<ProductModel, Long>{

}