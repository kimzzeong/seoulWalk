package com.example.seoulwalk.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.seoulwalk.R;

public class ActivityJoin extends AppCompatActivity {

    RadioButton sex_female, sex_male;
    RadioGroup sex_group;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        //커스텀 액션바 세팅
        Toolbar toolbar;
        ActionBar actionBar;
        toolbar = findViewById(R.id.join_actionbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false); //뒤로가기 아이콘 없앰



        //성별 선택
        sex_group = findViewById(R.id.sex_group);
        sex_female = findViewById(R.id.sex_female);
        sex_male = findViewById(R.id.sex_male);

    }
}