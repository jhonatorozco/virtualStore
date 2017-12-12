package com.keyrus.virtualStore.customer;

import com.keyrus.virtualStore.exception.VirtualStoreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that intercepts the requests related to customer resource.
 * @author Jhonatan Orozco
 * @version 1
 */
@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @PostMapping
    public ResponseEntity<Void> createCustomer(@RequestBody CustomerModel customer) throws VirtualStoreException {
        customerService.addCustomer(customer);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CustomerModel>> getCustomers() throws VirtualStoreException {
        List<CustomerModel> customers = new ArrayList<>();
        customers = customerService.findAll();
        return new ResponseEntity<List<CustomerModel>>(customers, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("customerId") Long customerId) throws VirtualStoreException {
        customerService.deleteCustomer(customerId);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

    }


    @GetMapping(value = "/{customerId}")
    public ResponseEntity<CustomerDTO> getOneCustomer(@PathVariable("customerId") Long customerId) throws VirtualStoreException {
        CustomerDTO customer;
        customer = customerService.findCustomer(customerId);
        return new ResponseEntity<CustomerDTO>(customer, HttpStatus.OK);
    }

    @PutMapping(value = "/{customerId}")
    public ResponseEntity<CustomerModel>
    updateCustomer(@PathVariable("customerId") Long customerId, @RequestBody CustomerModel updatedCustomer)
            throws VirtualStoreException {
        CustomerModel customer;
        customer = customerService.updateCustomer(customerId, updatedCustomer);
        return new ResponseEntity<CustomerModel>(customer, HttpStatus.OK);

    }

    @GetMapping(value = "/{customerId}/saleOrders")
    public ResponseEntity<CustomerDTO> getOrdersBelongToCustomer(@PathVariable("customerId") Long customerId)
            throws VirtualStoreException {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO = customerService.findOrders(customerId);
        return new ResponseEntity<CustomerDTO>(customerDTO, HttpStatus.OK);

    }

}
