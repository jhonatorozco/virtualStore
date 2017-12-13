package com.keyrus.virtualStore.order;

import com.keyrus.virtualStore.exception.VirtualStoreException;
import com.keyrus.virtualStore.orderProduct.OrderProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that intercepts the requests related to order resource.
 * @author Jhonatan Orozco
 * @version 1
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private IOrderService orderService;


    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) throws VirtualStoreException {
        OrderDTO order = orderService.addOrder(orderDTO);
        return new ResponseEntity<OrderDTO>(order, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getOrders() throws VirtualStoreException {
        List<OrderDTO> orders = new ArrayList<>();
        orders = orderService.findAll();
        return new ResponseEntity<List<OrderDTO>>(orders, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("orderId") Long orderId) throws VirtualStoreException {
            orderService.deleteOrder(orderId);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }


    @GetMapping(value = "/{orderId}")
    public ResponseEntity<OrderDTO> getOneOrder(@PathVariable("orderId") Long orderId)
            throws VirtualStoreException {
            OrderDTO saleOrder = orderService.findOrder(orderId);
            return new ResponseEntity<OrderDTO>(saleOrder, HttpStatus.OK);
    }

    @PutMapping(value = "/{orderId}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable("orderId") Long orderId, @RequestBody OrderDTO updatedSaleOrder)
            throws VirtualStoreException {
        OrderDTO orderDTO = orderService.updateOrder(orderId, updatedSaleOrder);
        return new ResponseEntity<OrderDTO>(orderDTO,HttpStatus.OK);

    }

    @GetMapping(value = "/{orderId}/products")
    public ResponseEntity<List<OrderProductDTO>> getProductsBelongToOrder(@PathVariable("orderId") Long orderId)
            throws VirtualStoreException {
        List<OrderProductDTO> products = new ArrayList<>();
        products = orderService.findProducts(orderId);
        return new ResponseEntity<List<OrderProductDTO>>(products, HttpStatus.OK);
    }

}
