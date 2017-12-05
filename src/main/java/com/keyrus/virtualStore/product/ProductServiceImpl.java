package com.keyrus.virtualStore.product;

import com.keyrus.virtualStore.exception.VirtualStoreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateJdbcException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Override
    public void addProduct(ProductModel product) throws VirtualStoreException {
        try {
            productRepository.save(product);
        } catch (HibernateJdbcException e) {
            throw new VirtualStoreException("Error en la conexión a la base de datos");
        }

    }

    @Override
    public void updateProduct(ProductModel product) throws VirtualStoreException {

    }

    @Override
    public ProductModel findProduct(Long id) throws VirtualStoreException {
        ProductModel product;
        try {
           product = productRepository.findOne(id);
        } catch (HibernateJdbcException e) {
            throw new VirtualStoreException("Error en la conexión a la base de datos");
        }
        return product;
    }

    @Override
    public List<ProductModel> findAll() throws VirtualStoreException {
       List<ProductModel> products;
        try {
            products= productRepository.findAll();
        } catch (HibernateJdbcException e) {
            throw new VirtualStoreException("Error en la conexión a la base de datos");
        }
        return products;
    }

    @Override
    public void deleteProduct(Long id) throws VirtualStoreException {
        try {
            productRepository.delete(id);
        } catch (HibernateJdbcException e) {
            throw new VirtualStoreException("Error en la conexión a la base de datos");
        }

    }
}
