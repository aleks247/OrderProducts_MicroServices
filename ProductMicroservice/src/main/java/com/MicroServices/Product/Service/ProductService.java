package com.MicroServices.Product.Service;

import com.MicroServices.Product.DTO.ProductDTO;
import com.MicroServices.Product.Model.Product;
import com.MicroServices.Product.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(product -> modelMapper.map(product, ProductDTO.class)).collect(Collectors.toList());
    }

    public Product addProduct(Product product, UriComponentsBuilder uriComponentsBuilder) {
        return productRepository.save(product);
    }

    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(RuntimeException::new);
        return modelMapper.map(product, (Type) ProductDTO.class);
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    public ProductDTO updateProduct(Long id, Product product) throws ChangeSetPersister.NotFoundException {
        Product productUpdate = productRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        productUpdate.setName(product.getName());
        productUpdate.setDescription(product.getDescription());
        productUpdate.setPrice(product.getPrice());
        productRepository.save(productUpdate);
        return modelMapper.map(productUpdate, ProductDTO.class);
    }
}
