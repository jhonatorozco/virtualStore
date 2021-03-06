package com.keyrus.virtualStore.product;

import com.keyrus.virtualStore.exception.VirtualStoreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Class that intercepts the requests related to product resource.
 * @author Jhonatan Orozco
 * @version 1
 */
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;


    @PostMapping
    public ResponseEntity<ProductModel> createProduct(@RequestBody ProductModel productModel) throws VirtualStoreException {

        ProductModel product = productService.addProduct(productModel);
        return new ResponseEntity<ProductModel>(product, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductModel>> getProducts() throws VirtualStoreException {
        List<ProductModel> products;
        products = productService.findAll();
        return new ResponseEntity<List<ProductModel>>(products, HttpStatus.OK);


    }

    @DeleteMapping(value = "/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("productId") Long productId) throws VirtualStoreException {

        productService.deleteProduct(productId);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }


    @GetMapping(value = "/{productId}")
    public ResponseEntity<ProductModel> getOneProduct(@PathVariable("productId") Long productId) throws VirtualStoreException {
        ProductModel product;

        product = productService.findProduct(productId);
        return new ResponseEntity<ProductModel>(product, HttpStatus.OK);

    }

    @PutMapping(value = "/{productId}")
    public ResponseEntity<ProductModel> updateProduct(@PathVariable("productId") Long productId, @RequestBody ProductModel updatedProduct)
            throws VirtualStoreException {

        ProductModel product ;
        product = productService.updateProduct(productId, updatedProduct);
        return new ResponseEntity<ProductModel>(product, HttpStatus.OK);
    }


}
