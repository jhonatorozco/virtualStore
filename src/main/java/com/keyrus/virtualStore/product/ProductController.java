package com.keyrus.virtualStore.product;

import com.keyrus.virtualStore.exception.VirtualStoreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductService productService;


    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody ProductModel product){

        try{
            productService.addProduct(product);
        }catch(VirtualStoreException e){
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductModel>> getProducts() {
        List<ProductModel> products= new ArrayList<>();
        try{
            products= productService.findAll();
            return new ResponseEntity<List<ProductModel>>(products,HttpStatus.OK);
        }catch(VirtualStoreException e){
            return new ResponseEntity<List<ProductModel>>(products,HttpStatus.CONFLICT);
        }


    }

    @DeleteMapping(value = "/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("productId") Long productId) {
        try{
            productService.deleteProduct(productId);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }catch(VirtualStoreException e){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }



    @GetMapping(value = "/{productId}")
    public ResponseEntity<ProductModel> getOneProduct(@PathVariable("productId") Long productId) {
        ProductModel product = new ProductModel();
        try{
            product = productService.findProduct(productId);
            return new ResponseEntity<ProductModel>(product,HttpStatus.OK);
        }catch(VirtualStoreException e){
            return new ResponseEntity<ProductModel>(product,HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{productId}")
    public ResponseEntity<ProductModel> updateProduct(@PathVariable("productId") Long productId, @RequestBody ProductModel updatedProduct) {
        ProductModel product = new ProductModel();
        try{
           product = productService.updateProduct(productId,updatedProduct);
            return new ResponseEntity<ProductModel>(product,HttpStatus.OK);
        }catch(VirtualStoreException e){
            return new ResponseEntity<ProductModel>(product,HttpStatus.NOT_FOUND);
        }
    }



}
