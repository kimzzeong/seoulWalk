package com.example.seoulwalk.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.seoulwalk.R;
import com.example.seoulwalk.adapter.WeeklyStepCountAdapter;
import com.example.seoulwalk.model.StepCount;
import com.example.seoulwalk.retrofit.ApiClient;
import com.example.seoulwalk.retrofit.ApiInterface;
import com.example.seoulwalk.retrofit.Dulle_Name;
import com.example.seoulwalk.util.UserInfo;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ActivityMyExerciseInfo extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();

    BarChart barChartStepCount;
    LineChart lineChartLevel;
    Button beforeWeekBtn, nextWeekBtn;
    RecyclerView recyclerViewWeeklyStepCount;
    WeeklyStepCountAdapter weeklyStepCountAdapter;
    List<StepCount> stepCountList = new ArrayList<>();
    TextView stepCountStartDate, stepCountEndDate;
    
    int week_num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_exercise_info);

        stepCountStartDate = findViewById(R.id.my_exercise_info_step_count_start_date);
        stepCountEndDate = findViewById(R.id.my_exercise_info_step_count_end_date);

        barChartStepCount = (BarChart) findViewById(R.id.my_exercise_info_barchart_step_count);

        lineChartLevel = (LineChart) findViewById(R.id.my_exercise_info_linechart_level);
        LineDataSet lineDataSet1 = new LineDataSet(dataValues1(), "레벨 변화 양상");
        int colorBlack = ContextCompat.getColor(getApplicationContext(), R.color.black);
        int colorRed = ContextCompat.getColor(getApplicationContext(), R.color.red);
        lineDataSet1.setColor(colorBlack);
        lineDataSet1.setCircleColor(colorRed);
        lineDataSet1.setLineWidth(2f);
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet1);

        LineData lineData = new LineData(dataSets);
        lineChartLevel.setData(lineData);
        lineChartLevel.setTouchEnabled(false);

        YAxis rightYAxis = lineChartLevel.getAxisRight();
        rightYAxis.setEnabled(false);
        rightYAxis.setDrawGridLines(false);

        YAxis leftYAxis = lineChartLevel.getAxisLeft();
//        leftYAxis.setLabelCount(5);
        leftYAxis.setGranularity(1.0f);
        leftYAxis.setGranularityEnabled(true);
        leftYAxis.setTextSize(16f);

        lineChartLevel.invalidate();

        recyclerViewWeeklyStepCount = findViewById(R.id.recyclerview_my_exercise_info);
        recyclerViewWeeklyStepCount.setHasFixedSize(true);
        recyclerViewWeeklyStepCount.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewWeeklyStepCount.setVisibility(View.GONE);
        
        fetchWeeklyStepCount(UserInfo.USER_ID, week_num);
        
        beforeWeekBtn = findViewById(R.id.button_before_week_my_exercise_info);
        nextWeekBtn = findViewById(R.id.button_next_week_my_exercise_info);

        beforeWeekBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!nextWeekBtn.isEnabled()) {
                    nextWeekBtn.setEnabled(true);
                }

                week_num++;
//                Toast.makeText(ActivityMyExerciseInfo.this, String.valueOf(week_num), Toast.LENGTH_SHORT).show();

                fetchWeeklyStepCount(UserInfo.USER_ID, week_num);
            }
        });

        nextWeekBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!beforeWeekBtn.isEnabled()) {
                    beforeWeekBtn.setEnabled(true);
                }

                if (week_num == 0) {
//                    Toast.makeText(ActivityMyExerciseInfo.this, "더이상 클릭할 수 없습니다.", Toast.LENGTH_SHORT).show();
                    nextWeekBtn.setEnabled(false);
                } else {
                    week_num--;
//                    Toast.makeText(ActivityMyExerciseInfo.this, String.valueOf(week_num), Toast.LENGTH_SHORT).show();

                    fetchWeeklyStepCount(UserInfo.USER_ID, week_num);
                }
            }
        });
    }

    private ArrayList<Entry> dataValues1() {
        ArrayList<Entry> dataVals = new ArrayList<Entry>();
        dataVals.add(new Entry(1, 2));
        dataVals.add(new Entry(2, 2));
        dataVals.add(new Entry(3, 1));
        dataVals.add(new Entry(4, 2));
        dataVals.add(new Entry(5, 3));
        dataVals.add(new Entry(6, 3));
        dataVals.add(new Entry(7, 4));
        dataVals.add(new Entry(8, 5));

        return dataVals;
    }
