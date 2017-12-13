package com.keyrus.virtualStore.orderProduct;

import com.keyrus.virtualStore.exception.VirtualStoreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that intercepts the requests related to orderProduct resource.
 * @author Jhonatan Orozco
 * @version 1
 */
@RestController
@RequestMapping("/orderProducts")
public class OrderProductController {

    @Autowired
    private OrderProductServiceImpl orderProductService;

    @PostMapping
    public ResponseEntity<OrderProductModel> createOrder(@RequestBody OrderProductModel orderProductModel) throws VirtualStoreException {
        OrderProductModel orderProduct = orderProductService.addOrderProduct(orderProductModel);
        return new ResponseEntity<OrderProductModel>(orderProduct, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OrderProductModel>> getOrderProducts() throws VirtualStoreException {

        List<OrderProductModel> productsOrder = new ArrayList<>();
        productsOrder = orderProductService.findAll();
        return new ResponseEntity<List<OrderProductModel>>(productsOrder, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{orderProductId}")
    public ResponseEntity<Void> deleteOrderProduct(@PathVariable("orderProductId") Long orderProductId) throws VirtualStoreException {
        orderProductService.deleteOrderProduct(orderProductId);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }


    @GetMapping(value = "/{orderProductId}")
    public ResponseEntity<OrderProductModel> getOneOrder(@PathVariable("orderProductId") Long orderProductId)
            throws VirtualStoreException {
        OrderProductModel orderProduct = orderProductService.findOrderProduct(orderProductId);
        return new ResponseEntity<OrderProductModel>(orderProduct, HttpStatus.OK);
    }

    @PutMapping(value = "/{orderProductId}")
    public ResponseEntity<OrderProductModel> updateOrder(@PathVariable("orderProductId") Long orderProductId, @RequestBody OrderProductModel updatedOrderProduct)
            throws VirtualStoreException {
        OrderProductModel orderProductDTO = orderProductService.updateOrderProduct(orderProductId, updatedOrderProduct);
        return new ResponseEntity<OrderProductModel>(orderProductDTO,HttpStatus.OK);

    }
}
