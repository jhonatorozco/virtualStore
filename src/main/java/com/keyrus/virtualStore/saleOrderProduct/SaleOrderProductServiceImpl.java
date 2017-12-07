package com.keyrus.virtualStore.saleOrderProduct;

import com.keyrus.virtualStore.exception.VirtualStoreException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateJdbcException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleOrderProductServiceImpl implements ISaleOrderProductService {

    @Autowired
    private ISaleOrderProductRepository saleOrderProductRepository;

    @Override
    public void addSaleOrderProduct(SaleOrderProductModel saleOrderProduct) throws VirtualStoreException {
        try {
            saleOrderProductRepository.save(saleOrderProduct);
        } catch (HibernateJdbcException e) {
            throw new VirtualStoreException("This operation is unavailable right now. Try later");
        }

    }

    @Override
    public SaleOrderProductModel updateSaleOrderProduct(SaleOrderProductIdentity id, SaleOrderProductModel updatedSaleOrderProduct)
            throws VirtualStoreException {

        SaleOrderProductModel saleOrderProduct;
        try{
            saleOrderProduct = saleOrderProductRepository.findOne(id);
            if(saleOrderProduct == null){
                throw new VirtualStoreException("The sale order doesn't exist");
            }
            saleOrderProduct.setSaleOrderProductId(updatedSaleOrderProduct.getSaleOrderProductId());
            saleOrderProduct.setQuantity(updatedSaleOrderProduct.getQuantity());
            saleOrderProduct = saleOrderProductRepository.save(saleOrderProduct);
        }catch (VirtualStoreException e){
            throw new VirtualStoreException("This operation is unavailable right now. Try later");
        }
        return saleOrderProduct;

    }

    @Override
    public SaleOrderProductModel findSaleOrderProduct(SaleOrderProductIdentity id) throws VirtualStoreException {
        SaleOrderProductModel saleOrderProduct;
        try {
            saleOrderProduct = saleOrderProductRepository.findOne(id);
        } catch (HibernateJdbcException e) {
            throw new VirtualStoreException("This operation is unavailable right now. Try later");
        }
        return saleOrderProduct;
    }

    @Override
    public List<SaleOrderProductModel> findAll() throws VirtualStoreException {
        List<SaleOrderProductModel> saleOrderProducts;
        try {
            saleOrderProducts= saleOrderProductRepository.findAll();
        } catch (HibernateJdbcException e) {
            throw new VirtualStoreException("This operation is unavailable right now. Try later");
        }
        return saleOrderProducts;
    }

    @Override
    public void deleteSaleOrderProduct(SaleOrderProductIdentity id) throws VirtualStoreException {
        try {
            saleOrderProductRepository.delete(id);
        } catch (HibernateJdbcException e) {
            throw new VirtualStoreException("This operation is unavailable right now. Try later");
        }

    }
}