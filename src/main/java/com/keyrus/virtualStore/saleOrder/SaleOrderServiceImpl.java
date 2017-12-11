package com.keyrus.virtualStore.saleOrder;

import com.keyrus.virtualStore.customer.CustomerModel;
import com.keyrus.virtualStore.customer.ICustomerRepository;
import com.keyrus.virtualStore.exception.VirtualStoreException;
import com.keyrus.virtualStore.product.IProductService;
import com.keyrus.virtualStore.product.ProductModel;
import com.keyrus.virtualStore.saleOrderProduct.ISaleOrderProductRepository;
import com.keyrus.virtualStore.saleOrderProduct.SaleOrderProductDTO;
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

    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private IProductService productService;

    @Override
    public void addSaleOrder(SaleOrderDTO saleOrder) throws VirtualStoreException {

        try {
            SaleOrderModel saleOrderModel = new SaleOrderModel();
            saleOrderModel.setSaleOrderDate(saleOrder.getSaleOrderDate());
            String email = saleOrder.getCustomerOrder().getEmail();
            CustomerModel customer = customerRepository.findByEmail(email);
            if(customer == null){
                throw new VirtualStoreException("The customer with this email doesn't exist");
            }
            int newAvailableQuantity;
            saleOrderModel.setCustomerOrder(customer);
            saleOrderModel = saleOrderRepository.save(saleOrderModel);
            List<SaleOrderProductDTO> products = saleOrder.getProducts();
            float totalPrice = 0;

            for(SaleOrderProductDTO orderProductDTO : products){
                Long productId = orderProductDTO.getProduct().getId();
                ProductModel product = productService.findProduct(productId);

                //Update the available quantity
                newAvailableQuantity = product.getAvailableQuantity() - orderProductDTO.getQuantity();
                if(newAvailableQuantity < 0 ){
                    throw new VirtualStoreException("The product doesn't have the requested quantity");
                }
                product.setAvailableQuantity(newAvailableQuantity);
                product = productService.updateProduct(product.getId(),product);

                SaleOrderProductModel saleOrderProduct = new SaleOrderProductModel();
                saleOrderProduct.setProduct(product);
                saleOrderProduct.setSaleOrder(saleOrderModel);
                saleOrderProduct.setQuantity(orderProductDTO.getQuantity());
                saleOrderProductRepository.save(saleOrderProduct);
                totalPrice = totalPrice + orderProductDTO.getQuantity() * product.getPrice();

            }
            saleOrderModel.setTotalPrice(totalPrice);
            updateSaleOrder(saleOrderModel.getId(),saleOrderModel);


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
            SaleOrderModel saleOrder = saleOrderRepository.findOne(id);
            if(saleOrder == null){
                throw new VirtualStoreException("The sale order doesn't exist");
            }
            saleOrderRepository.delete(id);
        } catch (HibernateJdbcException e) {
            throw new VirtualStoreException("This operation is unavailable right now. Try later");
        }
    }

    @Override
    public List<SaleOrderProductDTO> findProducts(Long id) throws VirtualStoreException{
        List<SaleOrderProductDTO> products = new ArrayList<>();
        SaleOrderModel saleOrder;
        List<SaleOrderProductModel> saleOrderProducts;
        try {
            saleOrder = saleOrderRepository.findOne(id);
            saleOrderProducts = saleOrder.getProductsOrder();
            if(!saleOrderProducts.isEmpty()){
                for(SaleOrderProductModel saleOrderProduct : saleOrderProducts){
                    SaleOrderProductDTO saleOrderProductDTO = new SaleOrderProductDTO(saleOrderProduct);
                    products.add(saleOrderProductDTO);
                }
            }
        } catch (HibernateJdbcException e) {
            throw new VirtualStoreException("It doesn't exist products for that order");
        }
        return products;

    }
}
