package com.example.seoulwalk.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.seoulwalk.R;

public class ActivityMypage extends AppCompatActivity {

    Button home_btn, dulle_btn,btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        dulle_btn = findViewById(R.id.dulle_btn);
        home_btn = findViewById(R.id.home_btn);
        btn = findViewById(R.id.btn);

        //둘레길 버튼 클릭 시 차트 액티비티로 이동
        dulle_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ActivityDulle.class);
                startActivity(intent);
                finish();
            }
        });

        //홈 버튼 클릭 시 차트 액티비티로 이동
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}