package com.keyrus.virtualStore.customer;

import com.keyrus.virtualStore.exception.VirtualStoreException;
import com.keyrus.virtualStore.order.IOrderService;
import com.keyrus.virtualStore.order.OrderModel;
import com.keyrus.virtualStore.order.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateJdbcException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * Class that implements ICustomerService
 * @author Jhonatan Orozco
 * @version 1
 */

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private IOrderService saleOrderService;

    @Override
    public CustomerModel addCustomer(CustomerModel customer) throws VirtualStoreException {
        CustomerModel customerModel;
        try {
            customerModel =customerRepository.save(customer);
        } catch (HibernateJdbcException e) {
            throw new VirtualStoreException("This operation is unavailable right now. Try later");
        }
        return customerModel;

    }

    @Override
    public CustomerModel updateCustomer(Long id, CustomerModel updatedCustomer) throws VirtualStoreException {
        CustomerModel customer;
        try{
            customer = customerRepository.findOne(id);
            if(customer == null){
                throw new VirtualStoreException("The customer doesn't exist");
            }
            customer.setName(updatedCustomer.getName());
            customer.setEmail(updatedCustomer.getEmail());
            customer.setPassword(updatedCustomer.getPassword());
            customer.setAddress(updatedCustomer.getAddress());
            customer.setOrders(updatedCustomer.getOrders());
            customer = customerRepository.save(customer);
        }catch (HibernateJdbcException e){
            throw new VirtualStoreException("This operation is unavailable right now. Try later");
        }
        return customer;

    }

    @Override
    public CustomerModel findCustomer(Long id) throws VirtualStoreException {
        CustomerModel customerModel;
        try {
            customerModel = customerRepository.findOne(id);
            if(customerModel == null){
                throw new VirtualStoreException("The client doesn't exist");
            }
        } catch (HibernateJdbcException e) {
            throw new VirtualStoreException("This operation is unavailable right now. Try later");
        }
        return customerModel;
    }

    @Override
    public List<CustomerModel> findAll() throws VirtualStoreException {
        List<CustomerModel> customers;
        try {
            customers= customerRepository.findAll();
        } catch (HibernateJdbcException e) {
            throw new VirtualStoreException("This operation is unavailable right now. Try later");
        }
        return customers;
    }

    @Override
    public void deleteCustomer(Long id) throws VirtualStoreException {
        try {
            CustomerModel customer = customerRepository.findOne(id);
            if(customer == null){
                throw new VirtualStoreException("The customer doesn't exist");
            }
            customerRepository.delete(id);
        } catch (HibernateJdbcException e) {
            throw new VirtualStoreException("This operation is unavailable right now. Try later");
        }

    }

    @Override
    public CustomerDTO findOrders(Long id) throws VirtualStoreException{
        CustomerModel customer;
        List<OrderModel> saleOrdersModel;
        List<OrderDTO> saleOrdersDTO = new ArrayList<>();
        CustomerDTO customerDTO;
        try {
            customer = customerRepository.findOne(id);
            if(customer == null){
                throw  new VirtualStoreException("The customer doesn't exist");
            }
           saleOrdersModel = customer.getOrders();
            if(saleOrdersModel != null){
                for(OrderModel orderModel : saleOrdersModel){
                    OrderDTO orderDTO = new OrderDTO(orderModel);
                    orderDTO.setProducts(saleOrderService.findProducts(orderModel.getId()));
                    saleOrdersDTO.add(orderDTO);
                }
            }
            customerDTO = new CustomerDTO(customer);
            customerDTO.setOrders(saleOrdersDTO);


        } catch (HibernateJdbcException e) {
            throw new VirtualStoreException("This operation is unavailable right now. Try later");
        }
        return customerDTO;

    }
}
