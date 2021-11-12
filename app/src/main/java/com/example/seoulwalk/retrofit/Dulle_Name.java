package com.example.seoulwalk.retrofit;

import com.example.seoulwalk.data.Dulle_Data;
import com.example.seoulwalk.data.PostData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Dulle_Name {

    String REGIST_URL = "http://49.247.196.22/";

    @FormUrlEncoded
    @POST("tim_php/dulle_Name.php")
    Call<String> getDulleRegist(
//            @Field("dulle_start") String dulle_start1,
//            @Field("dulle_end") String dulle_end,
            @Field("dulle_start") String dulle_start   ///서버랑 통신하기 용

    );

    @GET("tim_php/test.php")
    Call<Integer> savePost(
            @Query("code[]") ArrayList<PostData> code
    );


//    @FormUrlEncoded
//    @POST("imgww.php")
//    Call<String> get_imgs(
//            @Field("p_id") String name //아이디
//            //,@Field("k_id") String nick//닉네임
//
//    );
}
