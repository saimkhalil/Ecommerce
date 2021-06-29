package com.example.ecommerce.ecommcontracts.Response;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
public class ResponseModel<T>
{
    private T data;
    private HttpStatus httpStatus;
    private String message;

}
