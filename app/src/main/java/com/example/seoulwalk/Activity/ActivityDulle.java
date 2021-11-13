package com.example.seoulwalk.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.seoulwalk.R;
import com.example.seoulwalk.adapter.DetailCourseAdapter;
import com.example.seoulwalk.adapter.HistoryAdapter;
import com.example.seoulwalk.adapter.ThemeAdapter;
import com.example.seoulwalk.adapter.Dulle2Adapter;
import com.example.seoulwalk.adapter.DulleDetailAdapter;
import com.example.seoulwalk.data.DetailCourse_Data;
import com.example.seoulwalk.data.DulleDetail_Data;
import com.example.seoulwalk.data.Dulle_Data;
import com.example.seoulwalk.data.Dulle_theme_Data;
import com.example.seoulwalk.retrofit.ApiClient;
import com.example.seoulwalk.retrofit.ApiInterface;
import com.example.seoulwalk.retrofit.Dulle_Name;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ActivityDulle extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    Button course_btn, mypage_btn, home_btn, community_btn;
    TextView test_text_btn; // 테스트용 텍스트뷰, 클릭하면 회원가입 창으로 감
    PreferenceHelper shared;
    ArrayList<Dulle_Data> list1 = new ArrayList<>();
    ArrayList<Dulle_Data> list2 = new ArrayList<>();
    ArrayList<DulleDetail_Data> detail_list = new ArrayList<>();
    Dulle_Data dulle_data ;
    DulleDetail_Data dulleDetail_data;

    ThemeAdapter ThemeAdapter;
    RecyclerView dulle1;
    Dulle2Adapter dulle2Adapter;
    RecyclerView dulle2;

    // 세부코스별
//    DulleDetailAdapter dulleDetailAdapter;
    DetailCourseAdapter detailCourseAdapter;
    DetailCourseAdapter.ItemClickListener detailCourseItemClickListener;
    ArrayList<DetailCourse_Data> detailCourse_list = new ArrayList<>();
    RecyclerView dulle_detail;

    String sortString;
    Spinner listing_spinner;
    ArrayList<Dulle_theme_Data> theme_list = new ArrayList<>();

    @Override
    protected void onStart() {
        super.onStart();

        retrofit_dulle();

        // 세부코스 데이터 가져오기
        fetchDetailCourseData("거리순");
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
//        listing_spinner.setSelection(0);
        listing_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) { //선택됐을 때
                Log.e("Spinner",""+position);
//                ((TextView)parent.getChildAt(0)).setTextColor(Color.BLACK);
//                ((TextView)parent.getChildAt(0)).setTextSize(20);

                String spinnerSelectedString = listing_spinner.getSelectedItem().toString();
                fetchDetailCourseData(spinnerSelectedString);
//                fetchDetailCourseData(position);
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
        dulle1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        dulle2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
//        dulle_detail.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));

        // 세부코스별 리사이클러뷰
        dulle_detail = findViewById(R.id.dulle_detail);
        dulle_detail.setHasFixedSize(true);
        dulle_detail.setLayoutManager(new LinearLayoutManager(this));

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

//        fetchDetailCourseData(sortString);
    }

    // 세부코스별 리사이클러뷰 데이터 가져오기
    private void fetchDetailCourseData(String sort) {
        Log.d(TAG, "fetchDetailCourseData()");
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ArrayList<DetailCourse_Data>> call = apiInterface.fetchDetailCourseData(sort);
        call.enqueue(new Callback<ArrayList<DetailCourse_Data>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<DetailCourse_Data>> call, @NonNull Response<ArrayList<DetailCourse_Data>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d(TAG, "fetchDetailCourseData() onResponse(): successful and not null");
                    Log.d(TAG, "fetchDetailCourseData() sort: " + sort);
                    Log.d(TAG, "fetchDetailCourseData() response.body(): " + response.body());
                    Log.d(TAG, "fetchDetailCourseData() response: " + response);

                    if (detailCourse_list != null) {
                        detailCourse_list.clear();
                        Log.d(TAG, "fetchDetailCourseData() detailCourse_list.clear()");
                    }
                    parseDetailCourseData(response.body());
                } else {
                    Log.d(TAG, "fetchDetailCourseData() not successful");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<DetailCourse_Data>> call, @NonNull Throwable t) {
                Log.d(TAG, "fetchDetailCourseData(): onFailure : " + t.getMessage());
            }
        });
    }

    private void parseDetailCourseData(ArrayList<DetailCourse_Data> lists) {
        Log.d(TAG, "parseDetailCourseData()");
        // 세부코스별 리사이클러뷰 어댑터
        detailCourse_list = lists;
        detailCourseAdapter = new DetailCourseAdapter(this, detailCourse_list, detailCourseItemClickListener);
        dulle_detail.setAdapter(detailCourseAdapter);
        detailCourseAdapter.notifyDataSetChanged();

    }

    // TODO: 11/8/21 레트로핏
    private void retrofit_dulle() {

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

                    dulleDetail_data = new DulleDetail_Data();
                    JSONObject dataobj = dataArray.getJSONObject(i);
                    dulleDetail_data.setDulle_name_start(dataobj.getString("dulle_start"));
                    dulleDetail_data.setDulle_name_end(dataobj.getString("dulle_end"));
                    dulleDetail_data.setDulle_time(dataobj.getString("dulle_time"));
                    dulleDetail_data.setLatlng(dataobj.getString("LatLng"));
                    dulleDetail_data.setLatlng_end(dataobj.getString("LatLng_End"));
                    dulleDetail_data.setImg_item(dataobj.getString("img_item"));
                    //System.out.println(dataobj.getString("Latlng")+i+"번쨰");

                    ///saveInfo(list1);

                    detail_list.add(dulleDetail_data);
                }


                /** 여기 코스별로 나누자 */
                list2.add(new Dulle_Data("도봉산역","1코스-수락·불암산코스","화랑대역","dulle_1_1.jpg","37.68917268817206,  127.0468070568162","37.62057141019006, 127.0846233899846"));
                list2.add(new Dulle_Data("화랑대역","2코스-용마·아차산코스","광나루역","dulle_2_1.jpg","37.62057141019006, 127.0846233899846","37.5453623012, 127.1044593584"));
                list2.add(new Dulle_Data("광나루역","3코스-고덕·일자산코스","수서역","dulle_3_1.jpg","37.5453623012, 127.1044593584","37.48703092484752, 127.1014292535824"));
                list2.add(new Dulle_Data("수서역","4코스-대모·우면산코스","사당역 갈림길","dulle_4_1.jpg","37.48703092484752, 127.1014292535824","37.47597056036504, 126.9818863213483"));
                list2.add(new Dulle_Data("사당역 갈림길","5코스-관악산코스","석수역","dulle_5_1.jpg","37.47597056036504, 126.9818863213483","37.43428146926362, 126.9021778590784"));
                list2.add(new Dulle_Data("석수역","6코스-안양천코스","가양대교 남단","dulle_6_1.jpg","37.43428146926362, 126.9021778590784","37.56137725367158, 126.8554687742643"));
                list2.add(new Dulle_Data("가양대교 남단","7코스-봉산·앵봉산코스","구파발역","dulle_7_1.jpg","37.56137725367158, 126.8554687742643","37.6361699551611, 126.9189621745594"));
                list2.add(new Dulle_Data("구파발역","8코스-북한산코스","도봉산역","dulle_8_1.jpg","37.6366754939268, 126.9189565646409","37.68900589242598, 127.0459151597205"));


                @SuppressLint("UseCompatLoadingForDrawables") Drawable forman = ContextCompat.getDrawable(getApplicationContext(), R.drawable.forman);
                @SuppressLint("UseCompatLoadingForDrawables") Drawable forman2 = ContextCompat.getDrawable(getApplicationContext(), R.drawable.fornature);
                @SuppressLint("UseCompatLoadingForDrawables") Drawable forman3= ContextCompat.getDrawable(getApplicationContext(), R.drawable.forwalk);
                @SuppressLint("UseCompatLoadingForDrawables") Drawable forman4= ContextCompat.getDrawable(getApplicationContext(), R.drawable.forstory);


                if (theme_list.size() == 0) {
                    //하드로 theme 데이터들 넣어야함
                    Dulle_theme_Data dulle_theme_data = new Dulle_theme_Data("사람을 위한 길", forman);
                    Dulle_theme_Data dulle_theme_data1 = new Dulle_theme_Data("자연을 위한 길", forman2);
                    Dulle_theme_Data dulle_theme_data2 = new Dulle_theme_Data("산책을 위한 길", forman3);
                    Dulle_theme_Data dulle_theme_data3 = new Dulle_theme_Data("이야기가 있는 길", forman4);
                    theme_list.add(dulle_theme_data);
                    theme_list.add(dulle_theme_data1);
                    theme_list.add(dulle_theme_data2);
                    theme_list.add(dulle_theme_data3);
                }

                ThemeAdapter = new ThemeAdapter(theme_list);


                //saveInfo(list1);
                System.out.println("size입니다."+list1.size());
                dulle1.setAdapter(ThemeAdapter);

                dulle2Adapter = new Dulle2Adapter(list2);
                dulle2.setAdapter(dulle2Adapter);


