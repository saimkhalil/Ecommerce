package com.example.ecommerce.ecomapp.Controller;


import com.example.ecommerce.ecomapp.Security.UserAuth;
import com.example.ecommerce.ecomapp.Service.EcommService;
import com.example.ecommerce.ecomapp.Validator.ProductValidator;
import com.example.ecommerce.ecomapp.utils.SError;
import com.example.ecommerce.ecommcontracts.Response.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/ecomm")
public class EcomController
{
    @Autowired
    private UserAuth userAuth;

    @Autowired
    private ProductValidator productValidator;

    @Autowired
    private EcommService ecommService;

    @RequestMapping(value = "/placeOrder" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseModel<String> placeOrder(@RequestParam("productId") String productId,
                                             @RequestParam("callingUserId") String callingUserId,
                                             @RequestParam("quantity") int buyingQuantity)
    {
        System.out.println("calling Id :" + callingUserId);
        System.out.println("product Id :" + productId);
        ResponseModel<String> responseModel = new ResponseModel<>();

        try
        {
            userAuth.authenticateUser(callingUserId);
            productValidator.validateProduct(productId);
            responseModel = ecommService.fetchProductById(productId,buyingQuantity);

        }
        catch (IOException e)
        {
            System.out.println("Exception : " + e);
            responseModel.setMessage("Error , please try again");
            responseModel.setHttpStatus(HttpStatus.BAD_REQUEST);

        }
        catch (SError e)
        {
            responseModel.setMessage(e.getMessage());
            responseModel.setHttpStatus(e.getHttpStatus());
        }
        return responseModel;
    }
}
