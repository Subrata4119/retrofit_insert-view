package com.subrata.retrofit_2021_with_email.API;



import com.subrata.retrofit_2021_with_email.model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiRequestBiodata {
    @FormUrlEncoded
    @POST("insert.php")
    Call<ResponseModel> sendBiodata(@Field("Name") String name, @Field("Position") String position, @Field("Salary") String salary,@Field("Email") String email);

    @GET("read.php")
    Call<ResponseModel> getBiodata();
}