//                // 세부코스별 리사이클러뷰 어댑터
////                dulleDetailAdapter = new DulleDetailAdapter(detail_list);
////                dulle_detail.setAdapter(dulleDetailAdapter);
//                detailCourseAdapter = new DetailCourseAdapter(this, detailCourse_list, detailCourseItemClickListener);
//                dulle_detail.setAdapter(detailCourseAdapter);

                ThemeAdapter.setOnItemClickListener(new ThemeAdapter.OnItemClickListener() {
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
//                dulleDetailAdapter = new DulleDetailAdapter(detail_list);
//                dulle_detail.setAdapter(dulleDetailAdapter);
//


                dulle2Adapter.setOnItemClickListener(new Dulle2Adapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {
                        //Toast.makeText(getApplicationContext(),"click2 : "+position,Toast.LENGTH_SHORT).show();
                        //Toast.makeText(getApplicationContext(),"click1 : "+position,Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),ActivityCourseInfo.class);
                        intent.putExtra("dulle_start",list2.get(position).getDulle_name_start());
                        intent.putExtra("dulle_end",list2.get(position).getDulle_name_end());
                        intent.putExtra("LanLng",list2.get(position).getLatlng());
                        intent.putExtra("LatLng_end",list2.get(position).getLatlng_end());
                        intent.putExtra("dulle_full_title",list2.get(position).getDulle_time());
                        startActivity(intent);
                    }
                });


//                dulleDetailAdapter.setOnItemClickListener(new DulleDetailAdapter.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(View v, int position) {
//
//                        Intent intent = new Intent(getApplicationContext(),ActivityCourseInfo.class);
//                        intent.putExtra("dulle_start",detail_list.get(position).getDulle_name_start());
//                        intent.putExtra("dulle_end",detail_list.get(position).getDulle_name_end());
//                        intent.putExtra("LanLng",detail_list.get(position).getLatlng());
//                        intent.putExtra("LatLng_end",detail_list.get(position).getLatlng_end());
//                        startActivity(intent);
//
//                    }
//                });


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