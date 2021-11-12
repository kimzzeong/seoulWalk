package com.example.seoulwalk.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.seoulwalk.R;
import com.example.seoulwalk.adapter.CommunityAdapter;
import com.example.seoulwalk.data.CommunityData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ActivityCommunity extends AppCompatActivity {

    Button course_btn, mypage_btn, home_btn, community_btn;
    RecyclerView community_list;
    CommunityAdapter communityAdapter;
    Spinner community_spinner;
    ArrayList<CommunityData> list = new ArrayList<>();
    ImageView create_post;
    Gson gson;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private static final String SHARED_PREF_NAME = "mypref";
    public static ActivityCommunity activityCommunity;


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

        community_list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        sharedPreferences =getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        editor = sharedPreferences.edit();

        Type type = new TypeToken<ArrayList<CommunityData>>() {}.getType();
        gson = new Gson();
        if(!sharedPreferences.getString("POST","").equals("")){
            list = gson.fromJson(sharedPreferences.getString("POST",""),type);
            //Log.e("post_lost",""+list.size());
            Collections.sort(list,Collections.reverseOrder());
            //Log.e("post_lost",""+list.size());
        }

        communityAdapter = new CommunityAdapter(list,this);
        community_list.setAdapter(communityAdapter);


        ArrayAdapter<String> spinner_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,(String[])getResources().getStringArray(R.array.communitySpinner));
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        community_spinner.setAdapter(spinner_adapter);
        community_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) { //선택됐을 때
                Log.e("Spinner",""+list.size());

                ArrayList<CommunityData> sub_list = new ArrayList<>();
                if(position == 0){
//                    list = gson.fromJson(sharedPreferences.getString("POST",""),type);
//                    Collections.sort(list,Collections.reverseOrder());
                    communityAdapter = new CommunityAdapter(list,ActivityCommunity.this);
                    community_list.setAdapter(communityAdapter);

                }else if(position == 1){
                    for (int i = 0; i < list.size(); i++){
                        CommunityData data = list.get(i);
                        if(data.getWrite_spinner().equals("후기글")){
                            sub_list.add(data);
                        }

                        Collections.sort(sub_list,Collections.reverseOrder());
                        communityAdapter = new CommunityAdapter(sub_list,ActivityCommunity.this);
                        community_list.setAdapter(communityAdapter);
                    }

                }else if(position == 2){
                    for (int i = 0; i < list.size(); i++){
                        CommunityData data = list.get(i);
                        if(data.getWrite_spinner().equals("인증글")){
                            sub_list.add(data);
                        }

                        Collections.sort(sub_list,Collections.reverseOrder());
                        communityAdapter = new CommunityAdapter(sub_list,ActivityCommunity.this);
                        community_list.setAdapter(communityAdapter);
                    }

                }else if(position == 3){
                    for (int i = 0; i < list.size(); i++){
                        CommunityData data = list.get(i);
                        if(data.getWrite_spinner().equals("정보공유글")){
                            sub_list.add(data);
                        }
                        Collections.sort(sub_list,Collections.reverseOrder());
                        communityAdapter = new CommunityAdapter(sub_list,ActivityCommunity.this);
                        community_list.setAdapter(communityAdapter);
                    }

                }else {
                    for (int i = 0; i < list.size(); i++){
                        CommunityData data = list.get(i);
                        if(data.getWrite_spinner().equals("자유글")){
                            sub_list.add(data);
                        }

                        Collections.sort(sub_list,Collections.reverseOrder());
                        communityAdapter = new CommunityAdapter(sub_list,ActivityCommunity.this);
                        community_list.setAdapter(communityAdapter);
                    }

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { //선택이 안됐을 때

            }
        });

        communityAdapter.setOnItemClickListener(new CommunityAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(),ActivityCommunityInfo.class);
                startActivity(intent);
            }
        });

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