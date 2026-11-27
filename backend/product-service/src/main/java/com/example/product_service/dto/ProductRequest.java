package com.example.product_service.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record ProductRequest(
        @NotBlank(message = "Tên sản phẩm không được để trống")
        @Size(max = 200, message = "Tên sản phẩm tối đa 200 ký tự")
        String name,

        @NotNull(message = "Giá không được để trống")
        @Positive(message = "Giá phải lớn hơn 0")
        BigDecimal price,

        @NotNull(message = "Số lượng tồn không được để trống")
        @PositiveOrZero(message = "Số lượng tồn không được âm")
        Integer quantity,

        String description,

        @Size(max = 255, message = "URL hình ảnh tối đa 255 ký tự")
        String imageUrl,

        Boolean status,

        @NotNull(message = "ID loại sản phẩm không được để trống")
        Long categoryId,

        @NotNull(message = "ID thương hiệu không được để trống")
        Long brandId
) {}