//
//    private void drawLevelLineChart() {
//
//        ArrayList<Entry> values = new ArrayList<>();
//
//        for (int i = 0; i < 10; i++) {
//
//            float val = (float) (Math.random() * 10);
//            values.add(new Entry(i, val));
//        }
//
//        LineDataSet set1;
//        set1 = new LineDataSet(values, "DataSet 1");
//
//        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
//        dataSets.add(set1); // add the data sets
//
//        // create a data object with the data sets
//        LineData data = new LineData(dataSets);
//
//        // black lines and points
//        set1.setColor(Color.BLACK);
//        set1.setCircleColor(Color.BLACK);
//
//        // set data
//        chart.setData(data);
//    }

    private void fetchWeeklyStepCount(String userid, int week_num) {
        Log.d(TAG, "fetchWeeklyStepCount() : week = " + week_num);
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<StepCount>> call = apiInterface.fetchWeeklyStepCount(userid, week_num);
        call.enqueue(new Callback<List<StepCount>>() {
            @Override
            public void onResponse(@NonNull Call<List<StepCount>> call, @NonNull Response<List<StepCount>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d(TAG, "fetchWeeklyStepCount() onResponse(): successful and not null");
                    Log.d(TAG, "fetchWeeklyStepCount() response.body(): " + response.body());
                    Log.d(TAG, "fetchWeeklyStepCount() response: " + response);
                    parseFetchedWeeklyStepCount(response.body());
                } else {
                    Log.d(TAG, "fetchWeeklyStepCount() onResponse() : not successful");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<StepCount>> call, @NonNull Throwable t) {
                Log.d(TAG, "fetchWeeklyStepCount(): onFailure : " + t.getMessage());
            }
        });
    }

    private void parseFetchedWeeklyStepCount(List<StepCount> lists) {
        Log.d(TAG, "parseFetchedWeeklyStepCount()");
//        weeklyStepCountAdapter = new WeeklyStepCountAdapter(ActivityMyExerciseInfo.this, lists);
//        recyclerViewWeeklyStepCount.setAdapter(weeklyStepCountAdapter);
//        Log.e(TAG, "setAdapter()");
//        weeklyStepCountAdapter.notifyDataSetChanged();
//
//        stepCountList = lists;

        int listSize = lists.size();

        if (listSize > 0) {

            weeklyStepCountAdapter = new WeeklyStepCountAdapter(ActivityMyExerciseInfo.this, lists);
            recyclerViewWeeklyStepCount.setAdapter(weeklyStepCountAdapter);
            Log.e(TAG, "setAdapter()");
            weeklyStepCountAdapter.notifyDataSetChanged();

            stepCountList = lists;

            stepCountStartDate.setText(lists.get(0).getDate());
            stepCountEndDate.setText(lists.get(listSize - 1).getDate());

//        ArrayList<String> days = new ArrayList<>();
            ArrayList<Integer> days = new ArrayList<>();
            ArrayList<Integer> stepCounts = new ArrayList<>();

            List<String> xAxisValues = new ArrayList<>(Arrays.asList("", "일", "월", "화", "수", "목", "금", "토"));

            // 주별 각각 날짜를 요일로 변환해서 days arraylist 에 저장하기
            for (int i = 0; i < listSize; i++) {
                Date date = null;
                try {
                    date = new SimpleDateFormat("yyyy-MM-dd").parse(lists.get(i).getDate());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
//            days.add(getDayOfWeek(calendar.get(Calendar.DAY_OF_WEEK)));
                days.add(calendar.get(Calendar.DAY_OF_WEEK));

                stepCounts.add(lists.get(i).getSteps());
            }

            Log.e(TAG, "days: " + days);
            Log.e(TAG, "stepCounts: " + stepCounts);

            ArrayList<BarEntry> stepInfo = new ArrayList<>();

            for (int i = 0; i < listSize; i++) {
                stepInfo.add(new BarEntry(days.get(i), stepCounts.get(i)));
            }

            if (!days.contains(1)) {
                stepInfo.add(new BarEntry(1, 0));
            }
            if (!days.contains(2)) {
                stepInfo.add(new BarEntry(2, 0));
            }
            if (!days.contains(3)) {
                stepInfo.add(new BarEntry(3, 0));
            }
            if (!days.contains(4)) {
                stepInfo.add(new BarEntry(4, 0));
            }
            if (!days.contains(5)) {
                stepInfo.add(new BarEntry(5, 0));
            }
            if (!days.contains(6)) {
                stepInfo.add(new BarEntry(6, 0));
            }
            if (!days.contains(7)) {
                stepInfo.add(new BarEntry(7, 0));
            }

            //String setter in x-Axis
            barChartStepCount.getXAxis().setValueFormatter(new com.github.mikephil.charting.formatter.IndexAxisValueFormatter(xAxisValues));

//            XAxis xAxis = barChartStepCount.getXAxis();
//            xAxis.setGranularity(1f);
//            xAxis.setCenterAxisLabels(false);
//            xAxis.setEnabled(true);
//            xAxis.setDrawGridLines(false);
//            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

//            XAxis xAxis = barChartStepCount.getXAxis();
//            xAxis.setValueFormatter(new ValueFormatter() {
//                @Override
//                public String getFormattedValue(float value, AxisBase axis) {
//                    switch ((int)value){
//                        //write your logic here
//                        case 1:
//                            return "일";
//                        case 2:
//                            return "월";
//                        case 3:
//                            return "화";
//                        case 4:
//                            return "수";
//                        case 5:
//                            return "목";
//                        case 6:
//                            return "금";
//                        case 7:
//                            return "토";
//                    }
//                }
//            });

            BarDataSet barDataSet = new BarDataSet(stepInfo, "걸음 수");

            int color = ContextCompat.getColor(getApplicationContext(), R.color.lightblue);
            barDataSet.setColor(color);
            barDataSet.setValueTextColor(Color.BLACK);
            barDataSet.setValueTextSize(16f);

            BarData barData = new BarData(barDataSet);

            barChartStepCount.setFitBars(true);
            barChartStepCount.setTouchEnabled(false);
            barChartStepCount.setData(barData);
//            barChartStepCount.getDescription().setText("Bar Chart Example");
            barChartStepCount.animateY(0);
            barChartStepCount.invalidate();
//            barChartStepCount.getLegend().setEnabled(false);
//            barChartStepCount.getDescription().setEnabled(false);

        } else {
//            Toast.makeText(ActivityMyExerciseInfo.this, "listSize = 0", Toast.LENGTH_SHORT).show();
            week_num--;
            beforeWeekBtn.setEnabled(false);
        }

    }
//
//    private void fetchUserLevelData(String userid) {
//        Log.d(TAG, "fetchUserLevelData()");
//        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
//        Call<List<StepCount>> call = apiInterface.fetchUserLevelData(userid);
//        call.enqueue(new Callback<List<StepCount>>() {
//            @Override
//            public void onResponse(@NonNull Call<List<StepCount>> call, @NonNull Response<List<StepCount>> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    Log.d(TAG, "fetchWeeklyStepCount() onResponse(): successful and not null");
//                    Log.d(TAG, "fetchWeeklyStepCount() response.body(): " + response.body());
//                    Log.d(TAG, "fetchWeeklyStepCount() response: " + response);
//                    parseFetchedWeeklyStepCount(response.body());
//                } else {
//                    Log.d(TAG, "fetchWeeklyStepCount() onResponse() : not successful");
//                }
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<List<StepCount>> call, @NonNull Throwable t) {
//                Log.d(TAG, "fetchWeeklyStepCount(): onFailure : " + t.getMessage());
//            }
//        });
//    }
//
//    private void parseFetchedWeeklyStepCount(List<StepCount> lists) {
//        Log.d(TAG, "parseFetchedWeeklyStepCount()");
////        weeklyStepCountAdapter = new WeeklyStepCountAdapter(ActivityMyExerciseInfo.this, lists);
////        recyclerViewWeeklyStepCount.setAdapter(weeklyStepCountAdapter);
////        Log.e(TAG, "setAdapter()");
////        weeklyStepCountAdapter.notifyDataSetChanged();
////
////        stepCountList = lists;
//
//        int listSize = lists.size();
//
//        if (listSize > 0) {
//
//            weeklyStepCountAdapter = new WeeklyStepCountAdapter(ActivityMyExerciseInfo.this, lists);
//            recyclerViewWeeklyStepCount.setAdapter(weeklyStepCountAdapter);
//            Log.e(TAG, "setAdapter()");
//            weeklyStepCountAdapter.notifyDataSetChanged();
//
//            stepCountList = lists;
//
////        ArrayList<String> days = new ArrayList<>();
//            ArrayList<Integer> days = new ArrayList<>();
//            ArrayList<Integer> stepCounts = new ArrayList<>();
//
//            List<String> xAxisValues = new ArrayList<>(Arrays.asList("", "일", "월", "화", "수", "목", "금", "토"));
//
//            // 주별 각각 날짜를 요일로 변환해서 days arraylist 에 저장하기
//            for (int i = 0; i < listSize; i++) {
//                Date date = null;
//                try {
//                    date = new SimpleDateFormat("yyyy-MM-dd").parse(lists.get(i).getDate());
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                Calendar calendar = Calendar.getInstance();
//                calendar.setTime(date);
////            days.add(getDayOfWeek(calendar.get(Calendar.DAY_OF_WEEK)));
//                days.add(calendar.get(Calendar.DAY_OF_WEEK));
//
//                stepCounts.add(lists.get(i).getSteps());
//            }
//
//            Log.e(TAG, "days: " + days);
//            Log.e(TAG, "stepCounts: " + stepCounts);
//
//            ArrayList<BarEntry> stepInfo = new ArrayList<>();
//
//            for (int i = 0; i < listSize; i++) {
//                stepInfo.add(new BarEntry(days.get(i), stepCounts.get(i)));
//            }
//
//            if (!days.contains(1)) {
//                stepInfo.add(new BarEntry(1, 0));
//            }
//            if (!days.contains(2)) {
//                stepInfo.add(new BarEntry(2, 0));
//            }
//            if (!days.contains(3)) {
//                stepInfo.add(new BarEntry(3, 0));
//            }
//            if (!days.contains(4)) {
//                stepInfo.add(new BarEntry(4, 0));
//            }
//            if (!days.contains(5)) {
//                stepInfo.add(new BarEntry(5, 0));
//            }
//            if (!days.contains(6)) {
//                stepInfo.add(new BarEntry(6, 0));
//            }
//            if (!days.contains(7)) {
//                stepInfo.add(new BarEntry(7, 0));
//            }
//
//            //String setter in x-Axis
//            barChartStepCount.getXAxis().setValueFormatter(new com.github.mikephil.charting.formatter.IndexAxisValueFormatter(xAxisValues));
//
////            XAxis xAxis = barChartStepCount.getXAxis();
////            xAxis.setGranularity(1f);
////            xAxis.setCenterAxisLabels(false);
////            xAxis.setEnabled(true);
////            xAxis.setDrawGridLines(false);
////            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//
////            XAxis xAxis = barChartStepCount.getXAxis();
////            xAxis.setValueFormatter(new ValueFormatter() {
////                @Override
////                public String getFormattedValue(float value, AxisBase axis) {
////                    switch ((int)value){
////                        //write your logic here
////                        case 1:
////                            return "일";
////                        case 2:
////                            return "월";
////                        case 3:
////                            return "화";
////                        case 4:
////                            return "수";
////                        case 5:
////                            return "목";
////                        case 6:
////                            return "금";
////                        case 7:
////                            return "토";
////                    }
////                }
////            });
//
//            BarDataSet barDataSet = new BarDataSet(stepInfo, "걸음 수");
//
//            int color = ContextCompat.getColor(getApplicationContext(), R.color.lightblue);
//            barDataSet.setColor(color);
//            barDataSet.setValueTextColor(Color.BLACK);
//            barDataSet.setValueTextSize(16f);
//
//            BarData barData = new BarData(barDataSet);
//
//            barChartStepCount.setFitBars(true);
//            barChartStepCount.setTouchEnabled(false);
//            barChartStepCount.setData(barData);
////            barChartStepCount.getDescription().setText("Bar Chart Example");
//            barChartStepCount.animateY(0);
//            barChartStepCount.invalidate();
////            barChartStepCount.getLegend().setEnabled(false);
////            barChartStepCount.getDescription().setEnabled(false);
//
//        } else {
////            Toast.makeText(ActivityMyExerciseInfo.this, "listSize = 0", Toast.LENGTH_SHORT).show();
//            week_num--;
//            beforeWeekBtn.setEnabled(false);
//        }
//
//    }

    // 요일 1~7 을 일~토 로 변환하기
    private String getDayOfWeek(int value) {
        String day = "";
        switch (value) {
            case 1:
                day = "일";
                break;
            case 2:
                day = "월";
                break;
            case 3:
                day = "화";
                break;
            case 4:
                day = "수";
                break;
            case 5:
                day = "목";
                break;
            case 6:
                day = "금";
                break;
            case 7:
                day = "토";
                break;
        }
        return day;
    }
}