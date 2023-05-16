package com.MicroServices.Product.Repository;

import com.MicroServices.Product.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
