package com.OrderMicroserviceApp.OrderMicroserviceApp.Client;

import com.OrderMicroserviceApp.OrderMicroserviceApp.DTO.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "orderProductMicroService")
public interface ProductClient {
    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable("id")Long id);
}
