package com.keyrus.virtualStore.customer;

import com.keyrus.virtualStore.exception.VirtualStoreException;
import com.keyrus.virtualStore.saleOrder.SaleOrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateJdbcException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public void addCustomer(CustomerModel customer) throws VirtualStoreException {
        try {
            customerRepository.save(customer);
        } catch (HibernateJdbcException e) {
            throw new VirtualStoreException("This operation is unavailable right now. Try later");
        }

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
        }catch (VirtualStoreException e){
            throw new VirtualStoreException("This operation is unavailable right now. Try later");
        }
        return customer;

    }

    @Override
    public CustomerModel findCustomer(Long id) throws VirtualStoreException {
        CustomerModel customer;
        try {
            customer = customerRepository.findOne(id);
        } catch (HibernateJdbcException e) {
            throw new VirtualStoreException("This operation is unavailable right now. Try later");
        }
        return customer;
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
            customerRepository.delete(id);
        } catch (HibernateJdbcException e) {
            throw new VirtualStoreException("This operation is unavailable right now. Try later");
        }

    }

    @Override
    public List<SaleOrderModel> findOrders(Long id) throws VirtualStoreException{
        CustomerModel customer;
        List<SaleOrderModel> orders;
        try {
            customer = customerRepository.findOne(id);
            orders = customer.getOrders();
        } catch (HibernateJdbcException e) {
            throw new VirtualStoreException("The customer doesn't exist");
        }
        return orders;

    }
}