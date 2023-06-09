package com.MicroServices.Order.Model;

import com.MicroServices.Order.DTO.ProductDTO;
import com.MicroServices.Order.Config.ProductDTOConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order_id;

    @Convert(converter = ProductDTOConverter.class)
    private ProductDTO product;
}
