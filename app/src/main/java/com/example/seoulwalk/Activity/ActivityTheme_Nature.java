package com.example.seoulwalk.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.seoulwalk.R;
import com.example.seoulwalk.data.DetailCourse_Data;

public class ActivityTheme_Nature extends AppCompatActivity {
    RecyclerView theme_nature_recycler;
    DetailCourse_Data detailCourse_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_nature);

        theme_nature_recycler = findViewById(R.id.theme_nature_recycler);





    }
}