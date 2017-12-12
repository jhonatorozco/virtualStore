package com.keyrus.virtualStore.saleOrderProduct;

import com.keyrus.virtualStore.exception.VirtualStoreException;


import java.util.List;

public interface ISaleOrderProductService {

    void addSaleOrderProduct(SaleOrderProductModel saleOrderProduct) throws VirtualStoreException;
    SaleOrderProductModel updateSaleOrderProduct(Long id, SaleOrderProductModel saleOrderProduct) throws VirtualStoreException;
    SaleOrderProductModel findSaleOrderProduct(Long id) throws VirtualStoreException;
    List<SaleOrderProductModel> findAll() throws VirtualStoreException;
    void deleteSaleOrderProduct(Long id) throws VirtualStoreException;
    void updateSaleOrderProducts(List<SaleOrderProductModel> orderProducts) throws VirtualStoreException;
}
