package com.keyrus.virtualStore.customer;

import com.keyrus.virtualStore.exception.VirtualStoreException;
import com.keyrus.virtualStore.saleOrder.SaleOrderModel;
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
    public ResponseEntity<Void> createCustomer(@RequestBody CustomerModel customer){

        try{
            CustomerService.addCustomer(customer);
        }catch(VirtualStoreException e){
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CustomerModel>> getCustomers() {
        List<CustomerModel> customers= new ArrayList<>();
        try{
            customers= CustomerService.findAll();
            return new ResponseEntity<List<CustomerModel>>(customers,HttpStatus.OK);
        }catch(VirtualStoreException e){
            return new ResponseEntity<List<CustomerModel>>(customers,HttpStatus.CONFLICT);
        }


    }

    @DeleteMapping(value = "/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("customerId") Long customerId) {
        try{
            CustomerService.deleteCustomer(customerId);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }catch(VirtualStoreException e){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }



    @GetMapping(value = "/{customerId}")
    public ResponseEntity<CustomerModel> getOneCustomer(@PathVariable("customerId") Long customerId) {
        CustomerModel Customer = new CustomerModel();
        try{
            Customer = CustomerService.findCustomer(customerId);
            return new ResponseEntity<CustomerModel>(Customer,HttpStatus.OK);
        }catch(VirtualStoreException e){
            return new ResponseEntity<CustomerModel>(Customer,HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{customerId}")
    public ResponseEntity<CustomerModel> updateCustomer(@PathVariable("customerId") Long customerId, @RequestBody CustomerModel updatedCustomer) {
        CustomerModel Customer = new CustomerModel();
        try{
            Customer = CustomerService.updateCustomer(customerId,updatedCustomer);
            return new ResponseEntity<CustomerModel>(Customer,HttpStatus.OK);
        }catch(VirtualStoreException e){
            return new ResponseEntity<CustomerModel>(Customer,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{customerId}/saleOrders")
    public ResponseEntity<CustomerDTO>getOrdersBelongToCustomer(@PathVariable("customerId") Long customerId) {
        CustomerDTO customerDTO = new CustomerDTO();
        try{
            customerDTO= CustomerService.findOrders(customerId);
            return new ResponseEntity<CustomerDTO>(customerDTO,HttpStatus.OK);
        }catch(VirtualStoreException e){
            return new ResponseEntity<CustomerDTO>(customerDTO,HttpStatus.CONFLICT);
        }

    }

}
