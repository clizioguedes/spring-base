package com.acer.sellercenter.sellercenter.controller;

import com.acer.sellercenter.sellercenter.dto.ProductDTO;
import com.acer.sellercenter.sellercenter.model.Product;
import com.acer.sellercenter.sellercenter.service.ProductService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/products")
public class ProductController extends GenericController<Product, ProductDTO, ProductService> {

    protected ProductController(ProductService service) {
        super(service);
    }
}
