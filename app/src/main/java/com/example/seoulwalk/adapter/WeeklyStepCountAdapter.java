package com.example.seoulwalk.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.seoulwalk.R;
import com.example.seoulwalk.model.StepCount;
import com.example.seoulwalk.retrofit.ApiClient;
import com.example.seoulwalk.retrofit.ApiInterface;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeeklyStepCountAdapter extends RecyclerView.Adapter<WeeklyStepCountAdapter.WeeklyStepCountViewHolder> {
    private final String TAG = this.getClass().getSimpleName();
    private final Context context;
    private final List<StepCount> lists;

    public WeeklyStepCountAdapter(Context context, List<StepCount> lists) {
        this.context = context;
        this.lists = lists;
    }

    @NonNull
    @Override
    public WeeklyStepCountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.weekly_step_count_item, parent, false);
        return new WeeklyStepCountViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeeklyStepCountViewHolder holder, int position) {
        StepCount stepCount = lists.get(position);

        holder.date.setText(stepCount.getDate());
        holder.step_count.setText(String.valueOf(stepCount.getSteps()));

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public static class WeeklyStepCountViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView date, step_count;

        public WeeklyStepCountViewHolder(@NonNull View view) {
            super(view);
            date = view.findViewById(R.id.weekly_step_count_item_date);
            step_count = view.findViewById(R.id.weekly_step_count_item_step_count);
        }

        @Override
        public void onClick(View view) {}
    }
}
