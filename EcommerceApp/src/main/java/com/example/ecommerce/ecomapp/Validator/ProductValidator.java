package com.example.ecommerce.ecomapp.Validator;

import com.example.ecommerce.ecomapp.utils.SError;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ProductValidator
{
    public void validateProduct(String productId) throws SError
    {
        if (null == productId || "".equals(productId.trim()))
        {
            throw new SError("Product ID cannot be Empty ", HttpStatus.BAD_REQUEST);
        }
    }
}
