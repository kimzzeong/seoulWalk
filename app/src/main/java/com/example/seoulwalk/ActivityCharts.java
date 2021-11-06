package com.example.seoulwalk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class ActivityCharts extends AppCompatActivity {

    Button home_btn, btn, charts_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charts);

        charts_btn = findViewById(R.id.charts_btn);
        home_btn = findViewById(R.id.home_btn);
        btn = findViewById(R.id.btn);

//여기부터 바텀 네비게이션 역할하는 버튼들 모음-----------------------------------------------------------
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

        charts_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
//여기까지-------------------------------------------------------------------------------------------

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
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Bar Charts Example");
        barChart.animateY(2000);
    }
}