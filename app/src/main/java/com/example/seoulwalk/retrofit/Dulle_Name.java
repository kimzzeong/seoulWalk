package com.example.seoulwalk.retrofit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Dulle_Name {

    String REGIST_URL = "http://49.247.196.22/";

    @FormUrlEncoded
    @POST("dulle_Name.php")
    Call<String> getDulleRegist(
//            @Field("dulle_start") String dulle_start,
//            @Field("dulle_end") String dulle_end,
            @Field("dulle_start") String dulle_start   ///서버랑 통신하기 용

    );


    @FormUrlEncoded
    @POST("imgww.php")
    Call<String> get_imgs(
            @Field("p_id") String name //아이디
            //,@Field("k_id") String nick//닉네임

    );
}