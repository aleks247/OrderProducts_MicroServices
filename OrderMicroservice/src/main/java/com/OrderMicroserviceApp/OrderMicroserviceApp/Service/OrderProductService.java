package com.OrderMicroserviceApp.OrderMicroserviceApp.Service;

import com.OrderMicroserviceApp.OrderMicroserviceApp.Model.OrderProducts;
import com.OrderMicroserviceApp.OrderMicroserviceApp.Repository.OrderProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderProductService {

    private final OrderProductsRepository orderProductsRepository;


    public List<OrderProducts> getAllOrderProducts() {
        return orderProductsRepository.findAll();
    }

    public Optional<OrderProducts> getOrderProductsById(Long id) {
        return orderProductsRepository.findById(id);
    }

    public OrderProducts createOrderProducts(OrderProducts orderProducts) {
        return orderProductsRepository.save(orderProducts);
    }

    public OrderProducts updateOrderProducts(Long id, OrderProducts updatedOrderProducts) {
        Optional<OrderProducts> existingOrderProducts = orderProductsRepository.findById(id);
        if (existingOrderProducts.isPresent()) {
            updatedOrderProducts.setId(id);
            return orderProductsRepository.save(updatedOrderProducts);
        }
        return null;
    }

    public boolean deleteOrderProducts(Long id) {
        Optional<OrderProducts> orderProducts = orderProductsRepository.findById(id);
        if (orderProducts.isPresent()) {
            orderProductsRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
