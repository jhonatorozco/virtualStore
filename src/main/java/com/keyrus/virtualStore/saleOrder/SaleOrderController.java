package com.keyrus.virtualStore.saleOrder;

import com.keyrus.virtualStore.exception.VirtualStoreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/saleOrders")
public class SaleOrderController {

    @Autowired
    private ISaleOrderService saleOrderService;


    @PostMapping
    public ResponseEntity<Void> createSaleOrder(@RequestBody SaleOrderModel saleOrder) {

        try{
            saleOrderService.addSaleOrder(saleOrder);
        }catch(VirtualStoreException e){
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SaleOrderModel>> getSaleOrders() {
        List<SaleOrderModel> saleOrders= new ArrayList<>();
        try{
            saleOrders= saleOrderService.findAll();
            return new ResponseEntity<List<SaleOrderModel>>(saleOrders,HttpStatus.OK);
        }catch(VirtualStoreException e){
            return new ResponseEntity<List<SaleOrderModel>>(saleOrders,HttpStatus.CONFLICT);
        }


    }

    @DeleteMapping(value = "/{saleOrderId}")
    public ResponseEntity<Void> deleteSaleOrder(@PathVariable("saleOrderId") Long saleOrderId) {
        try{
            saleOrderService.deleteSaleOrder(saleOrderId);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }catch(VirtualStoreException e){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }



    @GetMapping(value = "/{saleOrderId}")
    public ResponseEntity<SaleOrderModel> getOneSaleOrder(@PathVariable("saleOrderId") Long saleOrderId) {
        SaleOrderModel saleOrder = new SaleOrderModel();
        try{
            saleOrder = saleOrderService.findSaleOrder(saleOrderId);
            return new ResponseEntity<SaleOrderModel>(saleOrder,HttpStatus.OK);
        }catch(VirtualStoreException e){
            return new ResponseEntity<SaleOrderModel>(saleOrder,HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{saleOrderId}")
    public ResponseEntity<SaleOrderModel> updateSaleOrder(@PathVariable("saleOrderId") Long saleOrderId, @RequestBody SaleOrderModel updatedSaleOrder) {
        SaleOrderModel saleOrder = new SaleOrderModel();
        try{
            saleOrder = saleOrderService.updateSaleOrder(saleOrderId,updatedSaleOrder);
            return new ResponseEntity<SaleOrderModel>(saleOrder,HttpStatus.OK);
        }catch(VirtualStoreException e){
            return new ResponseEntity<SaleOrderModel>(saleOrder,HttpStatus.NOT_FOUND);
        }
    }

}
