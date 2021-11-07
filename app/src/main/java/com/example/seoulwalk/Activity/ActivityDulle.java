package com.example.seoulwalk.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.seoulwalk.R;
import com.example.seoulwalk.adapter.Dulle1Adapter;
import com.example.seoulwalk.adapter.Dulle2Adapter;

import java.util.ArrayList;

public class ActivityDulle extends AppCompatActivity {

    Button home_btn, btn, dulle_btn;
    TextView test_text_btn; // 테스트용 텍스트뷰, 클릭하면 회원가입 창으로 감

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dulle);

        ArrayList<String> list = new ArrayList<>();
        RecyclerView dulle1 = findViewById(R.id.dulle_list_1);
        RecyclerView dulle2 = findViewById(R.id.dulle_list_2);
        dulle1.setLayoutManager(new LinearLayoutManager(this));
        dulle2.setLayoutManager(new LinearLayoutManager(this));

        Dulle1Adapter dulle1Adapter = new Dulle1Adapter(list);
        dulle1.setAdapter(dulle1Adapter);
       // Dulle2Adapter dulle2Adapter = new Dulle2Adapter(list);
      //  dulle2.setAdapter(dulle2Adapter);


        for (int i = 1; i <= 10; i++){
                    list.add("LIST1 : "+i);
        }

        dulle_btn = findViewById(R.id.dulle_btn);
        home_btn = findViewById(R.id.home_btn);
        btn = findViewById(R.id.btn);
        test_text_btn = findViewById(R.id.test_text_btn);


        //바텀 네비게이션 역할하는 버튼들 모음

        //홈 버튼 클릭 시 차트 액티비티로 이동
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //암거나 버튼 클릭 시 다른 액티비티 이동(어떤 메뉴 넣을지에 따라 이름 바꿔야함)
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ActivityMypage.class);
                startActivity(intent);
                finish();
            }
        });

        dulle_btn.setOnClickListener(new View.OnClickListener() {
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
}