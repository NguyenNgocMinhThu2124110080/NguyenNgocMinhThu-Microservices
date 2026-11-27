package com.example.product_service.service;

import com.example.product_service.dto.ProductRequest;
import com.example.product_service.dto.ProductResponse;
import com.example.product_service.entity.Brand;
import com.example.product_service.entity.Category;
import com.example.product_service.entity.Product;
import com.example.product_service.exception.ResourceNotFoundException;
import com.example.product_service.repository.BrandRepository;
import com.example.product_service.repository.CategoryRepository;
import com.example.product_service.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;

    public ProductService(ProductRepository repository,
                          CategoryRepository categoryRepository,
                          BrandRepository brandRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
    }

    private ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getQuantity(),
                product.getDescription(),
                product.getImageUrl(),
                product.getStatus(),
                product.getCategory() != null ? product.getCategory().getId() : null,
                product.getCategory() != null ? product.getCategory().getName() : null,
                product.getBrand() != null ? product.getBrand().getId() : null,
                product.getBrand() != null ? product.getBrand().getName() : null
        );
    }

    public List<ProductResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ProductResponse getById(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Không tìm thấy sản phẩm: " + id
                        )
                );
        return toResponse(product);
    }

    public List<ProductResponse> getByCategory(Long categoryId) {
        return repository.findByCategoryId(categoryId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<ProductResponse> getByBrand(Long brandId) {
        return repository.findByBrandId(brandId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<ProductResponse> searchByName(String keyword) {
        return repository.findByNameContainingIgnoreCase(keyword)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ProductResponse create(ProductRequest request) {
        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Không tìm thấy loại sản phẩm: " + request.categoryId()
                        )
                );
        Brand brand = brandRepository.findById(request.brandId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Không tìm thấy thương hiệu: " + request.brandId()
                        )
                );

        Product product = new Product();
        product.setName(request.name());
        product.setPrice(request.price());
        product.setQuantity(request.quantity());
        product.setDescription(request.description());
        product.setImageUrl(request.imageUrl());
        product.setStatus(request.status() != null ? request.status() : true);
        product.setCategory(category);
        product.setBrand(brand);

        return toResponse(repository.save(product));
    }

    public ProductResponse update(Long id, ProductRequest request) {
        Product existing = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Không tìm thấy sản phẩm: " + id
                        )
                );
        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Không tìm thấy loại sản phẩm: " + request.categoryId()
                        )
                );
        Brand brand = brandRepository.findById(request.brandId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Không tìm thấy thương hiệu: " + request.brandId()
                        )
                );

        existing.setName(request.name());
        existing.setPrice(request.price());
        existing.setQuantity(request.quantity());
        existing.setDescription(request.description());
        existing.setImageUrl(request.imageUrl());
        if (request.status() != null) {
            existing.setStatus(request.status());
        }
        existing.setCategory(category);
        existing.setBrand(brand);

        return toResponse(repository.save(existing));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Không tìm thấy sản phẩm: " + id
            );
        }
        repository.deleteById(id);
    }
}
