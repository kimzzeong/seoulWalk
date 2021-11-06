package com.example.seoulwalk;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ActivityJoin extends AppCompatActivity {

    NumberPicker numberPicker_year;
    RadioButton sex_female, sex_male;
    RadioGroup sex_group;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        //커스텀 액션바
        Toolbar toolbar;
        ActionBar actionBar;
        toolbar = findViewById(R.id.join_title);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(false); //뒤로가기 아이콘 없앰
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);

        //생년 선택
        numberPicker_year = findViewById(R.id.join_year);
        numberPicker_year.setMinValue(1910);
        numberPicker_year.setMaxValue(2021);
        numberPicker_year.setWrapSelectorWheel(false); // 최소값과 최대값이 순환되지 않게 설정
        numberPicker_year.setValue(2000); // 초기값 설정

        //성별 선택
        sex_group = findViewById(R.id.sex_group);
        sex_female = findViewById(R.id.sex_female);
        sex_male = findViewById(R.id.sex_male);

    }
}