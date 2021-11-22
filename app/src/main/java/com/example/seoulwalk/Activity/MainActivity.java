package com.example.seoulwalk.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
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
import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button course_btn, mypage_btn, home_btn, community_btn; //바텀 네비게이션 버튼
    ProgressBar main_week_step_progressBar, main_today_step_progressBar; // 이번주 걸음수, 오늘 걸음수 그래프
    int week_goal_step, week_now_goal_step, today_step, today_goal_step; // 이번주 목표 걸음수, 이번주 걸음수, 오늘 걸음수, 오늘 목표 걸음수
    ImageView level_info,main_profile_photo,point_shop;
    TextView main_goal_info, main_my_exercise_info,main_goal_step_count,main_week_step_count,main_today_goal_step_count,
            main_today_step_count,main_nickname,main_level,main_week_goal;
    ConstraintLayout main_course_good;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private static final String SHARED_PREF_NAME = "mypref";
    String user_idx, user_nickname, user_profile, user_status;
    int user_level,user_goal_level;
    TextView week_percent,day_percent;

    String check; // 걷기 완료 후 메인으로 돌아오는거 확인하는 변수(인텐트로 넘어옴)

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

        main_goal_step_count = findViewById(R.id.main_goal_step_count);
        main_week_step_count = findViewById(R.id.main_week_step_count);
        main_today_goal_step_count = findViewById(R.id.main_today_goal_step_count);
        main_today_step_count = findViewById(R.id.main_today_step_count);
        main_course_good = findViewById(R.id.main_course_good);
        main_profile_photo = findViewById(R.id.main_profile_photo);
        main_week_goal = findViewById(R.id.main_week_goal);
        week_percent = findViewById(R.id.week_percent);
        day_percent = findViewById(R.id.day_percent);
        point_shop = findViewById(R.id.point_shop);


        main_nickname = findViewById(R.id.main_nickname);
        main_level = findViewById(R.id.main_level);

        main_goal_info = findViewById(R.id.main_goal_info);


        Intent intent = getIntent();
        if(!TextUtils.isEmpty(intent.getStringExtra("확인"))){
            check = intent.getStringExtra("확인");
            Log.e("Main intent",check);
        }else{
            check = "비어있음";
        }
        Log.e("Main intent",check);

        // 나의 활동 분석으로 이동
        main_my_exercise_info = findViewById(R.id.main_my_exercise_info);
        main_my_exercise_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityMyExerciseInfo.class);
                startActivity(intent);
            }
        });

        point_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ActivityPoint.class);
                startActivity(intent);
            }
        });

        main_nickname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"여기에 다이얼로그",Toast.LENGTH_SHORT).show();
                //커스텀 다이얼로그가 있던 자리인데 완주 액티비티로 옮김.
            }
        });

        week_goal_step = 33362; // 이번주 목표 걸음수
        week_now_goal_step = 19800; // 이번주 걸음수
        today_goal_step = 4766; // 오늘 목표 걸음수
        today_step = 3870; // 오늘 걸음수
        sharedPreferences =getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        editor = sharedPreferences.edit();

        //유저 정보 쉐어드 저장
        editor.putString("user_idx","1");
        editor.putString("user_nickname","지나지나");
//        editor.putString("user_profile","");
//        editor.putInt("user_level",2);
//        editor.putString("user_status","유지");
//        editor.putInt("user_goal_level",3);
//        editor.putInt("goal_week_step_count",week_goal_step);
//        editor.putInt("goal_day_step_count",today_goal_step);
        editor.apply();


        course_btn = findViewById(R.id.course_btn);
        home_btn = findViewById(R.id.home_btn);
        community_btn = findViewById(R.id.community_btn);
        mypage_btn = findViewById(R.id.mypage_btn);

        main_week_step_progressBar = findViewById(R.id.main_week_step_progressBar);
        main_today_step_progressBar = findViewById(R.id.main_today_step_progressBar);



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
                overridePendingTransition(0,0);
            }
        });

        //마이페이지
        mypage_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ActivityMypage.class);
                startActivity(intent);
                finish();
                overridePendingTransition(0,0);
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
                overridePendingTransition(0,0);
            }
        });

        main_course_good.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this,ActivityCourseInfo.class);
//                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        user_profile = sharedPreferences.getString("user_profile","");


        user_idx = sharedPreferences.getString("user_idx","");
        user_nickname = sharedPreferences.getString("user_nickname","");
        user_status = sharedPreferences.getString("user_status","");
        user_level = sharedPreferences.getInt("user_level",0);
        user_goal_level = sharedPreferences.getInt("user_goal_level",0);
        week_goal_step = sharedPreferences.getInt("goal_week_step_count",0);
        today_goal_step = sharedPreferences.getInt("goal_day_step_count",0);


        main_nickname.setText(user_nickname);
        main_level.setText("Lv."+user_level);

        main_level.setText("Lv."+user_level);
        main_week_step_progressBar.setMax(week_goal_step);
        main_week_step_progressBar.setProgress(week_now_goal_step);

        main_today_step_progressBar.setMax(today_goal_step);
        main_today_step_progressBar.setProgress(today_step);

        main_goal_step_count.setText(String.valueOf(week_goal_step));
        main_today_goal_step_count.setText(String.valueOf(today_goal_step));
        main_week_step_count.setText(String.valueOf(week_now_goal_step));
        main_today_step_count.setText(String.valueOf(today_step));



        //목표 걸음수 퍼센트
        int week_percent_num, day_percent_num;
        week_percent_num = (int) Math.floor((double) week_now_goal_step / (double) week_goal_step * 100.0);
        if(week_percent_num > 100){
            week_percent_num = 100;
        }

        day_percent_num = (int) Math.floor((double) today_step / (double) today_goal_step * 100.0);
        if(day_percent_num > 100){
            day_percent_num = 100;
        }
        week_percent.setText(week_percent_num + "%");
        day_percent.setText( day_percent_num+ "%");


        Log.e("user_idx",user_idx);
        Log.e("user_nickname",user_nickname);
        Log.e("user_status",user_status);
        Log.e("user_level",""+user_level);
        Log.e("week_goal_step",""+week_goal_step);
        Log.e("today_goal_step",""+today_goal_step);




        if(user_status.equals("도전")) {

            main_week_goal.setText("Lv."+user_goal_level+user_status);
        }else{

            main_week_goal.setText("Lv."+user_level+user_status);
        }

        if(user_profile.equals("")){
            Glide.with(this) .load(R.drawable.basic_profile) .into(main_profile_photo);
        }else{
            Glide.with(this) .load(R.drawable.stamp15_on) .into(main_profile_photo);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(check.equals("확인")){
            //여기에 동일한 걸음수를 넣어서 목표 이뤄지게
//            week_now_goal_step += 2682;
//            today_step += 2682;

            week_now_goal_step += 5372;
            today_step += 5372;

            main_today_step_progressBar.setMax(today_goal_step);
            main_today_step_progressBar.setProgress(today_step);
        }
    }
}