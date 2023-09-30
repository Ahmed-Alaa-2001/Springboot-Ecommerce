package com.example.SpringbootEcommerce.controller;

import com.example.SpringbootEcommerce.dto.ProductDTO;
import com.example.SpringbootEcommerce.exceptions.ProductExist;
import com.example.SpringbootEcommerce.exceptions.ProductNotFound;
import com.example.SpringbootEcommerce.model.Product;
import com.example.SpringbootEcommerce.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/product")
@RestController
@Slf4j
public class ProductController {
    @Autowired
    ProductService productService;
    @GetMapping("/getproducts")
    public ResponseEntity getProducts() throws ProductNotFound{
        try {
            return new ResponseEntity<List<Product>>(productService.findProduct(), HttpStatus.FOUND);
        }catch (ProductNotFound err){
            return new ResponseEntity<String>(err.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getproduct/{name}")
    public ResponseEntity getProduct(@PathVariable String name){
        try {
//            Product product = productService.findProductById(id);
            Product product = productService.findProductByName(name);
            return new ResponseEntity<Product>(product, HttpStatus.FOUND);
        }catch (ProductNotFound err){
            System.out.println(err);
            return new ResponseEntity<String>(err.getMessage(),HttpStatus.NOT_FOUND);
            // Log the exception for debugging purposes
            //log.error("Product not found with ID: {}", id, err);
            // Log the exception for debugging purposes
            //        err.printStackTrace();
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err.getMessage());

        }
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity deleteProduct(@PathVariable String name){
        try{
            return new ResponseEntity<String>(productService.deleteProductByName(name),HttpStatus.OK);
        }catch (ProductNotFound err){
            return new ResponseEntity<String>(err.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody ProductDTO productDTO){
        try{
            return new ResponseEntity<Product>(productService.addProduct(productDTO),HttpStatus.CREATED);
        }catch (ProductExist err){
            return new ResponseEntity<String>(err.getMessage(),HttpStatus.CONFLICT);
        }
    }
    @PutMapping("/Edit")
    public ResponseEntity editProduct(@RequestBody ProductDTO productDTO){
        try{
            return new ResponseEntity<Product>(productService.updateProduct(productDTO),HttpStatus.OK);
        }catch (ProductNotFound err){
            return new ResponseEntity<String>(err.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
