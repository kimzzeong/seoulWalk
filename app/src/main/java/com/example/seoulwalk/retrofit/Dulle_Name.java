package com.example.seoulwalk.retrofit;

import com.example.seoulwalk.data.Dulle_Data;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Dulle_Name {

    String REGIST_URL = "http://49.247.196.22/";

    @FormUrlEncoded
    @POST("tim_php/dulle_Name.php")
    Call<ArrayList<Dulle_Data>> getDulleRegist(
        //    @Field("dulle_start") String dulle_start1,
            @Field("dulle_end") String dulle_end,
            @Field("dulle_start") String dulle_start   ///서버랑 통신하기 용

    );


//    @FormUrlEncoded
//    @POST("imgww.php")
//    Call<String> get_imgs(
//            @Field("p_id") String name //아이디
//            //,@Field("k_id") String nick//닉네임
//
//    );
}
