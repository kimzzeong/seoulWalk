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

    int idInt, yearFormatInt, viewInt;
    String titleString, descriptionString, dateString, videoPathString, locationString, userString, monthDateString, thumbnailString;
    Uri videoUri;

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
            idInt = lists.get(getBindingAdapterPosition()).getId();
            titleString = lists.get(getBindingAdapterPosition()).getTitle();
            descriptionString = lists.get(getBindingAdapterPosition()).getDescription();
            dateString = lists.get(getBindingAdapterPosition()).getDate();
            locationString = lists.get(getBindingAdapterPosition()).getLocation();
            userString = lists.get(getBindingAdapterPosition()).getUser();
            monthDateString = lists.get(getBindingAdapterPosition()).getMonthDate();
            thumbnailString = lists.get(getBindingAdapterPosition()).getThumbnail();
            yearFormatInt = lists.get(getBindingAdapterPosition()).getYearFormat();
            viewInt = lists.get(getBindingAdapterPosition()).getViews();
            Log.d(TAG, "userString: " + userString);
            videoPathString = ApiClient.BASE_URL.concat(lists.get(getBindingAdapterPosition()).getPath());
            videoUri = Uri.parse(videoPathString);

            if (pos != RecyclerView.NO_POSITION) {
//                Toast.makeText(view.getContext(œ), "pos: " + pos + " / title: " + titleString, Toast.LENGTH_SHORT).show();
                Log.d("MyVideoAdapter", "onClick: videoPathString: " + videoPathString);
                Context context = view.getContext();
                Intent intent = new Intent(context, VideoActivity.class);
                intent.putExtra("id", idInt);
                intent.putExtra("title", titleString);
                intent.putExtra("description", descriptionString);
                intent.putExtra("date", dateString);
                intent.putExtra("videoUri", videoUri);
                intent.putExtra("location", locationString);
                intent.putExtra("email", userString);
                intent.putExtra("yearFormat", yearFormatInt);
                intent.putExtra("monthDate", monthDateString);
                intent.putExtra("views", viewInt);
                Log.d("MyVideoAdapter", "intent id: " + idInt);
                Log.d("MyVideoAdapter", "intent titleString: " + titleString);
                Log.d("MyVideoAdapter", "intent descriptionString: " + descriptionString);
                Log.d("MyVideoAdapter", "intent dateString: " + dateString);
                Log.d("MyVideoAdapter", "intent videoUri: " + videoUri);
                Log.d("MyVideoAdapter", "intent locationString: " + locationString);
                Log.d("MyVideoAdapter", "intent email: " + userString);
                Log.d("MyVideoAdapter", "intent yearFormatInt: " + yearFormatInt);
                Log.d("MyVideoAdapter", "intent monthDateString: " + monthDateString);
                Log.d("MyVideoAdapter", "intent viewInt: " + viewInt);
                context.startActivity(intent);
            }
        }
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}