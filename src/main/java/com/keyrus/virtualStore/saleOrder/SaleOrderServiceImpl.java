package com.keyrus.virtualStore.saleOrder;

import com.keyrus.virtualStore.exception.VirtualStoreException;
import com.keyrus.virtualStore.saleOrderProduct.SaleOrderProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateJdbcException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleOrderServiceImpl implements  ISaleOrderService{
    @Autowired
    private ISaleOrderRepository saleOrderRepository;

    @Override
    public void addSaleOrder(SaleOrderModel saleOrder) throws VirtualStoreException {
        try {
            saleOrderRepository.save(saleOrder);
        } catch (HibernateJdbcException e) {
            throw new VirtualStoreException("This operation is unavailable right now. Try later");
        }

    }

    @Override
    public SaleOrderModel updateSaleOrder(Long id, SaleOrderModel updatedSaleOrder) throws VirtualStoreException {
        SaleOrderModel saleOrder;
        try{
            saleOrder = saleOrderRepository.findOne(id);
            if(saleOrder == null){
                throw new VirtualStoreException("The sale order doesn't exist");
            }
            saleOrder.setCustomerOrder(updatedSaleOrder.getCustomerOrder());
            saleOrder.setSaleOrderDate(updatedSaleOrder.getSaleOrderDate());
            saleOrder.setTotalPrice(updatedSaleOrder.getTotalPrice());
            saleOrder = saleOrderRepository.save(saleOrder);
        }catch (VirtualStoreException e){
            throw new VirtualStoreException("This operation is unavailable right now. Try later");
        }
        return saleOrder;

    }

    @Override
    public SaleOrderModel findSaleOrder(Long id) throws VirtualStoreException {
        SaleOrderModel saleOrder;
        try {
            saleOrder = saleOrderRepository.findOne(id);
        } catch (HibernateJdbcException e) {
            throw new VirtualStoreException("This operation is unavailable right now. Try later");
        }
        return saleOrder;
    }

    @Override
    public List<SaleOrderModel> findAll() throws VirtualStoreException {
        List<SaleOrderModel> saleOrders;
        try {
            saleOrders= saleOrderRepository.findAll();
        } catch (HibernateJdbcException e) {
            throw new VirtualStoreException("This operation is unavailable right now. Try later");
        }
        return saleOrders;
    }

    @Override
    public void deleteSaleOrder(Long id) throws VirtualStoreException {
        try {
            saleOrderRepository.delete(id);
        } catch (HibernateJdbcException e) {
            throw new VirtualStoreException("This operation is unavailable right now. Try later");
        }
    }

    @Override
    public List<SaleOrderProductModel> findProducts(Long id) throws VirtualStoreException{
        SaleOrderModel saleOrder;
        List<SaleOrderProductModel> productsOrder;
        try {
            saleOrder = saleOrderRepository.findOne(id);
            productsOrder = saleOrder.getProductsOrder();
        } catch (HibernateJdbcException e) {
            throw new VirtualStoreException("It doesn't exist products for that order");
        }
        return productsOrder;

    }
}
