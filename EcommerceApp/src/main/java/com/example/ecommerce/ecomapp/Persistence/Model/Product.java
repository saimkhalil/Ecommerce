package com.example.ecommerce.ecomapp.Persistence.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@ToString
@Data
@Document("Product")
public class Product
{
    private int quantity;
    private String productId;

    public static class Constants
    {
        public static final String PRODUCT_ID = "productId";
        public static final String QUANTITY = "quantity";
    }
}



