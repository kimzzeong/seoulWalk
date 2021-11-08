package com.example.seoulwalk.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.seoulwalk.R;

public class ActivityGoal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);

        String[] items = {"Lv 1", "Lv 2", "Lv 3", "Lv 4", "Lv 5"};

        //커스텀 액션바 세팅
        Toolbar toolbar;
        ActionBar actionBar;
        toolbar = findViewById(R.id.goal_actionbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false); //뒤로가기 아이콘 없앰

        Spinner spinner_level = findViewById(R.id.spinner_level);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                //API에 만들어져 있는 R.layout.simple_spinner...를 씀
                this,android.R.layout.simple_spinner_item, items
        );
        //미리 정의된 레이아웃 사용
        adapter.setDropDownViewResource(R.layout.spinner_item);
        // 스피너 객체에다가 어댑터를 넣어줌
        spinner_level.setAdapter(adapter);
        spinner_level.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            // 선택되면
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("Log",items[position]);
            }

            // 아무것도 선택되지 않은 상태일 때
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.e("Log","ㅎㅇㅎㅇ");
            }
        });



    }
}