package com.example.product_service.service;

import com.example.product_service.entity.Product;
import com.example.product_service.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getAll() {
        return repository.findAll();
    }

    public Optional<Product> getById(Long id) {
        return repository.findById(id);
    }

    public Product create(Product product) {
        return repository.save(product);
    }

    public Optional<Product> update(Long id, Product productDetails) {
        return repository.findById(id).map(existing -> {
            existing.setName(productDetails.getName());
            existing.setPrice(productDetails.getPrice());
            existing.setQuantity(productDetails.getQuantity());
            existing.setDescription(productDetails.getDescription());
            existing.setImageUrl(productDetails.getImageUrl());
            if (productDetails.getStatus() != null) {
                existing.setStatus(productDetails.getStatus());
            }
            if (productDetails.getCategory() != null) {
                existing.setCategory(productDetails.getCategory());
            }
            if (productDetails.getBrand() != null) {
                existing.setBrand(productDetails.getBrand());
            }
            return repository.save(existing);
        });
    }

    public boolean delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Product> getByCategory(Long categoryId) {
        return repository.findByCategoryId(categoryId);
    }

    public List<Product> getByBrand(Long brandId) {
        return repository.findByBrandId(brandId);
    }

    public List<Product> searchByName(String keyword) {
        return repository.findByNameContainingIgnoreCase(keyword);
    }
}
