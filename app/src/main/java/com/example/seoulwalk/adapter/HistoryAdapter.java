package com.example.seoulwalk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.seoulwalk.R;
import com.example.seoulwalk.data.History_Data;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private ArrayList<History_Data> mData = null;


    private HistoryAdapter.OnItemClickListener mListener = null ;
    public interface OnItemClickListener {
        void onItemClick(View v, int position) ;
    }
    // OnItemClickListener 리스너 객체 참조를 어댑터에 전달하는 메서드
    public void setOnItemClickListener(HistoryAdapter.OnItemClickListener listener) {
        this.mListener = listener ;
    }

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

        holder.history_datetime.setText(history_data.getHistory_datetime());
        holder.history_name.setText(history_data.getHistory_name());
        holder.history_course_name_detail.setText(history_data.getHistory_course_name());
        holder.history_time.setText(history_data.getHistory_time());
        holder.history_distance.setText(history_data.getHistory_distance());
        holder.history_calorie.setText(history_data.getHistory_calorie());
        holder.history_speed.setText(history_data.getHistory_speed());

    }

    public HistoryAdapter(ArrayList<History_Data> list) {
        mData = list;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView history_datetime, history_name, history_course_name_detail, history_time, history_distance, history_calorie, history_speed;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            history_datetime = itemView.findViewById(R.id.history_datetime);
            history_name = itemView.findViewById(R.id.history_name);
            history_course_name_detail = itemView.findViewById(R.id.course_name_detail_history_item);
            history_time = itemView.findViewById(R.id.time_history_item);
            history_distance = itemView.findViewById(R.id.distance_history_item);
            history_calorie = itemView.findViewById(R.id.calorie_history_item);
            history_speed = itemView.findViewById(R.id.speed_history_item);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition() ;
                    if (pos != RecyclerView.NO_POSITION) {
                        if(mListener!=null){
                            //setPosition(pos);
                            mListener.onItemClick(v,pos);
                            notifyItemChanged(pos);
                        }
                    }
                }
            });
        }
    }
}
