package com.example.seoulwalk.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.seoulwalk.R;
import com.example.seoulwalk.adapter.Dulle1Adapter;
import com.example.seoulwalk.adapter.Dulle2Adapter;
import com.example.seoulwalk.data.Dulle_Data;
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
    Dulle_Data dulle_data ;
    Dulle1Adapter dulle1Adapter;
    RecyclerView dulle1;
    Dulle2Adapter dulle2Adapter;
    RecyclerView dulle2;
    @Override
    protected void onStart() {
        super.onStart();
        // TODO: 11/9/21 좀있다가 열어 줘라
        //retrofit_dulle();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dulle);

        test_text_btn = findViewById(R.id.test_text_btn);


        Exam_data exam_data = new Exam_data();
        String a = exam_data.getVv();
        Log.e("CREATE!!!!",a);
        parde_Location(a);


        shared = new PreferenceHelper(this);
//        dulle_data= new Dulle_Data("도봉산역","2시간 50분","당고개공원 갈림길");
//        dulle_data= new Dulle_Data("당고개공원 갈림길","5시간 50분","화랑대 역");




        //Dulle_Data dulle_data = new Dulle_Data("aaaa","Asdas","!#@3");


//      list1.add(dulle_data);

        System.out.println("홗인준입니다."+list1.size());



        course_btn = findViewById(R.id.course_btn);
        home_btn = findViewById(R.id.home_btn);
        community_btn = findViewById(R.id.community_btn);
        mypage_btn = findViewById(R.id.mypage_btn);


        //loadData();
        dulle1 = findViewById(R.id.dulle_list_1);
        dulle2 = findViewById(R.id.dulle_list_2);
        dulle1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        dulle2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));

//        dulle1Adapter = new Dulle1Adapter(list1);
//        dulle1.setAdapter(dulle1Adapter);
//
//        dulle2Adapter = new Dulle2Adapter(list2);
//        dulle2.setAdapter(dulle2Adapter);



        //홈 버튼 클릭 시 차트 액티비티로 이동
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //마이페이지
        mypage_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ActivityMypage.class);
                startActivity(intent);
                finish();
            }
        });

        //커뮤니티
        community_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ActivityCommunity.class);
                startActivity(intent);
                finish();
            }
        });

        //코스
        course_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        //회원가입 액티비티로 이동
        test_text_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ActivityMap.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        //액티비티를 종료할 때 애니메이션 없애기
        overridePendingTransition(0,0);
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
                    // System.out.println(dataobj.getString("dulle_start")+i+"번쨰");
                    list1.add(dulle_data);
                    ///saveInfo(list1);

                }

                //saveInfo(list1);
                System.out.println(list1.size());
                dulle1Adapter = new Dulle1Adapter(list1);
                dulle1.setAdapter(dulle1Adapter);
//
                dulle2Adapter = new Dulle2Adapter(list2);
                dulle2.setAdapter(dulle1Adapter);

                dulle1Adapter.setOnItemClickListener(new Dulle1Adapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {
                        //Toast.makeText(getApplicationContext(),"click1 : "+position,Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),ActivityCourseInfo.class);
                        intent.putExtra("dulle",list1.get(position).getDulle_name_start());
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
    private void parde_Location(String response)
    {

        String[] filt1 = response.split(",0");
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i=0; i<filt1.length; i++){

            System.out.println(filt1[i]+"확인중"+i);
            arrayList.add(filt1[i]);

        }
        System.out.println("확인합니다 "+arrayList.size());

        ArrayList<String> arrayList2 = new ArrayList<>();
        ArrayList<String> arrayList3 = new ArrayList<>();
        for (int i=0; i<arrayList.size(); i++){
            System.out.println("array get i" + arrayList.get(i));
            String[] filt2 = arrayList.get(i).split(",");

            System.out.println("filt2" + filt2.length);

            System.out.println(filt2[0]);
            System.out.println(filt2[1]);
            arrayList2.add(filt2[0]);
            arrayList3.add(filt2[1]);



        }

        System.out.println("x값 리스트" + arrayList2.size());
        System.out.println("y값 리스트" + arrayList3.size());





    }

}