package com.example.ecommerce.ecomapp.Mapper;

import com.example.ecommerce.ecomapp.Persistence.Model.Product;
import com.example.ecommerce.ecommcontracts.Response.ProductResponse;
import org.springframework.stereotype.Component;

@Component
public class EcommMapper
{
    public ProductResponse getProductResponse(Product product)
    {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setProductId(product.getProductId());
        productResponse.setQuantity(product.getQuantity());
        return productResponse;
    }

}
