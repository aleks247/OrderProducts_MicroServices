package com.MicroServices.Product;

import com.MicroServices.Product.DTO.ProductDTO;
import com.MicroServices.Product.Model.Product;
import com.MicroServices.Product.Repository.ProductRepository;
import com.MicroServices.Product.Service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ProductService productService;

    @Test
    void testGetAllProducts() {
        List<Product> Products = Arrays.asList(
                new Product(),
                new Product()
        );
        Mockito.when(productRepository.findAll()).thenReturn(Products);
        List<ProductDTO> result = productService.getAllProducts();
        List<ProductDTO> ProductDTOS = Products.stream().map(prc -> modelMapper.map(prc, ProductDTO.class)).collect(Collectors.toList());
        assertEquals(ProductDTOS, result);
        Mockito.verify(productRepository).findAll();
    }
    @Test
    void testAddProduct() {
        Product product = new Product();
        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);
        Product result = productService.addProduct(product);
        assertEquals(product, result);
        Mockito.verify(productRepository).save(Mockito.any(Product.class));
    }
    @Test
    void testGetProductById() {
        Long productId = 1L;
        Product product = new Product();
        Mockito.when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        ProductDTO result = productService.getProductById(productId);
        ProductDTO ProductDTO = modelMapper.map(product, ProductDTO.class);
        assertEquals(ProductDTO, result);
        Mockito.verify(productRepository).findById(productId);
    }

    @Test
    void testDeleteProductById() {
        Long productId = 1L;
        productService.deleteProductById(productId);
        Mockito.verify(productRepository).deleteById(productId);
    }
    @Test
    void testUpdateProduct() throws ChangeSetPersister.NotFoundException {
        Long productId = 1L;
        Product originalProduct = new Product();
        Product updatedProduct = new Product();
        Mockito.when(productRepository.findById(productId)).thenReturn(Optional.of(originalProduct));
        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(updatedProduct);
        ProductDTO result = productService.updateProduct(productId, updatedProduct);
        ProductDTO ProductDTO = modelMapper.map(originalProduct, ProductDTO.class);
        assertEquals(ProductDTO, result);
        Mockito.verify(productRepository).findById(productId);
        Mockito.verify(productRepository).save(Mockito.any(Product.class));
    }
}