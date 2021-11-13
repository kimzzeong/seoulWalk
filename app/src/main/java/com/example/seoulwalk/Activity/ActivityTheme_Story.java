package com.example.seoulwalk.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.seoulwalk.R;
import com.example.seoulwalk.adapter.Theme_Detail_Adapter;
import com.example.seoulwalk.data.DetailCourse_Data;

import java.util.ArrayList;

public class ActivityTheme_Story extends AppCompatActivity {
    RecyclerView theme_story_recycler;

    ArrayList<DetailCourse_Data> detailCourse_data;
    Theme_Detail_Adapter themeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_story);
        theme_story_recycler = findViewById(R.id.theme_story_recycler);


        // 리스트 초기화 후 레트로핏통신
        detailCourse_data = new ArrayList<>();


        themeAdapter = new Theme_Detail_Adapter(getApplicationContext(), detailCourse_data);


    }
}