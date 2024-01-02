package com.microservice.productservice.service.impl;

import com.microservice.productservice.entity.Product;
import com.microservice.productservice.exception.ProductServiceCustomException;
import com.microservice.productservice.payload.request.ProductRequest;
import com.microservice.productservice.payload.response.ProductResponse;
import com.microservice.productservice.repository.ProductRepository;
import com.microservice.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import static org.springframework.beans.BeanUtils.copyProperties;

import org.springframework.beans.factory.annotation.Autowired;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProductServiceImpl implements ProductService {

	//@Autowired
    private final ProductRepository productRepository;
    @Override
    public int addProduct(ProductRequest productRequest) {
        log.info("ProductServiceImpl | addProduct is called");
        Product product = Product.builder().
                productName(productRequest.getName()).
                quantity(productRequest.getQuantity()).
                price(productRequest.getPrice()).
                build();
        product = productRepository.save(product);
        log.info("ProductServiceImpl | addProduct | Product Created");
        log.info("ProductServiceImpl | addProduct | Product Id : " + product.getId());
        return product.getId();
    }

    @Override
    public ProductResponse getProductById(int id) {
        log.info("ProductServiceImpl | getProductById is called");
        Product product = productRepository.
                findById(id).orElseThrow(()-> new RuntimeException("Product with the given id is invalid, id not found"));
        ProductResponse productResponse
                = new ProductResponse();
        copyProperties(product, productResponse);

        log.info("ProductServiceImpl | getProductById | productResponse :" + productResponse.toString());
        return productResponse;
    }

    @Override
    public void reduceQuantity(int id, int quantity) {
        log.info("Reduce Quantity {} for Id: {}", quantity,id);
        Product product = productRepository.
                findById(id).
                orElseThrow(()-> new RuntimeException("Product with the given id is invalid, PRODUCT_NOT_FOUND"));
        if (product.getQuantity()<quantity)
        {
            throw new ProductServiceCustomException(
                    "Product has no sufficient quantity",
                    "INSUFFICIENT_QUANTITY");
        }
        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);
        log.info("Product Quantity is updated");
    }

    @Override
    public void deleteProductById(int id) {
        log.info("Product id: {}", id);

        if (!productRepository.existsById(id)) {
            log.info("Im in this loop {}", !productRepository.existsById(id));
            throw new ProductServiceCustomException(
                    "Product with given with Id: " + id + " not found:",
                    "PRODUCT_NOT_FOUND");
        }
        log.info("Deleting Product with id: {}", id);
        productRepository.deleteById(id);
    }
}
