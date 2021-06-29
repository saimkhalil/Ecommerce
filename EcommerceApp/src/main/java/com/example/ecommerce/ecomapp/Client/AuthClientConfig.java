package com.example.ecommerce.ecomapp.Client;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

@Configuration
public class AuthClientConfig
{
    private static final String BASE_URL = "http://localhost:8080/";

    private Retrofit retrofit;

    public AuthClientConfig()
    {
        System.out.println("base URL :" + BASE_URL);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(40, TimeUnit.MILLISECONDS)
                .writeTimeout(40, TimeUnit.MILLISECONDS)
                .readTimeout(40, TimeUnit.MILLISECONDS).build();

        this.retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    @Bean
    public AuthClient getAuthClient()
    {
        return retrofit.create(AuthClient.class);
    }
}
