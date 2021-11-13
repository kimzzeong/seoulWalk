package com.example.seoulwalk.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.seoulwalk.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ActivityResultAfterWalk extends AppCompatActivity {

    TextView textViewCourseName, textViewDateTime, textViewTime, textViewCalorie, textViewSpeed, textViewDistance;
    Button buttonOkay;
ImageView img_dulle_result;
SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    long mNow;
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_after_walk);


        /** 길찾기에서 넘어온 값 */
        Intent intent = getIntent();


        sharedPreferences= getSharedPreferences("dulle_result", MODE_PRIVATE);    // test 이름의 기본모드 설정
        editor= sharedPreferences.edit(); //sharedPreferences를 제어할 editor를 선언

        String textViewCourseName1 = sharedPreferences.getString("textViewCourseName","");
        // 걸은 코스이름
        textViewCourseName = findViewById(R.id.course_name_result_after_walk);
        // 걸은 날짜, 시간
        textViewDateTime = findViewById(R.id.datetime_result_after_walk);
        // 소요시간
        textViewTime = findViewById(R.id.time_result_after_walk);
        String time  = sharedPreferences.getString("time_results","");
        System.out.println("time 이비나입니디아닙다" + time);
        String courseNameString;
        String time_results;

        if (intent.getStringExtra("time_results") == null){
            textViewTime.setText(time);
            textViewCourseName.setText(textViewCourseName1);

            time_results = time;
            courseNameString = textViewCourseName1;

        }else {
             courseNameString = intent.getStringExtra("courseNameString");
             time_results= intent.getStringExtra("time_results");



            textViewCourseName.setText(courseNameString);

            textViewTime.setText(time_results);

        }

        // 소모 칼로리
        textViewCalorie = findViewById(R.id.calorie_result_after_walk);
        textViewCalorie.setText("15kcal");
        // 평균 속도
        textViewSpeed = findViewById(R.id.speed_result_after_walk);
        textViewSpeed.setText("2.1km/h");
        // 이동 거리
        textViewDistance = findViewById(R.id.distance_result_after_walk);
        textViewDistance.setText("0.5km");
        // 맨 밑에 확인 버튼
        buttonOkay = findViewById(R.id.button_okay_result_after_walk);

        Log.e("쉐어드 시간",time);



        buttonOkay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 버튼 누르면 마이페이지로 이동해서 하드코딩 된 걷기내역 보여주기
                editor.putString("textViewCourseName",courseNameString);
                editor.putString("time_results",time_results);
                editor.putString("cal","15kcal");
                editor.putString("km/h","2.1km/h");
                editor.putString("walk_distance","0.5km");
                editor.putString("date",getTime());
                // key,value 형식으로 저장
                editor.commit();    //최종 커밋. 커밋을 해야 저장이 된다.

                startActivity(new Intent(ActivityResultAfterWalk.this, ActivityMypage.class));
            }
        });
    }
    private String getTime() {
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }
}