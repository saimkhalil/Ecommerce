package com.example.ecommerce.ecomapp.Security;

import com.example.authserver.contracts.Response.ResponseModel;
import com.example.authserver.contracts.Response.UserResponse;
import com.example.ecommerce.ecomapp.Client.AuthClient;
import com.example.ecommerce.ecomapp.utils.SError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

@Component
public class UserAuth
{
    @Autowired
    private AuthClient authClient;

    public void authenticateUser(String userId) throws SError , IOException
    {
        Call<ResponseModel<UserResponse>> userResponseCall = authClient.getUser(userId);

        Response<ResponseModel<UserResponse>> responseModelResponse = userResponseCall.execute();

        if (responseModelResponse.isSuccessful())
        {
            ResponseModel<UserResponse> responseModel = responseModelResponse.body();
            assert responseModel != null;
            if (responseModel.getHttpStatus().is2xxSuccessful())
            {
                System.out.println("Welcome registered User");
            }
            else
            {
                throw new SError("User not registered with us ", HttpStatus.FORBIDDEN);
            }
        }
        else
        {
            throw new SError("Could not authenticate user due to " + responseModelResponse.errorBody(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
