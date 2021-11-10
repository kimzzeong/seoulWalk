package com.example.seoulwalk.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.seoulwalk.R;
import com.example.seoulwalk.adapter.HistoryAdapter;
import com.example.seoulwalk.adapter.StampAdapter;
import com.example.seoulwalk.adapter.StampListAdapter;
import com.example.seoulwalk.data.History_Data;
import com.example.seoulwalk.data.StampList_Data;
import com.example.seoulwalk.data.Stamp_Data;

import java.util.ArrayList;

public class ActivityStampCollection extends AppCompatActivity {

    ArrayList<StampList_Data> stampList_list = new ArrayList<>(); // 서울둘레길 8코스
    StampListAdapter stampListAdapter;
    RecyclerView stamp_recyclerview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stamp_collection);


        stamp_recyclerview = findViewById(R.id.stamp_recyclerview);
        stamp_recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));

        StampList_Data stampList_data1 = new StampList_Data("수락·불암산");
        stampList_list.add(stampList_data1);
        StampList_Data stampList_data2 = new StampList_Data("용마·아차산");
        stampList_list.add(stampList_data2);
        StampList_Data stampList_data3 = new StampList_Data("고덕·일자산");
        stampList_list.add(stampList_data3);
        StampList_Data stampList_data4 = new StampList_Data("대모·우면산");
        stampList_list.add(stampList_data4);
        StampList_Data stampList_data5 = new StampList_Data("관악산");
        stampList_list.add(stampList_data5);
        StampList_Data stampList_data6 = new StampList_Data("안양천");
        stampList_list.add(stampList_data6);
        StampList_Data stampList_data7 = new StampList_Data("봉산·앵봉산");
        stampList_list.add(stampList_data7);
        StampList_Data stampList_data8 = new StampList_Data("북한산");
        stampList_list.add(stampList_data8);

        stampListAdapter = new StampListAdapter(stampList_list);
        stamp_recyclerview.setAdapter(stampListAdapter);
    }
}