package com.example.seoulwalk.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.seoulwalk.R;
import com.example.seoulwalk.adapter.HistoryAdapter;
import com.example.seoulwalk.data.History_Data;

import java.util.ArrayList;

public class ActivityMypage extends AppCompatActivity {

    Button course_btn, mypage_btn, home_btn, community_btn;

    ArrayList<History_Data> history_list = new ArrayList<>();
    HistoryAdapter historyAdapter;
    RecyclerView history;
    ImageView mypage_profile_photo; // 프로필사진

    //도장 컬렉션으로 가는 레이아웃
    LinearLayout dullegil_stamp_collection;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private static final String SHARED_PREF_NAME = "mypref";
    String user_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        course_btn = findViewById(R.id.course_btn);
        home_btn = findViewById(R.id.home_btn);
        community_btn = findViewById(R.id.community_btn);
        mypage_btn = findViewById(R.id.mypage_btn);
        mypage_profile_photo = findViewById(R.id.mypage_profile_photo);


        history = findViewById(R.id.walking_history_list);
        history.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));

        History_Data history_data1 = new History_Data("서울둘레길 1-1코스","2021-11-01 20:01:06","후기\n보기");
        history_list.add(history_data1);
        History_Data history_data2 = new History_Data("서울둘레길 2-1코스","2021-11-02 20:01:06","후기\n보기");
        history_list.add(history_data2);
        History_Data history_data3 = new History_Data("서울둘레길 3-1코스","2021-11-03 20:01:06","후기\n작성");
        history_list.add(history_data3);
        History_Data history_data4 = new History_Data("서울둘레길 4-1코스","2021-11-04 20:01:06","후기\n보기");
        history_list.add(history_data4);
        History_Data history_data5 = new History_Data("서울둘레길 5-1코스","2021-11-05 20:01:06","후기\n작성");
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

    @Override
    protected void onResume() {
        super.onResume();
        sharedPreferences =getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        editor = sharedPreferences.edit();
        user_profile = sharedPreferences.getString("user_profile","");
        if(user_profile.equals("")){
            Glide.with(this) .load(R.drawable.basic_profile) .into(mypage_profile_photo);
        }else{
            Glide.with(this) .load(R.drawable.stamp14_on) .into(mypage_profile_photo);
        }
    }

}