package com.example.rxjavademo.network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {

    //get subscriber
    @GET("http://dummy.restapiexample.com/api/v1/employees")
    public Call<ResponseBody> getEmployee();


}
