package com.example.seoulwalk.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.seoulwalk.R;
import com.example.seoulwalk.data.Login;
import com.example.seoulwalk.retrofit.Dulle_Name;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ActivityLogin extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Button login_btn;
    EditText login_email,login_pw;

    String user_idx, user_nickname;






    private static final String SHARED_PREF_NAME = "mypref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_btn = findViewById(R.id.login_btn);
        login_email = findViewById(R.id.login_email);
        login_pw = findViewById(R.id.login_pw);

        sharedPreferences =getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        user_idx = sharedPreferences.getString("user_idx","");
        user_nickname = sharedPreferences.getString("user_nickname","");
        if(!user_idx.equals("")){
            Intent intent = new Intent(ActivityLogin.this,MainActivity.class);
            startActivity(intent);
            finish();
        }

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Gson gson = new GsonBuilder()
                        .setLenient()
                        .create();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Dulle_Name.REGIST_URL)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();

                Dulle_Name api = retrofit.create(Dulle_Name.class);
//                Call<Login> call = api.requestLogin(login_email.getText().toString(),login_pw.getText().toString());
//
//                call.enqueue(new Callback<Login>(){
//
//                    @Override
//                    public void onResponse(Call<Login> call, Response<Login> response) {
//
//                        if (response.isSuccessful() && response.body() != null)
//                        {
//                            editor = sharedPreferences.edit();
//
//                            Intent intent = new Intent(ActivityLogin.this, MainActivity.class);
//                            startActivity(intent);
//                            editor.putString("user_idx",response.body().getUser_idx());
//                            editor.putString("user_nickname",response.body().getNickname());
//                            editor.apply();
//
//                            Toast.makeText(getApplicationContext(),response.body().getNickname()+"님 어서오세요.",Toast.LENGTH_SHORT).show();
//                            finish();
//
//                        }
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<Login> call, Throwable t) {
//                        Log.e("에러",t.getLocalizedMessage());
//                    }
//                });
            }
        });


    }
}