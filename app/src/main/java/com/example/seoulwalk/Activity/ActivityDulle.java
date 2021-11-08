package com.example.seoulwalk.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.seoulwalk.R;
import com.example.seoulwalk.adapter.Dulle1Adapter;
import com.example.seoulwalk.adapter.Dulle2Adapter;

import java.util.ArrayList;

public class ActivityDulle extends AppCompatActivity {

    Button course_btn, mypage_btn, home_btn, community_btn;
    TextView test_text_btn; // 테스트용 텍스트뷰, 클릭하면 회원가입 창으로 감

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dulle);

        test_text_btn = findViewById(R.id.test_text_btn);

        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();


        for (int i = 1; i <= 10; i++){
            list1.add("LIST1 : "+i);
            list2.add("LIST2 : "+i);
        }

        RecyclerView dulle1 = findViewById(R.id.dulle_list_1);
        RecyclerView dulle2 = findViewById(R.id.dulle_list_2);
        dulle1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        dulle2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));

        Dulle1Adapter dulle1Adapter = new Dulle1Adapter(list1);
        dulle1.setAdapter(dulle1Adapter);
        Dulle2Adapter dulle2Adapter = new Dulle2Adapter(list2);
        dulle2.setAdapter(dulle2Adapter);

        dulle1Adapter.setOnItemClickListener(new Dulle1Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                //Toast.makeText(getApplicationContext(),"click1 : "+position,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),ActivityCourseInfo.class);
                startActivity(intent);
            }
        });


        dulle2Adapter.setOnItemClickListener(new Dulle2Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                //Toast.makeText(getApplicationContext(),"click2 : "+position,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),ActivityCourseInfo.class);
                startActivity(intent);
            }
        });


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

        //커뮤니티
        community_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ActivityCommunity.class);
                startActivity(intent);
                finish();
            }
        });

        //코스
        course_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        //회원가입 액티비티로 이동
        test_text_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ActivityJoin.class);
                startActivity(intent);
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