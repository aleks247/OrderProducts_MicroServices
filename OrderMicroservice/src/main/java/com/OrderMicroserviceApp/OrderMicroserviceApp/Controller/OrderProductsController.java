package com.OrderMicroserviceApp.OrderMicroserviceApp.Controller;

import com.OrderMicroserviceApp.OrderMicroserviceApp.Model.OrderProducts;
import com.OrderMicroserviceApp.OrderMicroserviceApp.Service.OrderProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orderProducts")
@RequiredArgsConstructor
public class OrderProductsController {
    private final OrderProductService orderProductsService;

    @GetMapping("/all")
    public ResponseEntity<List<OrderProducts>> getAllOrderProducts() {
        List<OrderProducts> orderProducts = orderProductsService.getAllOrderProducts();
        return ResponseEntity.ok(orderProducts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderProducts> getOrderProductsById(@PathVariable("id") Long id) {
        Optional<OrderProducts> orderProducts = orderProductsService.getOrderProductsById(id);
        return orderProducts.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<OrderProducts> createOrderProducts(@RequestBody OrderProducts orderProducts) {
        OrderProducts createdOrderProducts = orderProductsService.createOrderProducts(orderProducts);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrderProducts);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderProducts> updateOrderProducts(
            @PathVariable("id") Long id,
            @RequestBody OrderProducts updatedOrderProducts
    ) {
        OrderProducts orderProducts = orderProductsService.updateOrderProducts(id, updatedOrderProducts);
        if (orderProducts != null) {
            return ResponseEntity.ok(orderProducts);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderProducts(@PathVariable("id") Long id) {
        boolean deleted = orderProductsService.deleteOrderProducts(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}