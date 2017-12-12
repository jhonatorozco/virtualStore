package com.keyrus.virtualStore.saleOrder;

import com.keyrus.virtualStore.customer.CustomerModel;
import com.keyrus.virtualStore.customer.ICustomerRepository;
import com.keyrus.virtualStore.exception.VirtualStoreException;
import com.keyrus.virtualStore.product.IProductService;
import com.keyrus.virtualStore.product.ProductModel;
import com.keyrus.virtualStore.saleOrderProduct.ISaleOrderProductRepository;
import com.keyrus.virtualStore.saleOrderProduct.ISaleOrderProductService;
import com.keyrus.virtualStore.saleOrderProduct.SaleOrderProductDTO;
import com.keyrus.virtualStore.saleOrderProduct.SaleOrderProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateJdbcException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class SaleOrderServiceImpl implements  ISaleOrderService{
    @Autowired
    private ISaleOrderRepository saleOrderRepository;

    @Autowired
    private ISaleOrderProductService saleOrderProductService;

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
            saleOrderModel.setCustomerOrder(customer);
            saleOrderModel = saleOrderRepository.save(saleOrderModel);
            List<SaleOrderProductDTO> products = saleOrder.getProducts();
            saleOrderModel = updateProducts(saleOrderModel.getId(),products);

            saleOrderRepository.save(saleOrderModel);
        } catch (HibernateJdbcException e) {
            throw new VirtualStoreException("This operation is unavailable right now. Try later");
        }

    }


    @Override
    public SaleOrderDTO findSaleOrder(Long id) throws VirtualStoreException {
        SaleOrderModel saleOrderModel;
        SaleOrderDTO saleOrder;

        try {
            saleOrderModel = saleOrderRepository.findOne(id);
            if(saleOrderModel == null){
                throw new VirtualStoreException("The sale order doesn't exist");
            }
            saleOrder = new SaleOrderDTO(saleOrderModel);
            saleOrder.setProducts(findProducts(saleOrder.getId()));
        } catch (HibernateJdbcException e) {
            throw new VirtualStoreException("This operation is unavailable right now. Try later");
        }
        return saleOrder;
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

    @Override
    public SaleOrderDTO updateSaleOrder(Long id, SaleOrderDTO updatedSaleOrder) throws VirtualStoreException{
        SaleOrderDTO saleOrderDTO;
        try {
            SaleOrderModel saleOrderModel = saleOrderRepository.findOne(id);
            if(saleOrderModel == null){
                throw new VirtualStoreException("The sale order doesn't exist");
            }
            saleOrderModel.setSaleOrderDate(updatedSaleOrder.getSaleOrderDate());
            String email = updatedSaleOrder.getCustomerOrder().getEmail();
            CustomerModel customer = customerRepository.findByEmail(email);
            if(customer == null){
                throw new VirtualStoreException("The customer with this email doesn't exist");
            }
            saleOrderModel.setCustomerOrder(customer);
            List<SaleOrderProductDTO> orderProducts = updatedSaleOrder.getProducts();
            saleOrderModel = updateProducts(saleOrderModel.getId(),orderProducts);
            saleOrderModel = saleOrderRepository.save(saleOrderModel);
            saleOrderDTO = new SaleOrderDTO(saleOrderModel);
            saleOrderDTO.setProducts(findProducts(saleOrderModel.getId()));
        } catch (HibernateJdbcException e) {
            throw new VirtualStoreException("This operation is unavailable right now. Try later");
        }
        return saleOrderDTO;
    }

    @Override
    public SaleOrderModel updateProducts(Long id, List<SaleOrderProductDTO> saleOrderProducts) throws VirtualStoreException{
        SaleOrderModel saleOrderModel = saleOrderRepository.findOne(id);
        if(saleOrderModel == null){
            throw new VirtualStoreException("The sale order doesn't exist");
        }
        int newAvailableQuantity;
        float totalPrice = 0;
        List<SaleOrderProductModel> currentOrders =  saleOrderModel.getProductsOrder();
        if(currentOrders != null ){
            for (SaleOrderProductModel saleOrderProductModel : currentOrders){
                saleOrderProductService.deleteSaleOrderProduct(saleOrderProductModel.getId());
            }
            currentOrders.clear();
        }else{
            currentOrders = new ArrayList<>();
        }
        for(SaleOrderProductDTO orderProductDTO : saleOrderProducts){
            Long productId = orderProductDTO.getProduct().getId();
            ProductModel product = productService.findProduct(productId);
            SaleOrderProductModel saleOrderProduct = new SaleOrderProductModel();
            saleOrderProduct.setSaleOrder(saleOrderModel);
            saleOrderProduct.setProduct(product);
            saleOrderProduct.setQuantity(orderProductDTO.getQuantity());
            saleOrderProductService.addSaleOrderProduct(saleOrderProduct);
            currentOrders.add(saleOrderProduct);
            //Update the available quantity
            newAvailableQuantity = product.getAvailableQuantity() - orderProductDTO.getQuantity();
            if(newAvailableQuantity < 0 ){
                throw new VirtualStoreException("The product doesn't have the requested quantity");
            }
            product.setAvailableQuantity(newAvailableQuantity);
            product = productService.updateProduct(product.getId(),product);
            totalPrice = totalPrice + orderProductDTO.getQuantity() * product.getPrice();
        }
        saleOrderModel.setProductsOrder(currentOrders);
        saleOrderModel.setTotalPrice(totalPrice);
        return saleOrderModel;
    }
}
