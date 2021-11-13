package com.example.seoulwalk.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.seoulwalk.R;
import com.example.seoulwalk.adapter.DetailCourseAdapter;
import com.example.seoulwalk.adapter.ThemeAdapter;
import com.example.seoulwalk.adapter.Theme_Detail_Adapter;
import com.example.seoulwalk.data.DetailCourse_Data;
import com.example.seoulwalk.retrofit.ApiClient;
import com.example.seoulwalk.retrofit.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityTheme_Person extends AppCompatActivity {
    RecyclerView theme_person_recycler;
    ArrayList<DetailCourse_Data> detailCourse_data;

    Theme_Detail_Adapter themeAdapter;
    ActivityDulle activityDulle = (ActivityDulle)ActivityDulle.activityDulle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_person);
        Log.e("Theme_PERSON","1");

        theme_person_recycler = findViewById(R.id.theme_person_recycler);

        theme_person_recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));

        String Theme_name = "Person";


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


                    theme_person_recycler.setAdapter(themeAdapter);

                    themeAdapter.notifyDataSetChanged();


                    themeAdapter.setOnItemClickListener(new Theme_Detail_Adapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View v, int position, int stepCountInt, int timeInt, double distanceDouble, double lengthDouble, String courseFullNameString, String courseNameString, String difficultyString, String startString, String endString, String latlngStart, String latlngEnd) {
                            Intent intent = new Intent(ActivityTheme_Person.this,ActivityCourseInfo.class);
                            intent.putExtra("stepCountInt", stepCountInt);
                            intent.putExtra("timeInt", timeInt);
                            intent.putExtra("distanceDouble", distanceDouble);
                            intent.putExtra("lengthDouble", lengthDouble);
                            intent.putExtra("courseFullNameString", courseFullNameString);
                            intent.putExtra("courseNameString", courseNameString);
                            intent.putExtra("difficultyString", difficultyString);
                            intent.putExtra("dulle_start", startString);
                            intent.putExtra("dulle_end", endString);
                            intent.putExtra("LanLng", latlngStart);
                            intent.putExtra("LatLng_end", latlngEnd);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                            startActivity(intent);
                        }
                    });

                }
            }

            @Override
            public void onFailure(@NonNull Call<List<DetailCourse_Data>> call, @NonNull Throwable t) {

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("Theme_PERSON","2");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("Theme_PERSON","3");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("Theme_PERSON","4");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("Theme_PERSON","5");


    }


}