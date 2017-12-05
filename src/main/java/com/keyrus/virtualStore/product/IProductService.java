package com.keyrus.virtualStore.product;


import com.keyrus.virtualStore.exception.VirtualStoreException;

import java.util.List;

public interface IProductService {

    void addProduct(ProductModel product) throws VirtualStoreException;
    void updateProduct(ProductModel product) throws VirtualStoreException;
    ProductModel findProduct(Long id) throws VirtualStoreException;
    List<ProductModel> findAll() throws VirtualStoreException;
    void deleteProduct(Long id) throws VirtualStoreException;


}