package com.example.seoulwalk.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.seoulwalk.R;
import com.example.seoulwalk.adapter.HistoryAdapter;
import com.example.seoulwalk.adapter.StampAdapter;
import com.example.seoulwalk.data.History_Data;
import com.example.seoulwalk.data.Stamp_Data;

import java.util.ArrayList;

public class ActivityStampCollection extends AppCompatActivity {

    ArrayList<Stamp_Data> stamp_list = new ArrayList<>();
    StampAdapter stampAdapter;
    RecyclerView stamp_recyclerview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stamp_collection);


        stamp_recyclerview = findViewById(R.id.stamp_recyclerview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        stamp_recyclerview.setLayoutManager(gridLayoutManager);

        Stamp_Data stamp_data1 = new Stamp_Data(R.drawable.seoul_1,"1");
        stamp_list.add(stamp_data1);
        Stamp_Data stamp_data2 = new Stamp_Data(R.drawable.seoul_2,"2");
        stamp_list.add(stamp_data2);
        Stamp_Data stamp_data3 = new Stamp_Data(R.drawable.seoul_3,"3");
        stamp_list.add(stamp_data3);
        Stamp_Data stamp_data4 = new Stamp_Data(R.drawable.seoul_4,"4");
        stamp_list.add(stamp_data4);
        Stamp_Data stamp_data5 = new Stamp_Data(R.drawable.seoul_5,"5");
        stamp_list.add(stamp_data5);

        stampAdapter = new StampAdapter(stamp_list);
        stamp_recyclerview.setAdapter(stampAdapter);
    }
}