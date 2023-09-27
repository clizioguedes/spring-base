package com.acer.sellercenter.sellercenter.service;

import com.acer.sellercenter.sellercenter.dto.ProductDTO;
import com.acer.sellercenter.sellercenter.mappers.DtoMapper;
import com.acer.sellercenter.sellercenter.mappers.ProductDtoMapper;
import com.acer.sellercenter.sellercenter.model.Product;
import com.acer.sellercenter.sellercenter.repository.GenericRepository;
import com.acer.sellercenter.sellercenter.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final ProductDtoMapper mapper;

    public ProductServiceImpl(ProductRepository repository, ProductDtoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public GenericRepository<Product> getRepository() {
        return this.repository;
    }

    @Override
    public DtoMapper<Product, ProductDTO> getDtoMapper() {
        return this.mapper;
    }
}
