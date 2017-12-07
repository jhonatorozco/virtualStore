package com.keyrus.virtualStore.saleOrder;

import com.keyrus.virtualStore.exception.VirtualStoreException;
import com.keyrus.virtualStore.saleOrderProduct.SaleOrderProductDTO;

import java.util.List;

public interface ISaleOrderService {

    void addSaleOrder(SaleOrderDTO SaleOrder) throws VirtualStoreException;
    SaleOrderModel updateSaleOrder(Long id, SaleOrderModel SaleOrder) throws VirtualStoreException;
    SaleOrderModel findSaleOrder(Long id) throws VirtualStoreException;
    List<SaleOrderDTO> findAll() throws VirtualStoreException;
    void deleteSaleOrder(Long id) throws VirtualStoreException;
    List<SaleOrderProductDTO> findProducts(Long id) throws VirtualStoreException;
}
