package com.example.seoulwalk.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.seoulwalk.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button course_btn, mypage_btn, home_btn, community_btn; //바텀 네비게이션 버튼
    ProgressBar main_week_step_progressBar, main_today_step_progressBar; // 이번주 걸음수, 오늘 걸음수 그래프
    int week_goal_step, week_now_goal_step, today_step, today_goal_step; // 이번주 목표 걸음수, 이번주 걸음수, 오늘 걸음수, 오늘 목표 걸음수
    ImageView level_info;
    TextView main_goal_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //커스텀 액션바 세팅
        Toolbar toolbar;
        ActionBar actionBar;
        toolbar = findViewById(R.id.main_actionbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false); //뒤로가기 아이콘 없앰

        main_goal_info = findViewById(R.id.main_goal_info);

        week_goal_step = 56000; // 이번주 목표 걸음수
        week_now_goal_step = 16800; // 이번주 걸음수
        today_goal_step = 8000; // 오늘 목표 걸음수
        today_step = 2600; // 오늘 걸음수



        course_btn = findViewById(R.id.course_btn);
        home_btn = findViewById(R.id.home_btn);
        community_btn = findViewById(R.id.community_btn);
        mypage_btn = findViewById(R.id.mypage_btn);

        main_week_step_progressBar = findViewById(R.id.main_week_step_progressBar);
        main_today_step_progressBar = findViewById(R.id.main_today_step_progressBar);

        main_week_step_progressBar.setMax(week_goal_step);
        main_week_step_progressBar.setProgress(week_now_goal_step);

        main_today_step_progressBar.setMax(today_goal_step);
        main_today_step_progressBar.setProgress(today_step);

        level_info = findViewById(R.id.level_info);

        level_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ActivityLevelInfo.class);
                startActivity(intent);
            }
        });

        main_goal_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ActivityGoal.class);
                startActivity(intent);
            }
        });

        //코스
        course_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ActivityDulle.class);
                startActivity(intent);
                finish();
            }
        });

        //마이페이지
        mypage_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ActivityMypage.class);
                startActivity(intent);
                finish();
            }
        });

        //홈
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //커뮤니티
        community_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ActivityCommunity.class);
                startActivity(intent);
                finish();
            }
        });





    }

    @Override
    protected void onPause() {
        super.onPause();
        //액티비티를 종료할 때 애니메이션 없애기
        overridePendingTransition(0,0);
    }

}