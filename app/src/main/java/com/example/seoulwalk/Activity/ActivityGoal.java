package com.example.seoulwalk.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.seoulwalk.R;

import java.util.ArrayList;

public class ActivityGoal extends AppCompatActivity {

    LinearLayout goal_maintenance_layout, goal_step_up_layout;
    boolean goal_layout_flag = false;
    Button goal_maintenance_btn, goal_step_up_btn;
    int user_level = 2; //현재 유저의 레벨
    ArrayList<String> items = new ArrayList<>();
    TextView level_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);

        //커스텀 액션바 세팅
        Toolbar toolbar;
        ActionBar actionBar;
        toolbar = findViewById(R.id.goal_actionbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false); //뒤로가기 아이콘 없앰

        //탭 레이아웃처럼 변환 위한 정의
        goal_maintenance_layout = findViewById(R.id.goal_maintenance_layout);
        goal_step_up_layout = findViewById(R.id.goal_step_up_layout);
        goal_maintenance_btn = findViewById(R.id.goal_maintenance_btn);
        goal_step_up_btn = findViewById(R.id.goal_step_up_btn);
        level_text = findViewById(R.id.level_text);

        goal_maintenance_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goal_layout_flag = false;
                layoutChange(goal_layout_flag);
            }
        });

        goal_step_up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goal_layout_flag = true;
                layoutChange(goal_layout_flag);
            }
        });

        items.add("Lv 1"); //0 포지션값임
        items.add("Lv 2"); //1
        items.add("Lv 3"); //2
        items.add("Lv 4"); //3
        items.add("Lv 5"); //4


        for (int i = 0; i < user_level; i++){

            Log.e("items"+i, items.get(i));
            items.remove(0);
        }

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
              //  Log.e("Log",items.get(position));
                if(position == 0){
                    level_text.setText("Lv.3 도전 한다면?");
                }else if(position == 1){
                    level_text.setText("Lv.4 도전 한다면?");
                }else{
                    level_text.setText("Lv.5 도전 한다면?");
                }
            }

            // 아무것도 선택되지 않은 상태일 때
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.e("Log","ㅎㅇㅎㅇ");
            }
        });



    }

    //레이아웃 변환(탭 레이아웃처럼)
    private void layoutChange(boolean goal_layout_flag) {

        if(!goal_layout_flag){
            goal_maintenance_layout.setVisibility(View.VISIBLE);
            goal_step_up_layout.setVisibility(View.GONE);
        }else{
            goal_maintenance_layout.setVisibility(View.GONE);
            goal_step_up_layout.setVisibility(View.VISIBLE);
        }
    }
}