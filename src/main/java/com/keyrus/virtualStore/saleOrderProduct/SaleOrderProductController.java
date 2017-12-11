package com.keyrus.virtualStore.saleOrderProduct;

import com.keyrus.virtualStore.exception.VirtualStoreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
}
