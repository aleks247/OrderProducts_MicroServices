package com.MicroServices.Product.Controller;

import com.MicroServices.Product.DTO.ProductDTO;
import com.MicroServices.Product.Model.Product;
import com.MicroServices.Product.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> productDTOS = productService.getAllProducts();
        var status = productDTOS.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return ResponseEntity.status(status).body(productDTOS);
    }

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product, UriComponentsBuilder uriComponentsBuilder) {
        URI location = uriComponentsBuilder.path("/products/{id}").buildAndExpand(productService.addProduct(product, uriComponentsBuilder).getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/getProduct/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<Product> deleteProductById(@PathVariable("id") Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/updateProduct{id}")
    public ResponseEntity<ProductDTO> updateProduct(Long id, @RequestBody Product product) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(productService.updateProduct(id, product));
    }
}