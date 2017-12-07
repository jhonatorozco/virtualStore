package com.keyrus.virtualStore.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends JpaRepository<CustomerModel, Long>{

    CustomerModel findByEmail(String email);

}