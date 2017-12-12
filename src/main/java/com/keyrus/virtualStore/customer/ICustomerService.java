package com.keyrus.virtualStore.customer;

import com.keyrus.virtualStore.exception.VirtualStoreException;


import java.util.List;
/**
 * Interface used to expose the available operations for CustomerModel
 * @author Jhonatan Orozco
 * @version 1
 */
public interface ICustomerService {

    /**
     * Add customer to database.
     * @param customer
     * @throws VirtualStoreException When there is an issue with the database connection or HQL query
     *  the user doesn't fill up a required field of CustomerModel object
     */
    void addCustomer(CustomerModel customer) throws VirtualStoreException;

    /**
     * Update the customer with related id with the updatedCustomer
     * @param id Identification of the customer to be updated
     * @param updatedCustomer CustomerModel object with the new information of the customer.
     * @return The CustomerModel object stored in the database
     * @throws VirtualStoreException When the customer doesn't exist in the database
     */
    CustomerModel updateCustomer(Long id, CustomerModel updatedCustomer) throws VirtualStoreException;

    /**
     * Find customer with related id.
     * @param id Identification of the customer to be found
     * @return The CustomerModel object stored in the database with this id
     * @throws VirtualStoreException When the customer doesn't exist in the database
     */
    CustomerDTO findCustomer(Long id) throws VirtualStoreException;

    /**
     * Find all the customers stored in the customer table.
     * @return List of customers stored in the database
     * @throws VirtualStoreException When there is an issue with the database connection or HQL query.
     */
    List<CustomerModel> findAll() throws VirtualStoreException;

    /**
     * Delete customer with related id
     * @param id Identification of the customer to be delated
     * @throws VirtualStoreException When the customer doesn't exist in the database
     */
    void deleteCustomer(Long id) throws VirtualStoreException;


    /**
     * Find the sale orders belonging to the customer with related id.
     * @param id Identification of the customer
     * @return A CustomerDTO object with the info of the customer and the detail of his sale orders.
     * In case of the user doesn't have sale orders , this field appears empty
     * @throws VirtualStoreException When the customer doesn't exist in the database
     */
    CustomerDTO findOrders(Long id) throws VirtualStoreException;
}
