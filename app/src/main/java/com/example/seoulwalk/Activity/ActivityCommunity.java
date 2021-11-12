package com.example.seoulwalk.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.seoulwalk.R;
import com.example.seoulwalk.data.CommunityData;
import java.util.ArrayList;

public class ActivityCommunity extends AppCompatActivity {

    Button course_btn, mypage_btn, home_btn, community_btn;
    RecyclerView community_list;
    //CommunityAdapter communityAdapter;
    Spinner community_spinner;
    ArrayList<CommunityData> list = new ArrayList<>();
    ImageView create_post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);



        course_btn = findViewById(R.id.course_btn);
        home_btn = findViewById(R.id.home_btn);
        community_btn = findViewById(R.id.community_btn);
        mypage_btn = findViewById(R.id.mypage_btn);
        community_list = findViewById(R.id.community_list);
        community_spinner = findViewById(R.id.community_spinner);
        create_post = findViewById(R.id.create_post);

        create_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ActivityWrite.class);
                startActivity(intent);
            }
        });

//        community_list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
//
//        for (int i = 1; i <= 10; i++){
//            CommunityData communityData;
//            if(i%2 == 1){
//                communityData = new CommunityData("제목"+i,"내용"+i,"https://i.imgur.com/36Bivob.jpeg","닉네임"+i,"날짜"+i,"사용자"+i);
//            }else{
//                communityData = new CommunityData("제목"+i,"내용"+i,"","닉네임"+i,"날짜"+i,"사용자"+i);
//            }
//            list.add(communityData);
//        }
//
//        communityAdapter = new CommunityAdapter(list,this);
//        community_list.setAdapter(communityAdapter);

        ArrayAdapter<String> spinner_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,(String[])getResources().getStringArray(R.array.communitySpinner));
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        community_spinner.setAdapter(spinner_adapter);
        community_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) { //선택됐을 때
                Log.e("Spinner",""+position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { //선택이 안됐을 때

            }
        });

//        communityAdapter.setOnItemClickListener(new CommunityAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View v, int position) {
//                Intent intent = new Intent(getApplicationContext(),ActivityCommunityInfo.class);
//                startActivity(intent);
//            }
//        });

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