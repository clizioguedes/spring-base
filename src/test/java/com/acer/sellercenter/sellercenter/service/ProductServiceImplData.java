package com.acer.sellercenter.sellercenter.service;

import com.acer.sellercenter.sellercenter.dto.ProductDTO;
import com.acer.sellercenter.sellercenter.mappers.ProductDtoMapper;
import com.acer.sellercenter.sellercenter.model.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class ProductServiceImplData {

    private static final ProductDtoMapper mapper = new ProductDtoMapper();

    public static List<Product> getProductList() {
        return List.of(
                new Product(UUID.randomUUID(), "name-p1", "description-p1", BigDecimal.ONE, 2),
                new Product(UUID.randomUUID(), "name-p2", "description-p2", BigDecimal.TEN, 4),
                new Product(UUID.randomUUID(), "name-p3", "description-p3", BigDecimal.valueOf(300.10), 8),
                new Product(UUID.randomUUID(), "name-p4", "description-p4", BigDecimal.valueOf(2999.99), 16),
                new Product(UUID.randomUUID(), "name-p5", "description-p5", BigDecimal.valueOf(999.99), 32)
        );
    }

    public static Product getProduct() {
        return new Product(UUID.randomUUID(), "PRODUCT", "description", BigDecimal.TEN, 4);
    }

    public static ProductDTO getProductDtoToCreate() {
        return new ProductDTO(null, "PRODUCT", "description", BigDecimal.TEN, 4);
    }

    public static List<ProductDTO> getProductListDto(List<Product> products) {
        return products.stream().map(mapper::toDto).toList();
    }

    public static ProductDTO getProductDto(Product product) {
        return mapper.toDto(product);
    }

    public static Product dtoToProduct(ProductDTO dto) {
        return mapper.toEntity(dto);
    }


}
