package com.keyrus.virtualStore.orderProduct;

import com.keyrus.virtualStore.exception.VirtualStoreException;


import java.util.List;

/**
 * Interface used to expose the available operations for OrderProductModel
 * @author Jhonatan Orozco
 * @version 1
 *
 */

public interface IOrderProductService {

    /**
     * Add orderProduct to database.
     * @param orderProduct
     * @throws VirtualStoreException When there is an issue with the database connection or HQL query. or
     *  the user doesn't fill up a required field of OrderModel object
     */
    OrderProductModel addOrderProduct(OrderProductModel orderProduct) throws VirtualStoreException;

    /**
     * Update the found orderProduct with the new info provided by updatedOrderProduct
     * @param id Identification of the orderProduct to be updated
     * @param updatedOrderProduct OrderModel object with the new information of the orderProduct.
     * @return The OrderModel object stored in the database
     * @throws VirtualStoreException When the orderProduct doesn't exist in the database
     */
    OrderProductModel updateOrderProduct(Long id, OrderProductModel updatedOrderProduct) throws VirtualStoreException;

    /**
     * Find orderProduct with related id.
     * @param id Identification of the orderProduct to be found
     * @return The OrderModel object stored in the database with this id
     * @throws VirtualStoreException When the orderProduct doesn't exist in the database
     */
    OrderProductModel findOrderProduct(Long id) throws VirtualStoreException;

    /**
     * Find all the orderProduct stored in the orderProduct table.
     * @return List of orderProducts stored in the database
     * @throws VirtualStoreException When there is an issue with the database connection or HQL query.
     */
    List<OrderProductModel> findAll() throws VirtualStoreException;

    /**
     * Delete orderProduct with related id
     * @param id Identification of the orderProduct to be deleted
     * @throws VirtualStoreException When the orderProduct doesn't exist in the database
     */
    void deleteOrderProduct(Long id) throws VirtualStoreException;

    /**
     *
     * @param orderProducts
     * @throws VirtualStoreException
     */
    void updateOrderProducts(List<OrderProductModel> orderProducts) throws VirtualStoreException;
}
