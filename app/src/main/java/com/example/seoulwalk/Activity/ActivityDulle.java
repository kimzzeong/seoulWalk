package com.example.seoulwalk.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.seoulwalk.R;
//import com.example.seoulwalk.adapter.Dulle1Adapter;
import com.example.seoulwalk.adapter.Dulle1Adapter;
import com.example.seoulwalk.adapter.Dulle2Adapter;
import com.example.seoulwalk.adapter.DulleDetailAdapter;
import com.example.seoulwalk.data.DulleDetail_Data;
import com.example.seoulwalk.data.Dulle_Data;
import com.example.seoulwalk.data.Dulle_theme_Data;
import com.example.seoulwalk.data.Exam_data;
import com.example.seoulwalk.retrofit.Dulle_Name;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ActivityDulle extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    Button course_btn, mypage_btn, home_btn, community_btn;
    TextView test_text_btn; // 테스트용 텍스트뷰, 클릭하면 회원가입 창으로 감
    PreferenceHelper shared;
    ArrayList<Dulle_Data> list1 = new ArrayList<>();
    ArrayList<Dulle_Data> list2 = new ArrayList<>();
    ArrayList<DulleDetail_Data> detail_list = new ArrayList<>();
    ArrayList<Dulle_theme_Data> theme_list = new ArrayList<>();


    Dulle_Data dulle_data ;
    Dulle1Adapter dulle1Adapter;
    RecyclerView dulle1;
    Dulle2Adapter dulle2Adapter;
    RecyclerView dulle2;
    DulleDetailAdapter dulleDetailAdapter;
    RecyclerView dulle_detail;
    Spinner listing_spinner;

    @Override
    protected void onStart() {
        super.onStart();

        retrofit_dulle();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dulle);

        listing_spinner = findViewById(R.id.listing_spinner);
        shared = new PreferenceHelper(this);








        ArrayAdapter<String> spinner_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,(String[])getResources().getStringArray(R.array.dulleSpinner));
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listing_spinner.setAdapter(spinner_adapter);
        listing_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) { //선택됐을 때
                Log.e("Spinner",""+position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { //선택이 안됐을 때

            }
        });

        System.out.println("홗인준입니다."+list1.size());



        course_btn = findViewById(R.id.course_btn);
        home_btn = findViewById(R.id.home_btn);
        community_btn = findViewById(R.id.community_btn);
        mypage_btn = findViewById(R.id.mypage_btn);
        test_text_btn = findViewById(R.id.text_view);

        //loadData();
        dulle1 = findViewById(R.id.dulle_theme_list);
        dulle2 = findViewById(R.id.dulle_course_list);
        dulle_detail = findViewById(R.id.dulle_detail);
        dulle1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        dulle2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        dulle_detail.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));

        for (int i = 1; i <= 10; i++){
            DulleDetail_Data dulleDetail_data = new DulleDetail_Data("시작점"+i,"시간"+i,"도착점"+i);
            detail_list.add(dulleDetail_data);
        }

        dulleDetailAdapter = new DulleDetailAdapter(detail_list);
        dulle_detail.setAdapter(dulleDetailAdapter);




        //홈 버튼 클릭 시 차트 액티비티로 이동
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(0,0);
            }
        });

        //마이페이지
        mypage_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ActivityMypage.class);
                startActivity(intent);
                finish();
                overridePendingTransition(0,0);
            }
        });

        //커뮤니티
        community_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ActivityCommunity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(0,0);
            }
        });

        //코스
        course_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



//        회원가입 액티비티로 이동
        test_text_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(),ActivityMap.class);
