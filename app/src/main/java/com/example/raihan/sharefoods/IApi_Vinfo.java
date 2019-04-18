package com.example.raihan.sharefoods;



import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IApi_Vinfo {

    @GET("V_info.php")
    Call<List<V_info_object>> getVinfo();

    @GET("V_info.php")
    Call<List<Profile_Object>> getProfileinfo();
}
