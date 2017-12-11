package com.keyrus.virtualStore.customer;

import com.keyrus.virtualStore.exception.VirtualStoreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    @Autowired
    private ICustomerService CustomerService;

    @PostMapping
    public ResponseEntity<Void> createCustomer(@RequestBody CustomerModel customer) throws VirtualStoreException {
        CustomerService.addCustomer(customer);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CustomerModel>> getCustomers() throws VirtualStoreException {
        List<CustomerModel> customers = new ArrayList<>();
        customers = CustomerService.findAll();
        return new ResponseEntity<List<CustomerModel>>(customers, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("customerId") Long customerId) throws VirtualStoreException {
        CustomerService.deleteCustomer(customerId);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

    }


    @GetMapping(value = "/{customerId}")
    public ResponseEntity<CustomerModel> getOneCustomer(@PathVariable("customerId") Long customerId) throws VirtualStoreException {
        CustomerModel Customer = new CustomerModel();
        Customer = CustomerService.findCustomer(customerId);
        return new ResponseEntity<CustomerModel>(Customer, HttpStatus.OK);
    }

    @PutMapping(value = "/{customerId}")
    public ResponseEntity<CustomerModel>
    updateCustomer(@PathVariable("customerId") Long customerId, @RequestBody CustomerModel updatedCustomer)
            throws VirtualStoreException {
        CustomerModel Customer;
        Customer = CustomerService.updateCustomer(customerId, updatedCustomer);
        return new ResponseEntity<CustomerModel>(Customer, HttpStatus.OK);

    }

    @GetMapping(value = "/{customerId}/saleOrders")
    public ResponseEntity<CustomerDTO> getOrdersBelongToCustomer(@PathVariable("customerId") Long customerId)
            throws VirtualStoreException {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO = CustomerService.findOrders(customerId);
        return new ResponseEntity<CustomerDTO>(customerDTO, HttpStatus.OK);

    }

}
