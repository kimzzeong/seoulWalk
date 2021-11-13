package com.example.seoulwalk.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.seoulwalk.R;
import com.example.seoulwalk.adapter.Theme_Detail_Adapter;
import com.example.seoulwalk.data.DetailCourse_Data;
import com.example.seoulwalk.retrofit.ApiClient;
import com.example.seoulwalk.retrofit.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityTheme_Walk extends AppCompatActivity {

    RecyclerView theme_walk_recycler;
    ArrayList<DetailCourse_Data> detailCourse_data;
    Theme_Detail_Adapter themeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_walk);
        theme_walk_recycler = findViewById(R.id.theme_walk_recycler);

        // 리스트 초기화 후 레트로핏통신
        detailCourse_data = new ArrayList<>();


        themeAdapter = new Theme_Detail_Adapter(getApplicationContext(), detailCourse_data);


        theme_walk_recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));

        String Theme_name = "Walk";



        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<DetailCourse_Data>> call = apiInterface.get_ThemeDetailCourse(Theme_name);
        call.enqueue(new Callback<List<DetailCourse_Data>>() {
            @Override
            public void onResponse(@NonNull Call<List<DetailCourse_Data>> call, @NonNull Response<List<DetailCourse_Data>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    detailCourse_data = new ArrayList<>();

                    detailCourse_data.addAll(response.body());

                    System.out.println(detailCourse_data.toString());
                    themeAdapter = new Theme_Detail_Adapter(getApplicationContext(), detailCourse_data);


                    theme_walk_recycler.setAdapter(themeAdapter);

                    themeAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(@NonNull Call<List<DetailCourse_Data>> call, @NonNull Throwable t) {

            }
        });

    }
}