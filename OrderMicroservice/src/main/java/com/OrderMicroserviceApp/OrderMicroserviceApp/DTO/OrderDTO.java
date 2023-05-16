package com.OrderMicroserviceApp.OrderMicroserviceApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {
    private LocalDateTime orderDateTime;
    private String customerName;
    private ProductDTO product;
}