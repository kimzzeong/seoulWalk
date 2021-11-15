package com.example.seoulwalk.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.seoulwalk.R;
import com.example.seoulwalk.YoutubeDialog;
import com.example.seoulwalk.adapter.PostDataAdapter;
import com.example.seoulwalk.data.CommunityData;
import com.example.seoulwalk.data.PostData;
import com.example.seoulwalk.retrofit.Dulle_Name;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ActivityWrite extends AppCompatActivity implements YoutubeDialog.YoutubeDialogInterface{

    TextView write_save;
    Button btn_photo, btn_gallery , btn_link;
    private ArrayList<PostData> contentsList = new ArrayList<>();
    private PostDataAdapter postDataAdapter;
    private RecyclerView postRecyclerview;
    private String profilePath;
    Spinner write_spinner, write_course_spinner; // 전체글 카테고리, 후기쓸 때 코스별
    EditText post_title,post_content;
    String user_idx, user_nickname;
    String write_spinner_text,write_course_spinner_text;
    ArrayList<CommunityData> post_list = new ArrayList<>();
    Gson gson;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private static final String SHARED_PREF_NAME = "mypref";
    String image = null;

    ActivityCommunity activityCommunity = (ActivityCommunity)ActivityCommunity.activityCommunity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        write_save = findViewById(R.id.write_save);
        btn_photo = (Button)findViewById(R.id.camera_btn);
        btn_gallery = (Button)findViewById(R.id.gallery_btn);
        btn_link = (Button)findViewById(R.id.link_btn);
        postRecyclerview = findViewById(R.id.post_Data);
        write_spinner = findViewById(R.id.write_spinner);
        write_course_spinner = findViewById(R.id.write_course_spinner);
        post_title = findViewById(R.id.post_title);
        post_content = findViewById(R.id.post_content);
        checkPermission();

        sharedPreferences =getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        editor = sharedPreferences.edit();
        user_idx = sharedPreferences.getString("user_idx","");
        user_nickname = sharedPreferences.getString("user_nickname","");

        Type type = new TypeToken<ArrayList<CommunityData>>() {}.getType();
        gson = new Gson();

        if(!sharedPreferences.getString("POST","").equals("")){

            post_list = gson.fromJson(sharedPreferences.getString("POST",""),type);
        }

        //Log.e("post_lost",""+post_list.size());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        postRecyclerview.setLayoutManager(linearLayoutManager);
        postDataAdapter = new PostDataAdapter(this,contentsList);
        postRecyclerview.setAdapter(postDataAdapter);

        //전체글 카테고리
        ArrayAdapter<String> spinner_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,(String[])getResources().getStringArray(R.array.writeSpinner));
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        write_spinner.setAdapter(spinner_adapter);

        //코스별 카테고리
        ArrayAdapter<String> course_spinner_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,(String[])getResources().getStringArray(R.array.courseSpinner));
        course_spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        write_course_spinner.setAdapter(course_spinner_adapter);

        write_course_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(ActivityWrite.this,write_course_spinner.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
                write_course_spinner_text = write_course_spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Toast.makeText(ActivityWrite.this,write_course_spinner.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
                write_course_spinner_text = write_course_spinner.getSelectedItem().toString();
            }
        });

        write_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) { //선택됐을 때
                Log.e("Spinner",""+position);
                if(position == 1){
                    write_course_spinner.setVisibility(View.VISIBLE);
                }else{
                    write_course_spinner.setVisibility(View.GONE);
                }
                write_spinner_text = write_spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { //선택이 안됐을 때
                write_spinner_text = write_spinner.getSelectedItem().toString();
            }
        });

        write_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gson = new Gson();
                Log.e("gson",gson.toJson(contentsList));
                long now = System.currentTimeMillis();
                Date date = new Date(now);
                SimpleDateFormat format = new SimpleDateFormat("MM/dd HH:mm:ss");
                String postDate = format.format(date);

                CommunityData communityData = new CommunityData(post_title.getText().toString(),post_content.getText().toString(),image,user_nickname,postDate,"",user_idx
                        ,gson.toJson(contentsList),write_spinner_text,write_course_spinner_text);
//                CommunityData communityData2 = new CommunityData("둘레길 도는데 날씨 좋네요.",post_content.getText().toString(),image,"둘레길매니아",postDate,"",user_idx
//                        ,gson.toJson(contentsList),write_spinner_text,write_course_spinner_text);
                post_list.add(communityData);

                editor.putString("POST",gson.toJson(post_list));
                editor.apply();
                Intent intent = new Intent(ActivityWrite.this,ActivityCommunity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                if(activityCommunity!=null){
                    activityCommunity.finish();
                }
                finish();
                Toast.makeText(ActivityWrite.this,"글이 정상적으로 등록되었습니다.",Toast.LENGTH_SHORT).show();
            }
        });


        //카메라 버튼
        btn_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //카메라로
                Intent intent = new Intent(ActivityWrite.this,CameraActivity.class);
                startActivityForResult(intent,0);
            }
        });

        //갤러리 버튼
        btn_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //갤러리로
                Intent intentgallery = new Intent(ActivityWrite.this,ActivityGallery.class);
                startActivityForResult(intentgallery,0);
            }
        });

        //유튜브 링크
        btn_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
    }

    //유튜브 링크 다이얼로그 오픈
    public void openDialog(){
        YoutubeDialog youtubeDialog = new YoutubeDialog();
        youtubeDialog.show(getSupportFragmentManager(),"링크 입력");
    }

    @Override
    public void applyText(String link) {
        PostData item = new PostData(null,null, link, null);
        contentsList.add(item);
        postDataAdapter.notifyDataSetChanged();
    }

    //권한 체크
    private void checkPermission() {
        int permission = ContextCompat.checkSelfPermission(ActivityWrite.this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int permission_cameara = ContextCompat.checkSelfPermission(ActivityWrite.this, Manifest.permission.CAMERA);
        if(permission == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(ActivityWrite.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},0);
        }
        if(permission_cameara == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(ActivityWrite.this,new String[]{Manifest.permission.CAMERA},0);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0 && resultCode == RESULT_OK) { // 갤러리 사진 프로필

            profilePath = data.getStringExtra("profilePath");

            if(image == null){
                image = profilePath;
            }else{

            }

            PostData item = new PostData(profilePath,null,null,null);
            contentsList.add(item);
            Log.e("profilePath", profilePath);
            postDataAdapter.notifyDataSetChanged();

//            Gson gson = new GsonBuilder()
//                    .setLenient()
//                    .create();
//
//            //레트로핏 테스트
//            Retrofit retrofit = new Retrofit.Builder()
//                    .baseUrl(Dulle_Name.REGIST_URL)
//                    .addConverterFactory(GsonConverterFactory.create(gson))
//                    .build();
//            Dulle_Name api = retrofit.create(Dulle_Name.class);
//            PostData postData = contentsList.get(contentsList.size()-1);
//            Log.e("test", postData.getImageData());
//            Log.e("Log",contentsList.size()+"");
//            Call<Integer> call = api.savePost(contentsList);
//            call.enqueue(new Callback<Integer>() {
//                @Override
//                public void onResponse(Call<Integer> call, Response<Integer> response) {
//                    //Toast.makeText(getApplicationContext(),""+response.body(),Toast.LENGTH_SHORT).show();
//
//                }
//
//                @Override
//                public void onFailure(Call<Integer> call, Throwable t) {
//                    Log.e("에러",t.getLocalizedMessage());
//                }
//            });
        }
    }
}