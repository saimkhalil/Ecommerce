package com.example.ecommerce.ecomapp.Persistence.Repository;

import com.example.ecommerce.ecomapp.Persistence.Model.Product;

import java.util.List;
import java.util.Optional;

public interface EcommRepository
{
    Optional<Product> findByProductId(String productId);

    void updateQuantity(Product product);

    void saveAll(List<Product> products);
}
