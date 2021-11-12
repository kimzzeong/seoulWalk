package com.example.seoulwalk.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.seoulwalk.R;
import com.example.seoulwalk.adapter.HistoryAdapter;
import com.example.seoulwalk.data.History_Data;

import java.util.ArrayList;

public class ActivityMypage extends AppCompatActivity {

    Button course_btn, mypage_btn, home_btn, community_btn;

    ArrayList<History_Data> history_list = new ArrayList<>();
    HistoryAdapter historyAdapter;
    RecyclerView history;

    //도장 컬렉션으로 가는 레이아웃
    LinearLayout dullegil_stamp_collection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        course_btn = findViewById(R.id.course_btn);
        home_btn = findViewById(R.id.home_btn);
        community_btn = findViewById(R.id.community_btn);
        mypage_btn = findViewById(R.id.mypage_btn);

        history = findViewById(R.id.walking_history_list);
        history.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));

        History_Data history_data1 = new History_Data(
                "서울둘레길 5-1코스",
                "2021-11-13 05:20",
                "관악산코스",
                "2시간 40분",
                "5.7km",
                "477kcal",
                "2.91km/h");
        history_list.add(history_data1);

        History_Data history_data2 = new History_Data(
                "서울둘레길 2-2코스",
                "2021-11-08 15:33",
                "용마·아차산코스",
                "2시간 8분",
                "4.3km",
                "340kcal",
                "3.05km/h");
        history_list.add(history_data2);

        History_Data history_data3 = new History_Data(
                "서울둘레길 2-1코스",
                "2021-11-03 17:48",
                "용마·아차산코스",
                "4시간 10분",
                "8.1km",
                "620kcal",
                "2.78km/h");
        history_list.add(history_data3);

        History_Data history_data4 = new History_Data(
                "서울둘레길 6-2코스",
                "2021-10-27 18:30",
                "안양천코스",
                "2시간 30분",
                "10.4km",
                "320kcal",
                "3.26km/h");
        history_list.add(history_data4);

        History_Data history_data5 = new History_Data(
                "서울둘레길 6-1코스",
                "2021-10-20 14:00",
                "안양천코스",
                "1시간 50분",
                "7.8km",
                "260kcal",
                "3.58km/h");
        history_list.add(history_data5);

        historyAdapter = new HistoryAdapter(history_list);
        history.setAdapter(historyAdapter);

        //도장 컬렉션
        dullegil_stamp_collection = findViewById(R.id.dullegil_stamp_collection);
        dullegil_stamp_collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ActivityStampCollection.class);
                startActivity(intent);
            }
        });

        //홈
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

    }

}