package com.microservice.productservice.controller;

import com.microservice.productservice.entity.Product;
import com.microservice.productservice.payload.request.ProductRequest;
import com.microservice.productservice.payload.response.ProductResponse;
import com.microservice.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@Log4j2
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/addProduct")
    public ResponseEntity<Integer> addProduct(@RequestBody ProductRequest productRequest)
    {
        log.info("ProductController | addProduct is called");
        log.info("ProductController | addProduct | productRequest : " + productRequest.toString());
        int id = productService.addProduct(productRequest);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") int id)
    {
        log.info("ProductController | getProductById is called");
        log.info("ProductController | getProductById | id : " + id);
        ProductResponse productResponse = productService.getProductById(id);
        return new ResponseEntity<>(productResponse,HttpStatus.OK);
    }
    @PutMapping("/reduceQuantity/{id}")
    public ResponseEntity<Void> reduceQuantity(
            @PathVariable("id") int id,
            @RequestParam int quantity
    ) {

        log.info("ProductController | reduceQuantity is called");

        log.info("ProductController | reduceQuantity | productId : " + id);
        log.info("ProductController | reduceQuantity | quantity : " + quantity);

        productService.reduceQuantity(id,quantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable("id") int id) {
        productService.deleteProductById(id);
    }

}
