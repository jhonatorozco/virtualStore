package com.keyrus.virtualStore.order;

import com.keyrus.virtualStore.exception.VirtualStoreException;
import com.keyrus.virtualStore.orderProduct.OrderProductDTO;

import java.util.List;


/**
 * Interface used to expose the available operations for OrderModel
 * @author Jhonatan Orozco
 * @version 1
 *
 */

public interface IOrderService {

    /**
     * Add order to database.
     * @param order
     * @throws VirtualStoreException When there is an issue with the database connection or HQL query. or
     *  the user doesn't fill up a required field of OrderModel object
     */
    OrderDTO addOrder(OrderDTO order) throws VirtualStoreException;

    /**
     * Update the found order with the new info provided by updatedOrder
     * @param id Identification of the order to be updated
     * @param updatedOrder OrderModel object with the new information of the order.
     * @return The OrderModel object stored in the database
     * @throws VirtualStoreException When the order doesn't exist in the database
     */
    OrderDTO updateOrder(Long id, OrderDTO updatedOrder) throws VirtualStoreException;

    /**
     * Find order with related id.
     * @param id Identification of the order to be found
     * @return The OrderModel object stored in the database with this id
     * @throws VirtualStoreException When the order doesn't exist in the database
     */
    OrderDTO findOrder(Long id) throws VirtualStoreException;

    /**
     * Find all the order stored in the order table.
     * @return List of saleOrders stored in the database
     * @throws VirtualStoreException When there is an issue with the database connection or HQL query.
     */
    List<OrderDTO> findAll() throws VirtualStoreException;

    /**
     * Delete order with related id
     * @param id Identification of the order to be deleted
     * @throws VirtualStoreException When the order doesn't exist in the database
     */
    void deleteOrder(Long id) throws VirtualStoreException;

    /**
     * Find the saleOrderProducts related to the Order object that was retrieved by the id
     * @param id Identification of the order
     * @return List of saleOrderProducts belonging to found Order by the id. In case of the order
     * doesn't have products , this field appears empty
     * @throws VirtualStoreException When there is an issue with the database connection or HQL query.
     */
    List<OrderProductDTO> findProducts(Long id) throws VirtualStoreException;


    /**
     * Update the list of products related to the Order object that was retrieved by the id.
     * @param id Ifentification of the order
     * @param saleOrderProducts List of saleOrderProducts
     * @return  Updated OrderProductModel object with the new list of products.
     * @throws VirtualStoreException When a product doesn't have the sufficient units to attend the sale order.
     * The order doesn't exist in the database
     */
    OrderModel updateProducts(Long id, List<OrderProductDTO> saleOrderProducts) throws VirtualStoreException;
}
