package com.keyrus.virtualStore.customer;

import com.keyrus.virtualStore.exception.VirtualStoreException;
import com.keyrus.virtualStore.saleOrder.SaleOrderModel;


import java.util.List;

public interface ICustomerService {

    void addCustomer(CustomerModel customer) throws VirtualStoreException;
    CustomerModel updateCustomer(Long id, CustomerModel customer) throws VirtualStoreException;
    CustomerModel findCustomer(Long id) throws VirtualStoreException;
    List<CustomerModel> findAll() throws VirtualStoreException;
    void deleteCustomer(Long id) throws VirtualStoreException;
    List<SaleOrderModel> findOrders(Long id) throws VirtualStoreException;
}
