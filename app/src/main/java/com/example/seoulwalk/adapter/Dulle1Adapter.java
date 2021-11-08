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

import java.util.ArrayList;

public class Dulle1Adapter extends RecyclerView.Adapter<Dulle1Adapter.ViewHolder> {

    public interface OnItemClickListener{
        void onItemClick(View v, int position);
    }

    private OnItemClickListener mListener = null ;
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener ;
    }

    private ArrayList<Dulle_Data> mData = null;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.dulle_item, parent,false);
        Dulle1Adapter.ViewHolder vh = new Dulle1Adapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Dulle_Data dulle_data = mData.get(position);
        holder.textView.setText(dulle_data.getDulle_name_start());
        holder.textView_end.setText(dulle_data.getDulle_name_end());
        holder.textView_time.setText(dulle_data.getDulle_time());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public Dulle1Adapter(ArrayList<Dulle_Data> list) {
        mData = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView,textView_end,textView_time ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_start);
            textView_end = itemView.findViewById(R.id.text_end);
            textView_time = itemView.findViewById(R.id.text_time);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        if (mListener != null) {
                            mListener.onItemClick(v, pos) ;
                        }
                    }
                }
            });
        }
    }
}
