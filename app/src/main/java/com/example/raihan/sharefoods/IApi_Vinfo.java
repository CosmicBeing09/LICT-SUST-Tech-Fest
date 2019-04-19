package com.example.raihan.sharefoods;



import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IApi_Vinfo {

    @GET("V_info.php")
    Call<List<V_info_object>> getVinfo();

    @GET("profile-list")
    Call<List<Profile_Object>> getProfileinfo();

    @GET("food-request")
    Call<List<FoodRequestObject>> getFoodRequestObject();

    @GET("donated-food")
    Call<List<RecordObject>> getRecordObject();

    @POST("donated-food")
    Call<FoodRequestObject> createFoodRequest(@Body FoodRequestObject foodRequestObject);


}
