package com.keyrus.virtualStore.saleOrderProduct;

import com.keyrus.virtualStore.exception.VirtualStoreException;


import java.util.List;

public interface ISaleOrderProductService {

    void addSaleOrderProduct(SaleOrderProductModel saleOrderProduct) throws VirtualStoreException;
    SaleOrderProductModel updateSaleOrderProduct(SaleOrderProductIdentity id, SaleOrderProductModel saleOrderProduct) throws VirtualStoreException;
    SaleOrderProductModel findSaleOrderProduct(SaleOrderProductIdentity id) throws VirtualStoreException;
    List<SaleOrderProductModel> findAll() throws VirtualStoreException;
    void deleteSaleOrderProduct(SaleOrderProductIdentity id) throws VirtualStoreException;
}
