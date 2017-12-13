package com.keyrus.virtualStore.order;

import com.keyrus.virtualStore.customer.CustomerModel;
import com.keyrus.virtualStore.customer.ICustomerRepository;
import com.keyrus.virtualStore.exception.VirtualStoreException;
import com.keyrus.virtualStore.product.IProductService;
import com.keyrus.virtualStore.product.ProductModel;
import com.keyrus.virtualStore.orderProduct.IOrderProductService;
import com.keyrus.virtualStore.orderProduct.OrderProductModel;
import com.keyrus.virtualStore.orderProduct.OrderProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateJdbcException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that implements IOrderService
 * @author Jhonatan Orozco
 * @version 1
 */

@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private IOrderProductService orderProductService;


    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private IProductService productService;


    @Override
    @Transactional
    public OrderDTO addOrder(OrderDTO orderDTO) throws VirtualStoreException {
        OrderDTO order;
        try {
            OrderModel orderModel = new OrderModel();
            orderModel.setOrderDate(orderDTO.getOrderDate());
            String email = orderDTO.getCustomerOrder().getEmail();
            CustomerModel customer = customerRepository.findByEmail(email);
            if(customer == null){
                throw new VirtualStoreException("The customer with this email doesn't exist");
            }
            orderModel.setCustomerOrder(customer);
            orderModel = orderRepository.save(orderModel);
            List<OrderProductDTO> products = orderDTO.getProducts();
            orderModel = updateProducts(orderModel.getId(),products);
            //update the order with the related products
            orderModel = orderRepository.save(orderModel);
            order = new OrderDTO(orderModel);
            order.setProducts(findProducts(orderModel.getId()));
        } catch (HibernateJdbcException e) {
            throw new VirtualStoreException("This operation is unavailable right now. Try later");
        }
        return order;

    }


    @Override
    public OrderDTO findOrder(Long id) throws VirtualStoreException {
        OrderModel orderModel;
        OrderDTO orderDTO;

        try {
            orderModel = orderRepository.findOne(id);
            if(orderModel == null){
                throw new VirtualStoreException("The order doesn't exist");
            }
            orderDTO = new OrderDTO(orderModel);
            orderDTO.setProducts(findProducts(orderDTO.getId()));
        } catch (HibernateJdbcException e) {
            throw new VirtualStoreException("This operation is unavailable right now. Try later");
        }
        return orderDTO;
    }

    @Override
    public List<OrderDTO> findAll() throws VirtualStoreException {
        List<OrderModel> orderModels;
        List<OrderDTO> orders = new ArrayList<OrderDTO>();

        try {
            orderModels= orderRepository.findAll();
            for(OrderModel orderModel : orderModels){
                OrderDTO order = new OrderDTO(orderModel);
                order.setProducts(findProducts(order.getId()));
                orders.add(order);
            }
        } catch (HibernateJdbcException e) {
            throw new VirtualStoreException("This operation is unavailable right now. Try later");
        }
        return orders;
    }

    @Override
    public void deleteOrder(Long id) throws VirtualStoreException {
        try {
            OrderModel orderModel = orderRepository.findOne(id);
            if(orderModel == null){
                throw new VirtualStoreException("The order doesn't exist");
            }
            orderRepository.delete(id);
        } catch (HibernateJdbcException e) {
            throw new VirtualStoreException("This operation is unavailable right now. Try later");
        }
    }

    @Override
    public List<OrderProductDTO> findProducts(Long id) throws VirtualStoreException{
        List<OrderProductDTO> products = new ArrayList<>();
        OrderModel orderModel;
        List<OrderProductModel> orderProducts;
        try {
            orderModel = orderRepository.findOne(id);
            orderProducts = orderModel.getProductsOrder();
            if(!orderProducts.isEmpty()){
                for(OrderProductModel orderProduct : orderProducts){
                    OrderProductDTO orderProductDTO = new OrderProductDTO(orderProduct);
                    products.add(orderProductDTO);
                }
            }
        } catch (HibernateJdbcException e) {
            throw new VirtualStoreException("This operation is unavailable right now. Try later");
        }
        return products;

    }

    @Override
    @Transactional
    public OrderDTO updateOrder(Long id, OrderDTO updatedOrder) throws VirtualStoreException{
        OrderDTO orderDTO;
        try {
            OrderModel orderModel = orderRepository.findOne(id);
            if(orderModel == null){
                throw new VirtualStoreException("The order doesn't exist");
            }
            orderModel.setOrderDate(updatedOrder.getOrderDate());
            String email = updatedOrder.getCustomerOrder().getEmail();
            CustomerModel customer = customerRepository.findByEmail(email);
            if(customer == null){
                throw new VirtualStoreException("The customer with this email doesn't exist");
            }
            orderModel.setCustomerOrder(customer);
            List<OrderProductDTO> orderProducts = updatedOrder.getProducts();
            orderModel = updateProducts(orderModel.getId(),orderProducts);
            orderModel = orderRepository.save(orderModel);
            orderDTO = new OrderDTO(orderModel);
            orderDTO.setProducts(findProducts(orderModel.getId()));
        } catch (HibernateJdbcException e) {
            throw new VirtualStoreException("This operation is unavailable right now. Try later");
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderModel updateProducts(Long id, List<OrderProductDTO> orderProducts) throws VirtualStoreException{
        OrderModel orderModel = orderRepository.findOne(id);
        if(orderModel == null){
            throw new VirtualStoreException("The order doesn't exist");
        }
        int newAvailableQuantity;
        float totalPrice = 0;
        List<OrderProductModel> currentOrders =  orderModel.getProductsOrder();
        if(currentOrders != null ){
            for (OrderProductModel orderProductModel : currentOrders){
                //update the available quantity, adding the requested quantity for the deleted order
                ProductModel product = orderProductModel.getProduct();
                int requestedQuantity = orderProductModel.getQuantity();
                int availableQuantity = product.getAvailableQuantity() + requestedQuantity;
                product.setAvailableQuantity(availableQuantity);
                productService.updateProduct(product.getId(),product);
                orderProductService.deleteOrderProduct(orderProductModel.getId());
            }
            currentOrders.clear();
        }else{
            currentOrders = new ArrayList<>();
        }
        for(OrderProductDTO orderProductDTO : orderProducts){
            Long productId = orderProductDTO.getProduct().getId();
            ProductModel product = productService.findProduct(productId);
            OrderProductModel orderProduct = new OrderProductModel();
            orderProduct.setSaleOrder(orderModel);
            orderProduct.setProduct(product);
            orderProduct.setQuantity(orderProductDTO.getQuantity());
            orderProductService.addOrderProduct(orderProduct);
            currentOrders.add(orderProduct);
            //Update the available quantity
            newAvailableQuantity = product.getAvailableQuantity() - orderProductDTO.getQuantity();
            if(newAvailableQuantity < 0 ){
                throw new VirtualStoreException("The product doesn't have the requested quantity");
            }
            product.setAvailableQuantity(newAvailableQuantity);
            product = productService.updateProduct(product.getId(),product);
            totalPrice = totalPrice + orderProductDTO.getQuantity() * product.getPrice();
        }
        orderModel.setProductsOrder(currentOrders);
        orderModel.setTotalPrice(totalPrice);
        return orderModel;
    }
}
