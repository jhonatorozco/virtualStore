package com.keyrus.virtualStore.saleOrder;

import com.keyrus.virtualStore.exception.VirtualStoreException;
import com.keyrus.virtualStore.saleOrderProduct.SaleOrderProductDTO;
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
    public ResponseEntity<Void> createSaleOrder(@RequestBody SaleOrderDTO saleOrder) throws VirtualStoreException {
        saleOrderService.addSaleOrder(saleOrder);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SaleOrderDTO>> getSaleOrders() throws VirtualStoreException {
        List<SaleOrderDTO> saleOrders = new ArrayList<>();
        saleOrders = saleOrderService.findAll();
        return new ResponseEntity<List<SaleOrderDTO>>(saleOrders, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{saleOrderId}")
    public ResponseEntity<Void> deleteSaleOrder(@PathVariable("saleOrderId") Long saleOrderId) throws VirtualStoreException {
            saleOrderService.deleteSaleOrder(saleOrderId);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }


    @GetMapping(value = "/{saleOrderId}")
    public ResponseEntity<SaleOrderDTO> getOneSaleOrder(@PathVariable("saleOrderId") Long saleOrderId)
            throws VirtualStoreException {
            SaleOrderDTO saleOrder = saleOrderService.findSaleOrder(saleOrderId);
            return new ResponseEntity<SaleOrderDTO>(saleOrder, HttpStatus.OK);
    }

    @PutMapping(value = "/{saleOrderId}")
    public ResponseEntity<Void> updateSaleOrder(@PathVariable("saleOrderId") Long saleOrderId, @RequestBody SaleOrderDTO updatedSaleOrder)
            throws VirtualStoreException {
        saleOrderService.updateSaleOrder(saleOrderId, updatedSaleOrder);
        return new ResponseEntity<Void>(HttpStatus.OK);

    }

    @GetMapping(value = "/{saleOrderId}/products")
    public ResponseEntity<List<SaleOrderProductDTO>> getProductsBelongToOrder(@PathVariable("saleOrderId") Long saleOrderId)
            throws VirtualStoreException {

        List<SaleOrderProductDTO> products = new ArrayList<>();
        products = saleOrderService.findProducts(saleOrderId);
        return new ResponseEntity<List<SaleOrderProductDTO>>(products, HttpStatus.OK);
    }

}
