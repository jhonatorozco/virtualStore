package com.keyrus.virtualStore.customer;

import com.keyrus.virtualStore.exception.VirtualStoreException;
import com.keyrus.virtualStore.saleOrder.ISaleOrderService;
import com.keyrus.virtualStore.saleOrder.SaleOrderDTO;
import com.keyrus.virtualStore.saleOrder.SaleOrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateJdbcException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private ISaleOrderService saleOrderService;

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
        }catch (HibernateJdbcException e){
            throw new VirtualStoreException("This operation is unavailable right now. Try later");
        }
        return customer;

    }

    @Override
    public CustomerDTO findCustomer(Long id) throws VirtualStoreException {
        CustomerModel customerModel;
        CustomerDTO customerDTO;
        try {
            customerModel = customerRepository.findOne(id);
            customerDTO = new CustomerDTO(customerModel);
        } catch (HibernateJdbcException e) {
            throw new VirtualStoreException("This operation is unavailable right now. Try later");
        }
        return customerDTO;
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
        List<SaleOrderModel> saleOrdersModel;
        List<SaleOrderDTO> saleOrdersDTO = new ArrayList<>();
        CustomerDTO customerDTO;
        try {
            customer = customerRepository.findOne(id);
            if(customer == null){
                throw  new VirtualStoreException("The customer doesn't exist");
            }
           saleOrdersModel = customer.getOrders();
            if(saleOrdersModel != null){
                for(SaleOrderModel saleOrderModel: saleOrdersModel){
                    SaleOrderDTO saleOrderDTO = new SaleOrderDTO(saleOrderModel);
                    saleOrderDTO.setProducts(saleOrderService.findProducts(saleOrderModel.getId()));
                    saleOrdersDTO.add(saleOrderDTO);
                }
            }
            customerDTO = new CustomerDTO(customer);
            customerDTO.setOrders(saleOrdersDTO);


        } catch (HibernateJdbcException e) {
            throw new VirtualStoreException("The customer doesn't exist");
        }
        return customerDTO;

    }
}
