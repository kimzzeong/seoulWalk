package com.example.seoulwalk.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.seoulwalk.R;

public class ActivityResultAfterWalk extends AppCompatActivity {

    TextView textViewCourseName, textViewDateTime, textViewTime, textViewCalorie, textViewSpeed, textViewDistance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_after_walk);

        // 걸은 코스이름
        textViewCourseName = findViewById(R.id.course_name_result_after_walk);
        // 걸은 날짜, 시간
        textViewDateTime = findViewById(R.id.datetime_result_after_walk);
        // 소요시간
        textViewTime = findViewById(R.id.time_result_after_walk);
        // 소모 칼로리
        textViewCalorie = findViewById(R.id.calorie_result_after_walk);
        // 평균 속도
        textViewSpeed = findViewById(R.id.speed_result_after_walk);
        // 이동 거리
        textViewDistance = findViewById(R.id.distance_result_after_walk);
    }
}