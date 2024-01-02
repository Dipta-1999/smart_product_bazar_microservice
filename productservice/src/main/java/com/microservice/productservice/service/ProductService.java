package com.microservice.productservice.service;

import com.microservice.productservice.payload.request.ProductRequest;
import com.microservice.productservice.payload.response.ProductResponse;

public interface ProductService {
    int addProduct(ProductRequest productRequest);
    ProductResponse getProductById(int id);
    void reduceQuantity(int id, int quantity);
    public void deleteProductById(int id);
}
