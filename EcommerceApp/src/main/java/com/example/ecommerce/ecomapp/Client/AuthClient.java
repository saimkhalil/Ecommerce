package com.example.ecommerce.ecomapp.Client;

import com.example.authserver.contracts.Response.ResponseModel;
import com.example.authserver.contracts.Response.UserResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface AuthClient
{
    @GET(value = "user/fetchUserById/{id}")
    Call<ResponseModel<UserResponse>> getUser(@Path("id") String id);
}
