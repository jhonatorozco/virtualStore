package com.keyrus.virtualStore.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Central interface used to define CRUD operations on the CustomerModel
 * @author Jhonatan Orozco
 * @version 1
 */

@Repository
public interface ICustomerRepository extends JpaRepository<CustomerModel, Long>{

    /**
     * Return the CustomerModel object related with the email.
     * @param email
     * @return
     */
    CustomerModel findByEmail(String email);

}