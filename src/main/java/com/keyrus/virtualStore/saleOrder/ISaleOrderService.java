package com.keyrus.virtualStore.saleOrder;

import com.keyrus.virtualStore.exception.VirtualStoreException;
import com.keyrus.virtualStore.saleOrderProduct.SaleOrderProductModel;

import java.util.List;

public interface ISaleOrderService {

    void addSaleOrder(SaleOrderModel SaleOrder) throws VirtualStoreException;
    SaleOrderModel updateSaleOrder(Long id, SaleOrderModel SaleOrder) throws VirtualStoreException;
    SaleOrderModel findSaleOrder(Long id) throws VirtualStoreException;
    List<SaleOrderModel> findAll() throws VirtualStoreException;
    void deleteSaleOrder(Long id) throws VirtualStoreException;
    List<SaleOrderProductModel> findProducts(Long id) throws VirtualStoreException;
}
