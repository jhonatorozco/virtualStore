package com.keyrus.virtualStore.saleOrder;

import com.keyrus.virtualStore.exception.VirtualStoreException;
import com.keyrus.virtualStore.saleOrderProduct.SaleOrderProductDTO;

import java.util.List;


/**
 * Interface used to expose the available operations for SaleOrderModel
 * @author Jhonatan Orozco
 * @version 1
 *
 */

public interface ISaleOrderService {

    /**
     * Add saleOrder to database.
     * @param saleOrder
     * @throws VirtualStoreException When there is an issue with the database connection or HQL query. or
     *  the user doesn't fill up a required field of SaleOrderModel object
     */
    void addSaleOrder(SaleOrderDTO saleOrder) throws VirtualStoreException;

    /**
     * Update the found saleOrder with the new info provided by updatedSaleOrder
     * @param id Identification of the saleOrder to be updated
     * @param updatedSaleOrder SaleOrderModel object with the new information of the saleOrder.
     * @return The SaleOrderModel object stored in the database
     * @throws VirtualStoreException When the saleOrder doesn't exist in the database
     */
    SaleOrderDTO updateSaleOrder(Long id, SaleOrderDTO updatedSaleOrder) throws VirtualStoreException;

    /**
     * Find saleOrder with related id.
     * @param id Identification of the saleOrder to be found
     * @return The SaleOrderModel object stored in the database with this id
     * @throws VirtualStoreException When the saleOrder doesn't exist in the database
     */
    SaleOrderDTO findSaleOrder(Long id) throws VirtualStoreException;

    /**
     * Find all the saleOrder stored in the saleOrder table.
     * @return List of saleOrders stored in the database
     * @throws VirtualStoreException When there is an issue with the database connection or HQL query.
     */
    List<SaleOrderDTO> findAll() throws VirtualStoreException;

    /**
     * Delete saleOrder with related id
     * @param id Identification of the saleOrder to be deleted
     * @throws VirtualStoreException When the saleOrder doesn't exist in the database
     */
    void deleteSaleOrder(Long id) throws VirtualStoreException;

    /**
     * Find the saleOrderProducts related to the SaleOrder object that was retrieved by the id
     * @param id Identification of the saleOrder
     * @return List of saleOrderProducts belonging to found SaleOrder by the id. In case of the saleOrder 
     * doesn't have products , this field appears empty
     * @throws VirtualStoreException When there is an issue with the database connection or HQL query.
     */
    List<SaleOrderProductDTO> findProducts(Long id) throws VirtualStoreException;


    /**
     * Update the list of products related to the SaleOrder object that was retrieved by the id.
     * @param id Ifentification of the saleOrder
     * @param saleOrderProducts List of saleOrderProducts
     * @return  Updated SaleOrderProductModel object with the new list of products.
     * @throws VirtualStoreException When a product doesn't have the sufficient units to attend the sale order.
     * The saleOrder doesn't exist in the database
     */
    SaleOrderModel updateProducts(Long id, List<SaleOrderProductDTO> saleOrderProducts) throws VirtualStoreException;
}
