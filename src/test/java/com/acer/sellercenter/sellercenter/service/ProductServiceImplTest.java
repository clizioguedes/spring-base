package com.acer.sellercenter.sellercenter.service;

import com.acer.sellercenter.sellercenter.dto.ProductDTO;
import com.acer.sellercenter.sellercenter.mappers.ProductDtoMapper;
import com.acer.sellercenter.sellercenter.model.Product;
import com.acer.sellercenter.sellercenter.repository.ProductRepository;
import com.acer.sellercenter.sellercenter.utils.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductDtoMapper productDtoMapper;

    private ProductService productService;

    ProductServiceImplTest() {
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productService = new ProductServiceImpl(productRepository, productDtoMapper);
    }

    @Test
    void findAll_ShouldReturnListOfProducts_WhenProductsExist() {
        // Arrange
        List<Product> products = ProductServiceImplData.getProductList();
        List<ProductDTO> productsDto = ProductServiceImplData.getProductListDto(products);
        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "name");

        when(productRepository.findAll(pageable)).thenReturn(new PageImpl<>(products));
        when(productDtoMapper.toDto(products)).thenReturn(productsDto);


        // Act
        Page<ProductDTO> result = productService.findAll(pageable);

        // Assert
        assertNotNull(result);
        assertEquals(products.size(), result.getTotalElements());
        assertEquals(productsDto, result.getContent());
    }

    @Test
    void findAll_ShouldReturnEmptyList_WhenNoProductsExist() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "name");
        when(productRepository.findAll(pageable)).thenReturn(Page.empty());

        // Act
        Page<ProductDTO> result = productService.findAll(pageable);

        // Assert
        assertNotNull(result);
        assertTrue(result.getContent().isEmpty());
    }

    @Test
    void findById_WithValidId_ShouldReturnProductDTO() {
        // Arrange
        Product product = ProductServiceImplData.getProduct();
        ProductDTO productDTO = ProductServiceImplData.getProductDto(product);
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        when(productDtoMapper.toDto(product)).thenReturn(productDTO);

        // Act
        ProductDTO result = productService.findById(product.getId());

        // Assert
        assertNotNull(result);
        assertEquals(productDTO, result);
    }

    @Test
    void findById_WithInvalidId_ShouldThrowResourceNotFoundException() {
        // Arrange
        UUID invalidId = UUID.randomUUID();
        when(productRepository.findById(invalidId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> productService.findById(invalidId));
    }

    @Test
    void create_ShouldCreateProduct_ReturnProductDTO() {
        // Arrange
        ProductDTO productDTO = ProductServiceImplData.getProductDtoToCreate();
        Product product = ProductServiceImplData.dtoToProduct(productDTO);

        Product savedProduct = ProductServiceImplData.getProduct();
        ProductDTO savedProductDto = ProductServiceImplData.getProductDto(savedProduct);

        when(productDtoMapper.toEntity(productDTO)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(savedProduct);
        when(productDtoMapper.toDto(savedProduct)).thenReturn(savedProductDto);

        // Act
        ProductDTO result = productService.create(productDTO);

        // Assert
        assertNotNull(result);
        assertEquals(savedProductDto, result);
    }

    @Test
    void update_ShouldUpdateProduct_ReturnUpdatedProductDTO() {
        // Arrange
        Product existingProduct = ProductServiceImplData.getProduct();

        ProductDTO updatedProductDTO = new ProductDTO(
                existingProduct.getId(),
                "Updated Product",
                existingProduct.getDescription(),
                existingProduct.getPrice(),
                existingProduct.getQuantity()
        );

        Product updatedProduct = ProductServiceImplData.dtoToProduct(updatedProductDTO);


        when(productRepository.findById(updatedProductDTO.id())).thenReturn(Optional.of(existingProduct));
        when(productDtoMapper.toEntity(updatedProductDTO)).thenReturn(updatedProduct);
        when(productRepository.save(updatedProduct)).thenReturn(updatedProduct);
        when(productDtoMapper.toDto(updatedProduct)).thenReturn(updatedProductDTO);

        // Act
        ProductDTO result = productService.update(existingProduct.getId(), updatedProductDTO);

        // Assert
        assertNotNull(result);
        assertEquals("Updated Product", result.name());
    }

    @Test
    void update_WithNonExistentId_ShouldThrowResourceNotFoundException() {
        // Arrange
        UUID nonExistentId = UUID.randomUUID();
        Product product = ProductServiceImplData.getProduct();
        ProductDTO updatedProductDTO = ProductServiceImplData.getProductDto(product);
        when(productRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> productService.update(nonExistentId, updatedProductDTO));
    }

    @Test
    void deleteById_ShouldMarkProductAsDeleted() {
        // Arrange
        UUID productId = UUID.randomUUID();
        Product product = ProductServiceImplData.getProduct();
        product.setDeleted(false);
        Product deletedProduct = ProductServiceImplData.getProduct();
        deletedProduct.setDeleted(true);
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(deletedProduct);

        // Act
        productService.deleteById(productId);

        // Assert
        assertTrue(deletedProduct.isDeleted());
    }


}