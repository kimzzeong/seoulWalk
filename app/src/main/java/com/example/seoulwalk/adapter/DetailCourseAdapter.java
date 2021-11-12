package com.example.seoulwalk.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.seoulwalk.Activity.ActivityCourseInfo;
import com.example.seoulwalk.R;
import com.example.seoulwalk.data.DetailCourse_Data;
import com.example.seoulwalk.retrofit.ApiClient;
import com.example.seoulwalk.retrofit.ApiInterface;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailCourseAdapter extends RecyclerView.Adapter<DetailCourseAdapter.DetailCourseViewHolder> {

    private final String TAG = this.getClass().getSimpleName();
    private final Context context;
    private final List<DetailCourse_Data> lists;
    private final ItemClickListener itemClickListener;

    int stepCountInt, timeInt;
    double distanceDouble, lengthDouble;
    String courseFullNameString, courseNameString, difficultyString;

    public DetailCourseAdapter(Context context, List<DetailCourse_Data> lists, ItemClickListener itemClickListener) {
        this.context = context;
        this.lists = lists;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public DetailCourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.course_detail_item, parent, false);
        return new DetailCourseViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailCourseViewHolder holder, int position) {
        DetailCourse_Data data = lists.get(position);
        holder.textViewOriginalCourseName.setText(data.getCourse_fullName());
        holder.textViewDetailCourseName.setText(data.getCourse_name());
        holder.textViewLength.setText(String.valueOf(data.getCourse_length()).concat("km"));
        holder.textViewTime.setText(String.valueOf(data.getCourse_time()).concat("분"));
        holder.textViewStepCount.setText(String.valueOf(data.getCourse_stepcount()).concat("걸음"));
        holder.textViewDifficulty.setText(data.getCourse_difficulty());
        holder.textViewDistance.setText(String.valueOf(data.getDistance_to_me()).concat("km"));

        Glide.with(context).load(ApiClient.BASE_URL.concat(data.getCourse_image())).into(holder.thumbnailImageView);

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public class DetailCourseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ConstraintLayout constraintLayout;
        public TextView textViewOriginalCourseName, textViewDetailCourseName, textViewLength, textViewTime, textViewStepCount, textViewDifficulty, textViewDistance;
        public ImageView thumbnailImageView;
        ItemClickListener itemClickListener;

        public DetailCourseViewHolder(@NonNull View view, ItemClickListener itemClickListener) {
            super(view);
            constraintLayout = view.findViewById(R.id.constraint_course_detail_item);
            thumbnailImageView = view.findViewById(R.id.course_detail_item_image);
            textViewOriginalCourseName = view.findViewById(R.id.course_detail_item_original_course_name);
            textViewDetailCourseName = view.findViewById(R.id.course_detail_item_name);
            textViewLength = view.findViewById(R.id.course_detail_item_length);
            textViewTime = view.findViewById(R.id.course_detail_item_time);
            textViewStepCount = view.findViewById(R.id.course_detail_item_step_count);
            textViewDifficulty = view.findViewById(R.id.course_detail_item_difficulty);
            textViewDistance = view.findViewById(R.id.course_detail_item_distance);

            this.itemClickListener = itemClickListener;
            constraintLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
//            itemClickListener.onItemClick(view, getAdapterPosition());

            // getAdapterPosition() is deprecated
            int pos = getBindingAdapterPosition();
            // 세부코스명
            courseNameString = lists.get(getBindingAdapterPosition()).getCourse_name();
            // 원래코스명
            courseFullNameString = lists.get(getBindingAdapterPosition()).getCourse_fullName();
            // 걸음수
            stepCountInt = lists.get(getBindingAdapterPosition()).getCourse_stepcount();
            // 코스길이
            lengthDouble = lists.get(getBindingAdapterPosition()).getCourse_length();
            // 나와의 거리
            distanceDouble = lists.get(getBindingAdapterPosition()).getDistance_to_me();
            // 난이도
            difficultyString = lists.get(getBindingAdapterPosition()).getCourse_difficulty();
            // 소요시간
            timeInt = lists.get(getBindingAdapterPosition()).getCourse_time();

            if (pos != RecyclerView.NO_POSITION) {
                Toast.makeText(view.getContext(), "pos: " + pos + " / courseNameString: " + courseNameString, Toast.LENGTH_SHORT).show();
                Context context = view.getContext();
                Intent intent = new Intent(context, ActivityCourseInfo.class);
                intent.putExtra("stepCountInt", stepCountInt);
                intent.putExtra("timeInt", timeInt);
                intent.putExtra("distanceDouble", distanceDouble);
                intent.putExtra("lengthDouble", lengthDouble);
                intent.putExtra("courseFullNameString", courseFullNameString);
                intent.putExtra("courseNameString", courseNameString);
                intent.putExtra("difficultyString", difficultyString);
                Log.d("TAG", "stepCountInt: " + stepCountInt);
                Log.d("TAG", "timeInt: " + timeInt);
                Log.d("TAG", "distanceDouble: " + distanceDouble);
                Log.d("TAG", "lengthDouble: " + lengthDouble);
                Log.d("TAG", "courseFullNameString: " + courseFullNameString);
                Log.d("TAG", "courseNameString: " + courseNameString);
                Log.d("TAG", "difficultyString: " + difficultyString);
                context.startActivity(intent);
            }
        }
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}