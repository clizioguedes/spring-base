package com.acer.sellercenter.sellercenter.mappers;

import com.acer.sellercenter.sellercenter.dto.ProductDTO;
import com.acer.sellercenter.sellercenter.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDtoMapper implements DtoMapper<Product, ProductDTO> {
    @Override
    public ProductDTO toDto(Product entity) {
        return new ProductDTO(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getQuantity()
        );
    }

    @Override
    public Product toEntity(ProductDTO dto) {
        return new Product(
                dto.id(),
                dto.name(),
                dto.description(),
                dto.price(),
                dto.quantity()
        );
    }
}
