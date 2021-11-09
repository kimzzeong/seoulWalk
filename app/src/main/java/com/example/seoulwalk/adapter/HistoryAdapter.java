package com.example.seoulwalk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.seoulwalk.R;
import com.example.seoulwalk.data.Dulle_Data;
import com.example.seoulwalk.data.History_Data;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private ArrayList<History_Data> mData = null;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.history_item, parent,false);
        HistoryAdapter.ViewHolder vh = new HistoryAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        History_Data history_data = mData.get(position);

        holder.history_time.setText(history_data.getHistory_time());
        holder.history_name.setText(history_data.getHistory_name());
        holder.history_history_review.setText(history_data.getHistory_review());

    }

    public HistoryAdapter(ArrayList<History_Data> list) {
        mData = list;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView history_time, history_name, history_history_review;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            history_time = itemView.findViewById(R.id.history_time);
            history_name = itemView.findViewById(R.id.history_name);
            history_history_review = itemView.findViewById(R.id.history_review);
        }
    }
}
