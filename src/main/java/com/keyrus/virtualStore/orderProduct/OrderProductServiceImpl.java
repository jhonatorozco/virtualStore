package com.keyrus.virtualStore.orderProduct;

import com.keyrus.virtualStore.exception.VirtualStoreException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateJdbcException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * /**
 * Class that implements IOrderProductService
 * @author Jhonatan Orozco
 * @version 1
 */

@Service
public class OrderProductServiceImpl implements IOrderProductService {

    @Autowired
    private IOrderProductRepository orderProductRepository;

    @Override
    public OrderProductModel addOrderProduct(OrderProductModel orderProductModel) throws VirtualStoreException {
        OrderProductModel orderProduct;
        try {
            orderProduct = orderProductRepository.save(orderProductModel);
        } catch (HibernateJdbcException e) {
            throw new VirtualStoreException("This operation is unavailable right now. Try later");
        }
        return orderProduct;

    }

    @Override
    public OrderProductModel updateOrderProduct(Long id, OrderProductModel updatedOrderProduct)
            throws VirtualStoreException {

        OrderProductModel saleOrderProduct;
        try{
            saleOrderProduct = orderProductRepository.findOne(id);
            if(saleOrderProduct == null){
                throw new VirtualStoreException("The sale order doesn't exist");
            }
            saleOrderProduct.setProduct(updatedOrderProduct.getProduct());
            saleOrderProduct.setSaleOrder(updatedOrderProduct.getSaleOrder());
            saleOrderProduct.setQuantity(updatedOrderProduct.getQuantity());
            saleOrderProduct = orderProductRepository.save(saleOrderProduct);
        }catch (VirtualStoreException e){
            throw new VirtualStoreException("This operation is unavailable right now. Try later");
        }
        return saleOrderProduct;

    }

    @Override
    public OrderProductModel findOrderProduct(Long id) throws VirtualStoreException {
        OrderProductModel saleOrderProduct;
        try {
            saleOrderProduct = orderProductRepository.findOne(id);
            if(saleOrderProduct == null){
                throw new VirtualStoreException("The sale order product doesn't exist");
            }
        } catch (HibernateJdbcException e) {
            throw new VirtualStoreException("This operation is unavailable right now. Try later");
        }
        return saleOrderProduct;
    }

    @Override
    public List<OrderProductModel> findAll() throws VirtualStoreException {
        List<OrderProductModel> saleOrderProducts;
        try {
            saleOrderProducts= orderProductRepository.findAll();
        } catch (HibernateJdbcException e) {
            throw new VirtualStoreException("This operation is unavailable right now. Try later");
        }
        return saleOrderProducts;
    }

    @Override
    public void deleteOrderProduct(Long id) throws VirtualStoreException {
        try {
            OrderProductModel saleOrderProduct = orderProductRepository.findOne(id);
            if(saleOrderProduct == null){
                throw new VirtualStoreException("The sale order doesn't exist");
            }
            System.out.println("el id es : " + id);
           orderProductRepository.delete(saleOrderProduct);
        } catch (HibernateJdbcException e) {
            throw new VirtualStoreException("This operation is unavailable right now. Try later");
        }

    }

    @Override
    @Transactional
    public void updateOrderProducts(List<OrderProductModel> orderProducts) throws VirtualStoreException {
        for(OrderProductModel orderProduct : orderProducts){
            updateOrderProduct(orderProduct.getId(),orderProduct);
        }

    }
}
