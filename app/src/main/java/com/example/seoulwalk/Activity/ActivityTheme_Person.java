package com.example.seoulwalk.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.seoulwalk.R;
import com.example.seoulwalk.data.DetailCourse_Data;

public class ActivityTheme_Person extends AppCompatActivity {
    RecyclerView theme_person_recycler;
    DetailCourse_Data detailCourse_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_person);

        theme_person_recycler = findViewById(R.id.theme_person_recycler);



    }
}