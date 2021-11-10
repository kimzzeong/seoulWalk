package com.example.seoulwalk.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.seoulwalk.R;

public class ActivityCommunity extends AppCompatActivity {

    Button course_btn, mypage_btn, home_btn, community_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);

        // 나나나나

        course_btn = findViewById(R.id.course_btn);
        home_btn = findViewById(R.id.home_btn);
        community_btn = findViewById(R.id.community_btn);
        mypage_btn = findViewById(R.id.mypage_btn);

        //홈 버튼 클릭 시 차트 액티비티로 이동
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(0,0);
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

        //커뮤니티
        community_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


}