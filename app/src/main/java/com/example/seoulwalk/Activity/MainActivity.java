package com.example.seoulwalk.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.seoulwalk.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button dulle_btn, btn, home_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //커스텀 액션바 세팅
        Toolbar toolbar;
        ActionBar actionBar;
        toolbar = findViewById(R.id.main_actionbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false); //뒤로가기 아이콘 없앰


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


        //차트 하드코딩 한거
        BarChart barChart = findViewById(R.id.bar_charts);

        ArrayList<BarEntry> visitor = new ArrayList<>();
        visitor.add(new BarEntry(2014,420));
        visitor.add(new BarEntry(2015,475));
        visitor.add(new BarEntry(2016,508));
        visitor.add(new BarEntry(2017,660));
        visitor.add(new BarEntry(2018,550));
        visitor.add(new BarEntry(2019,630));
        visitor.add(new BarEntry(2020,470));

        BarDataSet barDataSet = new BarDataSet(visitor,"Visitor");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(10f);

        BarData barData = new BarData(barDataSet);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText(" ");
        barChart.animateY(1000);



    }
}