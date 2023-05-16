package com.OrderMicroserviceApp.OrderMicroserviceApp.Repository;

import com.OrderMicroserviceApp.OrderMicroserviceApp.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
