package com.example.product_service.dto;

import java.math.BigDecimal;

public record ProductResponse(
        Long id,
        String name,
        BigDecimal price,
        Integer quantity,
        String description,
        String imageUrl,
        Boolean status,
        Long categoryId,
        String categoryName,
        Long brandId,
        String brandName
) {}
