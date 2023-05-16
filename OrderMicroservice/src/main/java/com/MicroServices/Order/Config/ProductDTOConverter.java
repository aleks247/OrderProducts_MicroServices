package com.MicroServices.Order.Config;

import com.MicroServices.Order.DTO.ProductDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;

public class ProductDTOConverter implements AttributeConverter<ProductDTO, String> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(ProductDTO attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    @Override
    public ProductDTO convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, ProductDTO.class);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
