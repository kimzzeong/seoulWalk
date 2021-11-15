package com.example.seoulwalk.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.seoulwalk.R;
import com.example.seoulwalk.adapter.HistoryAdapter;
import com.example.seoulwalk.adapter.PointAdapter;
import com.example.seoulwalk.adapter.StoreAdapter;
import com.example.seoulwalk.data.Point_Data;
import com.example.seoulwalk.data.PostData;
import com.example.seoulwalk.data.Store_Data;

import java.util.ArrayList;

public class ActivityPoint extends AppCompatActivity {

    Button myPoint_btn,store_btn;
    boolean goal_layout_flag = false;
    RecyclerView mypoint_recyclerview, pointstore_recyclerview;
    ArrayList<Point_Data> mData = new ArrayList<>();
    ArrayList<Store_Data> mData2 = new ArrayList<>();
    PointAdapter pointAdapter;
    StoreAdapter storeAdapter;

    ConstraintLayout mypoint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point);

        myPoint_btn = findViewById(R.id.myPoint_btn);
        store_btn = findViewById(R.id.store_btn);
        mypoint = findViewById(R.id.mypoint);
        mypoint_recyclerview = findViewById(R.id.mypoint_recyclerview);
        pointstore_recyclerview = findViewById(R.id.pointstore_recyclerview);

        Point_Data point_data1 = new Point_Data("하루 목표 달성!","40 포인트","2021-08-26");
        mData.add(point_data1);
        Point_Data point_data2 = new Point_Data("하루 목표 달성!","40 포인트","2021-08-27");
        mData.add(point_data2);
        Point_Data point_data3 = new Point_Data("하루 목표 달성!","40 포인트","2021-08-28");
        mData.add(point_data3);
        Point_Data point_data4 = new Point_Data("하루 목표 달성!","40 포인트","2021-08-29");
        mData.add(point_data4);
        Point_Data point_data5 = new Point_Data("하루 목표 달성!","40 포인트","2021-08-30");
        mData.add(point_data5);
        Point_Data point_data6 = new Point_Data("하루 목표 달성!","40 포인트","2021-08-31");
        mData.add(point_data6);
        Point_Data point_data7 = new Point_Data("하루 목표 달성!","40 포인트","2021-09-01");
        mData.add(point_data7);
        Point_Data point_data8 = new Point_Data("주간 목표 달성!","220 포인트","2021-09-02");
        mData.add(point_data8);
        Point_Data point_data9 = new Point_Data("하루 목표 달성!","40 포인트","2021-09-03");
        mData.add(point_data9);
        Point_Data point_data10 = new Point_Data("하루 목표 달성!","40 포인트","2021-09-04");
        mData.add(point_data10);
        Point_Data point_data11 = new Point_Data("하루 목표 달성!","40 포인트","2021-09-05");
        mData.add(point_data11);
        Point_Data point_data12 = new Point_Data("하루 목표 달성!","40 포인트","2021-09-06");
        mData.add(point_data12);
        Point_Data point_data13 = new Point_Data("하루 목표 달성!","40 포인트","2021-09-07");
        mData.add(point_data13);
        Point_Data point_data14 = new Point_Data("하루 목표 달성!","40 포인트","2021-09-08");
        mData.add(point_data14);
        Point_Data point_data15 = new Point_Data("하루 목표 달성!","40 포인트","2021-09-09");
        mData.add(point_data15);
        Point_Data point_data16 = new Point_Data("하루 목표 달성!","220 포인트","2021-09-10");
        mData.add(point_data16);

        mypoint_recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));

        pointAdapter = new PointAdapter(mData,ActivityPoint.this);
        mypoint_recyclerview.setAdapter(pointAdapter);

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
        mData = new ArrayList<>();
        if(!goal_layout_flag){

            pointstore_recyclerview.setVisibility(View.GONE);
            mypoint_recyclerview.setVisibility(View.VISIBLE);
            Point_Data point_data1 = new Point_Data("하루 목표 달성!","40 포인트","2021-08-26");
            mData.add(point_data1);
            Point_Data point_data2 = new Point_Data("하루 목표 달성!","40 포인트","2021-08-27");
            mData.add(point_data2);
            Point_Data point_data3 = new Point_Data("하루 목표 달성!","40 포인트","2021-08-28");
            mData.add(point_data3);
            Point_Data point_data4 = new Point_Data("하루 목표 달성!","40 포인트","2021-08-29");
            mData.add(point_data4);
            Point_Data point_data5 = new Point_Data("하루 목표 달성!","40 포인트","2021-08-30");
            mData.add(point_data5);
            Point_Data point_data6 = new Point_Data("하루 목표 달성!","40 포인트","2021-08-31");
            mData.add(point_data6);
            Point_Data point_data7 = new Point_Data("하루 목표 달성!","40 포인트","2021-09-01");
            mData.add(point_data7);
            Point_Data point_data8 = new Point_Data("주간 목표 달성!","220 포인트","2021-09-02");
            mData.add(point_data8);
            Point_Data point_data9 = new Point_Data("하루 목표 달성!","40 포인트","2021-09-03");
            mData.add(point_data9);
            Point_Data point_data10 = new Point_Data("하루 목표 달성!","40 포인트","2021-09-04");
            mData.add(point_data10);
            Point_Data point_data11 = new Point_Data("하루 목표 달성!","40 포인트","2021-09-05");
            mData.add(point_data11);
            Point_Data point_data12 = new Point_Data("하루 목표 달성!","40 포인트","2021-09-06");
            mData.add(point_data12);
            Point_Data point_data13 = new Point_Data("하루 목표 달성!","40 포인트","2021-09-07");
            mData.add(point_data13);
            Point_Data point_data14 = new Point_Data("하루 목표 달성!","40 포인트","2021-09-08");
            mData.add(point_data14);
            Point_Data point_data15 = new Point_Data("하루 목표 달성!","40 포인트","2021-09-09");
            mData.add(point_data15);
            Point_Data point_data16 = new Point_Data("하루 목표 달성!","220 포인트","2021-09-10");
            mData.add(point_data16);

            mypoint_recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));

            pointAdapter = new PointAdapter(mData,ActivityPoint.this);
            mypoint_recyclerview.setAdapter(pointAdapter);
        }else{

            mypoint_recyclerview.setVisibility(View.GONE);
            pointstore_recyclerview.setVisibility(View.VISIBLE);

            Store_Data store_data1 = new Store_Data("닭상점","https://img.etimg.com/thumb/width-1200,height-900,imgsize-122620,resizemode-1,msid-75214721/industry/services/retail/future-group-negotiates-rents-for-its-1700-stores.jpg","4.9","닭가슴살");
            mData2.add(store_data1);
            Store_Data store_data2 = new Store_Data("단백질상점","https://img.etimg.com/thumb/width-1200,height-900,imgsize-122620,resizemode-1,msid-75214721/industry/services/retail/future-group-negotiates-rents-for-its-1700-stores.jpg","3.5","단백질 쉐이크");
            mData2.add(store_data2);
            Store_Data store_data3 = new Store_Data("보호대상점","https://img.etimg.com/thumb/width-1200,height-900,imgsize-122620,resizemode-1,msid-75214721/industry/services/retail/future-group-negotiates-rents-for-its-1700-stores.jpg","4.2","무릎보호대");
            mData2.add(store_data3);
            Store_Data store_data4 = new Store_Data("헤어밴드상점","https://img.etimg.com/thumb/width-1200,height-900,imgsize-122620,resizemode-1,msid-75214721/industry/services/retail/future-group-negotiates-rents-for-its-1700-stores.jpg","4.0","헤어밴드");
            mData2.add(store_data4);
            Store_Data store_data5 = new Store_Data("런닝화상점","https://img.etimg.com/thumb/width-1200,height-900,imgsize-122620,resizemode-1,msid-75214721/industry/services/retail/future-group-negotiates-rents-for-its-1700-stores.jpg","2.1","런닝화");
            mData2.add(store_data5);
            Store_Data store_data6 = new Store_Data("스포츠의류상점","https://img.etimg.com/thumb/width-1200,height-900,imgsize-122620,resizemode-1,msid-75214721/industry/services/retail/future-group-negotiates-rents-for-its-1700-stores.jpg","5.0","스포츠의류");
            mData2.add(store_data6);
            Store_Data store_data7 = new Store_Data("곤약상점","https://img.etimg.com/thumb/width-1200,height-900,imgsize-122620,resizemode-1,msid-75214721/industry/services/retail/future-group-negotiates-rents-for-its-1700-stores.jpg","4.7","곤약");
            mData2.add(store_data7);
            Store_Data store_data8 = new Store_Data("물상점","https://img.etimg.com/thumb/width-1200,height-900,imgsize-122620,resizemode-1,msid-75214721/industry/services/retail/future-group-negotiates-rents-for-its-1700-stores.jpg","4.3","물");
            mData2.add(store_data8);
            pointstore_recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));

            storeAdapter = new StoreAdapter(mData2,ActivityPoint.this);
            pointstore_recyclerview.setAdapter(storeAdapter);
        }
    }
}