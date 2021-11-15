package com.example.seoulwalk.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.seoulwalk.R;

public class ActivityPoint extends AppCompatActivity {

    Button myPoint_btn,store_btn;
    boolean goal_layout_flag = false;
    RecyclerView mypoint_recyclerview;

    ConstraintLayout mypoint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point);

        myPoint_btn = findViewById(R.id.myPoint_btn);
        store_btn = findViewById(R.id.store_btn);
        mypoint = findViewById(R.id.mypoint);
        mypoint_recyclerview = findViewById(R.id.mypoint_recyclerview);

        myPoint_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goal_layout_flag = false;
                layoutChange(goal_layout_flag);
            }
        });

        store_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goal_layout_flag = true;
                layoutChange(goal_layout_flag);
            }
        });

    }

    //레이아웃 변환(탭 레이아웃처럼)
    private void layoutChange(boolean goal_layout_flag) {

//        if(!goal_layout_flag){
//            mypoint_recyclerview.setAdapter();
//        }else{
//            mypoint_recyclerview.setAdapter();
//        }
    }
}