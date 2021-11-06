package com.example.seoulwalk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button charts_btn, btn, home_btn;
    TextView test_text_btn; // 테스트용 텍스트뷰, 클릭하면 회원가입 창으로 감

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        charts_btn = findViewById(R.id.charts_btn);
        home_btn = findViewById(R.id.home_btn);
        btn = findViewById(R.id.btn);
        test_text_btn = findViewById(R.id.test_text_btn);

        //차트 버튼 클릭 시 차트 액티비티로 이동
        charts_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ActivityCharts.class);
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

        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        test_text_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ActivityJoin.class);
                startActivity(intent);
            }
        });

    }
}