package com.keyrus.virtualStore.saleOrderProduct;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISaleOrderProductRepository extends JpaRepository<SaleOrderProductModel,Long>{
}
