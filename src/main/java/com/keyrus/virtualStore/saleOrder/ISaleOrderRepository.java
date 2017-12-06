package com.keyrus.virtualStore.saleOrder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISaleOrderRepository extends JpaRepository<SaleOrderModel,Long>{

}

