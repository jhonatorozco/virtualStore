package com.keyrus.virtualStore.product;


import com.keyrus.virtualStore.exception.VirtualStoreException;

import java.util.List;

/**
 * Interface used to expose the available operations for ProductModel
 * @author Jhonatan Orozco
 * @version 1
 */

public interface IProductService {


    /**
     * Add product to database.
     * @param product
     * @throws VirtualStoreException When there is an issue with the database connection or HQL query
     *  the user doesn't fill up a required field of ProductModel object
     */
    void addProduct(ProductModel product) throws VirtualStoreException;

    /**
     * Update the found product with the new info provided by updatedProduct
     * @param id Identification of the product to be updated
     * @param updatedProduct ProductModel object with the new information of the product.
     * @return The ProductModel object stored in the database
     * @throws VirtualStoreException When the customer doesn't exist in the database
     */
    ProductModel updateProduct(Long id, ProductModel updatedProduct) throws VirtualStoreException;

    /**
     * Find product with related id.
     * @param id Identification of the customer to be found
     * @return The ProductModel object stored in the database with this id
     * @throws VirtualStoreException When the product doesn't exist in the database
     */
    ProductModel findProduct(Long id) throws VirtualStoreException;

    /**
     * Find all the product stored in the product table.
     * @return List of products stored in the database
     * @throws VirtualStoreException When there is an issue with the database connection or HQL query.
     */
    List<ProductModel> findAll() throws VirtualStoreException;

    /**
     * Delete product with related id
     * @param id Identification of the product to be deleted
     * @throws VirtualStoreException When the product doesn't exist in the database
     */
    void deleteProduct(Long id) throws VirtualStoreException;


}