//                intent.putExtra("dulle_start",list1.get(1).getDulle_name_start());
//                intent.putExtra("dulle_end",list1.get(1).getDulle_name_end());
//                intent.putExtra("LanLng",list1.get(1).getLatlng());
//                intent.putExtra("LatLng_end",list1.get(1).getLatlng_end());
//                startActivity(intent);


                    Intent intent = new Intent(getApplicationContext(),ActivityMap.class);
                    startActivity(intent);

            }
        });
    }

    // TODO: 11/8/21 레트로핏
    private void retrofit_dulle()
    {

        String username = "둘레길";


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Dulle_Name.REGIST_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        Dulle_Name api = retrofit.create(Dulle_Name.class);
        Call<String> call = api.getDulleRegist(username);

        call.enqueue(new Callback<String>()
        {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response)
            {
                Log.e("Retrofit","시작되냐요?");
                if (response.isSuccessful() && response.body() != null)
                {
                    Log.e("onSuccess", response.body());

                    String jsonResponse = response.body();
                    parseLoginData(jsonResponse);

                }
                else {
                    Log.e("onSuccess", "값받아오기 실패");
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t)
            {
                Log.e("ERROR", "에러 = " + t.getMessage());
            }
        });

    }

    private void parseLoginData(String response)
    {
        try
        {
            JSONObject jsonObject = new JSONObject(response);


            if (jsonObject.getString("status").equals("true"))
            {
                loadData();
                System.out.println("여기 까지 들어오나?");
                JSONArray dataArray = jsonObject.getJSONArray("OK");
                Log.e("OK",dataArray.toString());
                Log.e("OK", String.valueOf(dataArray.length()));
                for (int i = 0; i < dataArray.length(); i++)
                {
                    dulle_data = new Dulle_Data();
                    JSONObject dataobj = dataArray.getJSONObject(i);
                    dulle_data.setDulle_name_start(dataobj.getString("dulle_start"));
                    dulle_data.setDulle_name_end(dataobj.getString("dulle_end"));
                    dulle_data.setDulle_time(dataobj.getString("dulle_time"));
                    dulle_data.setLatlng(dataobj.getString("LatLng"));
                    dulle_data.setLatlng_end(dataobj.getString("LatLng_End"));
                    dulle_data.setImg_item(dataobj.getString("img_item"));
                    //System.out.println(dataobj.getString("Latlng")+i+"번쨰");
                    list1.add(dulle_data);
                    ///saveInfo(list1);

                }

                //saveInfo(list1);
                System.out.println("size입니다."+list1.size());

                @SuppressLint("UseCompatLoadingForDrawables") Drawable forman = ContextCompat.getDrawable(getApplicationContext(), R.drawable.forman);
                @SuppressLint("UseCompatLoadingForDrawables") Drawable forman2 = ContextCompat.getDrawable(getApplicationContext(), R.drawable.fornature);
                @SuppressLint("UseCompatLoadingForDrawables") Drawable forman3= ContextCompat.getDrawable(getApplicationContext(), R.drawable.forwalk);
                @SuppressLint("UseCompatLoadingForDrawables") Drawable forman4= ContextCompat.getDrawable(getApplicationContext(), R.drawable.forstory);


                //하드로 theme 데이터들 넣어야함
                Dulle_theme_Data dulle_theme_data = new Dulle_theme_Data("사람을 위한 길", forman);
                Dulle_theme_Data dulle_theme_data1 = new Dulle_theme_Data("자연을 위한 길", forman2);
                Dulle_theme_Data dulle_theme_data2 = new Dulle_theme_Data("산책을 위한 길", forman3);
                Dulle_theme_Data dulle_theme_data3 = new Dulle_theme_Data("이야기가 있는 길", forman4);


                theme_list.add(dulle_theme_data);
                theme_list.add(dulle_theme_data1);
                theme_list.add(dulle_theme_data2);
                theme_list.add(dulle_theme_data3);


                dulle1Adapter = new Dulle1Adapter(theme_list);
                dulle1.setAdapter(dulle1Adapter);


                // TODO: 2021-11-13 오세왕 작업 이거 dulle2 adapter에 연결해야하는데 1에 되어있길래 2로 바꿈
                dulle2Adapter = new Dulle2Adapter(list1);
                dulle2.setAdapter(dulle2Adapter);


                Log.e("확인",list1.get(0).getLatlng());
                dulle1Adapter.setOnItemClickListener(new Dulle1Adapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {
                        //Toast.makeText(getApplicationContext(),"click1 : "+position,Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),ActivityCourseInfo.class);
                        intent.putExtra("dulle_start",list1.get(position).getDulle_name_start());
                        intent.putExtra("dulle_end",list1.get(position).getDulle_name_end());
                        intent.putExtra("LanLng",list1.get(position).getLatlng());
                        intent.putExtra("LatLng_end",list1.get(position).getLatlng_end());
                        startActivity(intent);
                    }
                });


                dulle2Adapter.setOnItemClickListener(new Dulle2Adapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {
                        //Toast.makeText(getApplicationContext(),"click2 : "+position,Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),ActivityCourseInfo.class);
                        startActivity(intent);
                    }
                });


            }

        }
        catch (JSONException e)
        {
            e.printStackTrace();
            Log.e("error",e.getMessage());
        }

    }

    void loadData() {

        //먼저 지슨을 로드해주고
        Gson loadGsons = new Gson();
        //리스트의 경우 타입을 지정해줘야한다.
        Type type = new TypeToken<ArrayList<Dulle_Data>>() {
        }.getType();


        // 문자열 불러오기
        //String loadSharedName = "shared"; // 가져올 SharedPreferences 이름 지정
        String loadKey = "dulle_info"; // 가져올 데이터의 Key값 지정
        //여기에 json어레이가 담기도록 해보자
        String loadValue = ""; // 가져올 데이터가 담기는 변수
        String defaultValue = ""; // 가져오는것에 실패 했을 경우 기본 지정 텍스트. 보통 기본 값은 널 값으로 준다.

        sharedPreferences = getSharedPreferences("chat_data", MODE_PRIVATE);
        loadValue = sharedPreferences.getString(loadKey, defaultValue);

        System.out.println("여기는 메인+저장되어 있는 값 " + loadValue);
        //loadValue 안에 리스트의 데이터가 스트링 형태로 담아지기 때문에 스트링으로 되어있는 loadValue를 원래의 리스트 형태로 가지고 온다.
        list1 = loadGsons.fromJson(loadValue, type);
//        ttt.setText(loadValue);


        if (list1 == null) {
            list1 = new ArrayList<>();
        }

    }
    //
    private void saveInfo(ArrayList<Dulle_Data> response) {


        Gson saveGson = new Gson();

        //String saveSharedName = "shared"; // 저장할 SharedPreferences 이름 지정.
        String saveKey = "dulle_info"; // 저장할 데이터의 Key값 지정.
        String saveValue = saveGson.toJson(response); //저장할 데이터의 Content 지정.
        //객체를 json으로 변환 -->toJson

        sharedPreferences = getSharedPreferences("dulle_data", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        editor.putString(saveKey, saveValue);
        System.out.println("자금 저장되고 있는 리스트 값들" + saveValue);

        editor.commit();
    }


    ///데이터 파싱


}