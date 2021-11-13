package com.example.seoulwalk.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.seoulwalk.Activity.ActivityCourseInfo;
import com.example.seoulwalk.R;
import com.example.seoulwalk.data.DetailCourse_Data;
import com.example.seoulwalk.retrofit.ApiClient;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.ArrayList;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class Theme_Detail_Adapter extends RecyclerView.Adapter<Theme_Detail_Adapter.ThemeDetail_ViewHolder> {


    int stepCountInt, timeInt;
    double distanceDouble, lengthDouble;
    String courseFullNameString, courseNameString, difficultyString, startString, endString, latlngStart, latlngEnd;


    private Theme_Detail_Adapter.OnItemClickListener mListener = null ;
    public interface OnItemClickListener {
        void onItemClick(View v, int position, int stepCountInt, int timeInt, double distanceDouble, double lengthDouble, String courseFullNameString,
                         String courseNameString, String difficultyString, String startString,String endString, String latlngStart, String latlngEnd) ;
    }
    // OnItemClickListener 리스너 객체 참조를 어댑터에 전달하는 메서드
    public void setOnItemClickListener(Theme_Detail_Adapter.OnItemClickListener listener) {
        this.mListener = listener ;
    }


    ArrayList<DetailCourse_Data> recyclerItemArr = new ArrayList<>();
    Context context;


    public Theme_Detail_Adapter(Context context, ArrayList<DetailCourse_Data> data) {
        this.context = context;
        this.recyclerItemArr = data;
    }


    @NonNull
    @NotNull
    @Override
    public Theme_Detail_Adapter.ThemeDetail_ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.course_detail_item, parent, false);
        return new ThemeDetail_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Theme_Detail_Adapter.ThemeDetail_ViewHolder holder, int position) {
        holder.onBind(recyclerItemArr.get(position));



        courseNameString = recyclerItemArr.get(holder.getBindingAdapterPosition()).getCourse_name();
        // 원래코스명
        courseFullNameString = recyclerItemArr.get(holder.getBindingAdapterPosition()).getCourse_fullName();
        // 걸음수
        stepCountInt = recyclerItemArr.get(holder.getBindingAdapterPosition()).getCourse_stepcount();
        // 코스길이
        lengthDouble = recyclerItemArr.get(holder.getBindingAdapterPosition()).getCourse_length();
        // 나와의 거리
        distanceDouble = recyclerItemArr.get(holder.getBindingAdapterPosition()).getDistance_to_me();
        // 난이도
        difficultyString = recyclerItemArr.get(holder.getBindingAdapterPosition()).getCourse_difficulty();
        // 소요시간
        timeInt = recyclerItemArr.get(holder.getBindingAdapterPosition()).getCourse_time();
        // 시작점
        startString = recyclerItemArr.get(holder.getBindingAdapterPosition()).getStart();
        // 도착점
        endString = recyclerItemArr.get(holder.getBindingAdapterPosition()).getEnd();
        // 시작좌표
        latlngStart = recyclerItemArr.get(holder.getBindingAdapterPosition()).getLatLng();
        // 끝좌표
        latlngEnd = recyclerItemArr.get(holder.getBindingAdapterPosition()).getLatLng_End();

    }

    @Override
    public int getItemCount() {
        return recyclerItemArr.size();
    }

    public class ThemeDetail_ViewHolder extends RecyclerView.ViewHolder {


        // 난이도 나와의거리 코스길이 코스소요시간 걸음수 이미지경로 코스이름 원래코스이름

        TextView level;
        TextView home;
        TextView length;
        TextView time;

        TextView stepcount;
        ImageView imgurl;

        TextView name;
        TextView fullname;


        public ThemeDetail_ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            //아이템들 아이디 찾아주고
            level = itemView.findViewById(R.id.course_detail_item_difficulty);
            home = itemView.findViewById(R.id.course_detail_item_distance);
            length = itemView.findViewById(R.id.course_detail_item_length);
            time = itemView.findViewById(R.id.course_detail_item_time);
            stepcount = itemView.findViewById(R.id.course_detail_item_step_count);
            imgurl = itemView.findViewById(R.id.course_detail_item_image);
            name = itemView.findViewById(R.id.course_detail_item_name);
            fullname = itemView.findViewById(R.id.course_detail_item_original_course_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition() ;
                    if (pos != RecyclerView.NO_POSITION) {
                        if(mListener!=null){
                            //setPosition(pos);
                            mListener.onItemClick(v,pos,stepCountInt,timeInt,distanceDouble,lengthDouble,courseFullNameString,courseNameString,difficultyString,
                                    startString,endString,latlngStart,latlngEnd);
                            notifyItemChanged(pos);
                        }
                    }
                }
            });

        }

        public void onBind(DetailCourse_Data detailCourse_data) {


            //프로필 추가하면 프로필 사진도 불러와야함


            //settext를 데이터의 get메소드로 받아옴
            fullname.setText(detailCourse_data.getCourse_fullName());
            name.setText(detailCourse_data.getCourse_name());
            length.setText(String.valueOf(detailCourse_data.getCourse_length()).concat("km"));
            time.setText(String.valueOf(detailCourse_data.getCourse_time()).concat("분"));
            stepcount.setText(String.valueOf(detailCourse_data.getCourse_stepcount()).concat("걸음"));

            level.setText(detailCourse_data.getCourse_difficulty());
            home.setText(String.valueOf(detailCourse_data.getDistance_to_me()).concat("km"));

            Glide.with(context).load(ApiClient.BASE_URL.concat("img/").concat(detailCourse_data.getCourse_image())).into(imgurl);



//                    //액티비티로 이동
//                    Intent intent = new Intent(context, ActivityCourseInfo.class);
//
//                    intent.putExtra("LatLng_end", latlngEnd);
//                    Log.d("TAG", "stepCountInt: " + stepCountInt);
//                    Log.d("TAG", "timeInt: " + timeInt);
//                    Log.d("TAG", "distanceDouble: " + distanceDouble);
//                    Log.d("TAG", "lengthDouble: " + lengthDouble);
//                    Log.d("TAG", "courseFullNameString: " + courseFullNameString);
//                    Log.d("TAG", "courseNameString: " + courseNameString);
//                    Log.d("TAG", "difficultyString: " + difficultyString);
//                    Log.d("TAG", "dulle_start: " + startString);
//                    Log.d("TAG", "dulle_end: " + endString);
//                    Log.d("TAG", "LanLng: " + latlngStart);
//                    Log.d("TAG", "LatLng_end: " + latlngEnd);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    context.startActivity(intent);
            // }


        }


    }
}
