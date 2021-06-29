package com.example.ecommerce.ecomapp.Service;

import com.example.ecommerce.ecomapp.Mapper.EcommMapper;
import com.example.ecommerce.ecomapp.Persistence.Model.Product;
import com.example.ecommerce.ecomapp.Persistence.Repository.EcommRepository;
import com.example.ecommerce.ecommcontracts.Response.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class EcommService
{
    @Autowired
    private EcommRepository ecommRepository;

    @Autowired
    private EcommMapper ecommMapper;

    public ResponseModel<String> fetchProductById(String productId, int buyingQuantity)
    {
        Optional<Product> optionalProduct = ecommRepository.findByProductId(productId);
        System.out.println(optionalProduct);
        ResponseModel<String> responseModel = new ResponseModel<>();

        if (optionalProduct.isPresent())
        {
            Product product = optionalProduct.get();
            System.out.println("product ------>" + product);
            int productQuantity = product.getQuantity();
            if (productQuantity >= buyingQuantity)
            {
                product.setQuantity(product.getQuantity()-buyingQuantity);
                //use update here
                ecommRepository.updateQuantity(product);
//                ProductResponse productResponse = ecommMapper.getProductResponse(product);
                responseModel.setData(productId);
                responseModel.setMessage("Order successfully placed for the given Id");
                responseModel.setHttpStatus(HttpStatus.OK);
                return responseModel;
            }
            else
            {
                responseModel.setMessage("Quantity should be below or equal to " + productQuantity);
                responseModel.setHttpStatus(HttpStatus.BAD_REQUEST);
                return responseModel;
            }
        }
        else
        {
            responseModel.setMessage("Invalid product ID");
            responseModel.setHttpStatus(HttpStatus.BAD_REQUEST);
            return responseModel;
        }
    }

    @PostConstruct
    public void seedData()
    {
        System.out.println("here...........");
        Product p1 = new Product(5, "1");
        Product p2 = new Product(7, "2");
        Product p3 = new Product(12, "3");
        Product p4 = new Product(13, "4");
        Product p5 = new Product(15, "5");
        Product p6 = new Product(25, "6");
        Product p7 = new Product(65, "7");
        Product p8 = new Product(23, "8");
        Product p9 = new Product(67, "9");

        List<Product> products = Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9);
        ecommRepository.saveAll(products);
    }
}
