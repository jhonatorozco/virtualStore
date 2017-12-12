package com.keyrus.virtualStore.saleOrderProduct;

import com.keyrus.virtualStore.exception.VirtualStoreException;


import java.util.List;

/**
 * Interface used to expose the available operations for SaleOrderProductModel
 * @author Jhonatan Orozco
 * @version 1
 *
 */

public interface ISaleOrderProductService {

    /**
     * Add saleOrder to database.
     * @param saleOrderProduct
     * @throws VirtualStoreException When there is an issue with the database connection or HQL query. or
     *  the user doesn't fill up a required field of SaleOrderModel object
     */
    void addSaleOrderProduct(SaleOrderProductModel saleOrderProduct) throws VirtualStoreException;

    /**
     * Update the found saleOrderProduct with the new info provided by updatedSaleOrderProduct
     * @param id Identification of the saleOrderProduct to be updated
     * @param updatedSaleOrderProduct SaleOrderModel object with the new information of the saleOrderProduct.
     * @return The SaleOrderModel object stored in the database
     * @throws VirtualStoreException When the saleOrderProduct doesn't exist in the database
     */
    SaleOrderProductModel updateSaleOrderProduct(Long id, SaleOrderProductModel updatedSaleOrderProduct) throws VirtualStoreException;

    /**
     * Find saleOrderProduct with related id.
     * @param id Identification of the saleOrderProduct to be found
     * @return The SaleOrderModel object stored in the database with this id
     * @throws VirtualStoreException When the saleOrderProduct doesn't exist in the database
     */
    SaleOrderProductModel findSaleOrderProduct(Long id) throws VirtualStoreException;

    /**
     * Find all the saleOrderProduct stored in the saleOrderProduct table.
     * @return List of saleOrderProducts stored in the database
     * @throws VirtualStoreException When there is an issue with the database connection or HQL query.
     */
    List<SaleOrderProductModel> findAll() throws VirtualStoreException;

    /**
     * Delete saleOrderProduct with related id
     * @param id Identification of the saleOrderProduct to be deleted
     * @throws VirtualStoreException When the saleOrderProduct doesn't exist in the database
     */
    void deleteSaleOrderProduct(Long id) throws VirtualStoreException;

    /**
     *
     * @param orderProducts
     * @throws VirtualStoreException
     */
    void updateSaleOrderProducts(List<SaleOrderProductModel> orderProducts) throws VirtualStoreException;
}
