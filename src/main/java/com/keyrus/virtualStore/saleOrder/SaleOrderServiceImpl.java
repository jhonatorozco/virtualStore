package com.keyrus.virtualStore.saleOrder;

import com.keyrus.virtualStore.exception.VirtualStoreException;
import com.keyrus.virtualStore.product.ProductModel;
import com.keyrus.virtualStore.saleOrderProduct.ISaleOrderProductRepository;
import com.keyrus.virtualStore.saleOrderProduct.SaleOrderProductIdentity;
import com.keyrus.virtualStore.saleOrderProduct.SaleOrderProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateJdbcException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaleOrderServiceImpl implements  ISaleOrderService{
    @Autowired
    private ISaleOrderRepository saleOrderRepository;

    @Autowired
    private ISaleOrderProductRepository saleOrderProductRepository;

    @Override
    public void addSaleOrder(SaleOrderDTO saleOrder) throws VirtualStoreException {
        try {
            SaleOrderModel saleOrderModel = new SaleOrderModel();
            saleOrderModel.setSaleOrderDate(saleOrder.getSaleOrderDate());
            saleOrderModel.setCustomerOrder(saleOrder.getCustomerOrder());
            saleOrderModel = saleOrderRepository.save(saleOrderModel);
            List<ProductModel> products = saleOrder.getProducts();

            for(ProductModel product : products){

                SaleOrderProductModel saleOrderProduct = new SaleOrderProductModel();
                SaleOrderProductIdentity saleOrderProductId = new SaleOrderProductIdentity();
                saleOrderProductId.setProduct(product);
                saleOrderProductId.setSaleOrder(saleOrderModel);
                saleOrderProduct.setSaleOrderProductId(saleOrderProductId);
                saleOrderProductRepository.save(saleOrderProduct);

            }


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
        SaleOrderModel saleOrderModel;
        SaleOrderDTO saleOrder;

        try {
            saleOrderModel = saleOrderRepository.findOne(id);
            saleOrder = new SaleOrderDTO(saleOrderModel);
            saleOrder.setProducts(findProducts(saleOrder.getId()));
        } catch (HibernateJdbcException e) {
            throw new VirtualStoreException("This operation is unavailable right now. Try later");
        }
        return saleOrderModel;
    }

    @Override
    public List<SaleOrderDTO> findAll() throws VirtualStoreException {
        List<SaleOrderModel> saleOrdersModel;
        List<SaleOrderDTO> saleOrders = new ArrayList<SaleOrderDTO>();

        try {
            saleOrdersModel= saleOrderRepository.findAll();
            for(SaleOrderModel saleOrderModel : saleOrdersModel){
                SaleOrderDTO saleOrder = new SaleOrderDTO(saleOrderModel);
                saleOrder.setProducts(findProducts(saleOrder.getId()));
                saleOrders.add(saleOrder);
            }
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
    public List<ProductModel> findProducts(Long id) throws VirtualStoreException{
        List<ProductModel> products = new ArrayList<>();
        SaleOrderModel saleOrder;
        List<SaleOrderProductModel> saleOrderProducts;
        try {
            saleOrder = saleOrderRepository.findOne(id);
            saleOrderProducts = saleOrder.getProductsOrder();
            if(!saleOrderProducts.isEmpty()){
                for(SaleOrderProductModel saleOrderProduct : saleOrderProducts){
                    products.add(saleOrderProduct.getSaleOrderProductId().getProduct());
                }
            }
        } catch (HibernateJdbcException e) {
            throw new VirtualStoreException("It doesn't exist products for that order");
        }
        return products;

    }
}
