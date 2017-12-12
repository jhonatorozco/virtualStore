package com.keyrus.virtualStore.saleOrderProduct;

import com.keyrus.virtualStore.exception.VirtualStoreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that intercepts the requests related to saleOrderProduct resource.
 * @author Jhonatan Orozco
 * @version 1
 */
@RestController
@RequestMapping("/saleOrderProducts")
public class SaleOrderProductController {

    @Autowired
    private SaleOrderProductServiceImpl saleOrderProductService;

    @GetMapping
    public ResponseEntity<List<SaleOrderProductModel>> getSaleOrderProducts() throws VirtualStoreException {

        List<SaleOrderProductModel> productsOrder = new ArrayList<>();
        productsOrder = saleOrderProductService.findAll();
        return new ResponseEntity<List<SaleOrderProductModel>>(productsOrder, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{saleOrderProductId}")
    public ResponseEntity<Void> deleteSaleOrder(@PathVariable("saleOrderProductId") Long saleOrderProductId) throws VirtualStoreException {
        saleOrderProductService.deleteSaleOrderProduct(saleOrderProductId);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
