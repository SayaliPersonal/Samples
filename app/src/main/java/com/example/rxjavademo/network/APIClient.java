package com.example.rxjavademo.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static Retrofit retrofit = null;
    public static Retrofit getClient() {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new BasicAuthInterceptor("tvaeon","AYKGhA73QE43yun8vR")).build();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://dummy.restapiexample.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return retrofit;
    }
}
