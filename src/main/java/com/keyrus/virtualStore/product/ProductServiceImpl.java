package com.keyrus.virtualStore.product;

import com.keyrus.virtualStore.exception.VirtualStoreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateJdbcException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Class that implements IProductService
 * @author Jhonatan Orozco
 * @version 1
 */

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Override
    public ProductModel addProduct(ProductModel productModel) throws VirtualStoreException {
        ProductModel product;
        try {
            product = productRepository.save(productModel);
        } catch (HibernateJdbcException e) {
            throw new VirtualStoreException("This operation is unavailable right now. Try later");
        }
        return product;

    }

    @Override
    public ProductModel updateProduct(Long id, ProductModel updatedProduct) throws VirtualStoreException {
        ProductModel product;
        try{
            product = productRepository.findOne(id);
            if(product == null){
                throw new VirtualStoreException("The product doesn't exist");
            }
            product.setName(updatedProduct.getName());
            product.setDescription(updatedProduct.getDescription());
            product.setPrice(updatedProduct.getPrice());
            product.setAvailableQuantity(updatedProduct.getAvailableQuantity());
            product = productRepository.save(product);
        }catch (VirtualStoreException e){
            throw new VirtualStoreException("This operation is unavailable right now. Try later");
        }
        return product;

    }

    @Override
    public ProductModel findProduct(Long id) throws VirtualStoreException {
        ProductModel product;
        try {
           product = productRepository.findOne(id);
           if(product == null){
               throw new VirtualStoreException("The product doesn't exist");
           }
        } catch (HibernateJdbcException e) {
            throw new VirtualStoreException("This operation is unavailable right now. Try later");
        }
        return product;
    }

    @Override
    public List<ProductModel> findAll() throws VirtualStoreException {
       List<ProductModel> products;
        try {
            products= productRepository.findAll();
        } catch (HibernateJdbcException e) {
            throw new VirtualStoreException("This operation is unavailable right now. Try later");
        }
        return products;
    }

    @Override
    public void deleteProduct(Long id) throws VirtualStoreException {
        try {
            ProductModel product = productRepository.findOne(id);
            if(product == null){
                throw new VirtualStoreException("The product doesn't exist");
            }
            productRepository.delete(id);
        } catch (HibernateJdbcException e) {
            throw new VirtualStoreException("This operation is unavailable right now. Try later");
        }

    }
}
