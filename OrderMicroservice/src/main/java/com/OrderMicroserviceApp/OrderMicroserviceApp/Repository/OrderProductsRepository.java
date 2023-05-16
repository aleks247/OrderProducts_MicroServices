package com.OrderMicroserviceApp.OrderMicroserviceApp.Repository;

import com.OrderMicroserviceApp.OrderMicroserviceApp.Model.OrderProducts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductsRepository extends JpaRepository<OrderProducts, Long> {
}
