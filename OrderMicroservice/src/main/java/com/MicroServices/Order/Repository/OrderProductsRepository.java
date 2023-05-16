package com.MicroServices.Order.Repository;

import com.MicroServices.Order.Model.OrderProducts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductsRepository extends JpaRepository<OrderProducts, Long> {
}
