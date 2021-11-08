package com.example.seoulwalk.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.seoulwalk.R;
import com.example.seoulwalk.data.Dulle_Data;

import java.util.ArrayList;

public class Dulle2Adapter extends RecyclerView.Adapter<Dulle2Adapter.ViewHolder> {
    public interface OnItemClickListener{
        void onItemClick(View v, int position);
    }

    private Dulle2Adapter.OnItemClickListener mListener = null ;


    public void setOnItemClickListener(Dulle2Adapter.OnItemClickListener listener) {
        this.mListener = listener ;
    }
    private ArrayList<Dulle_Data> mData = null;

    public Dulle2Adapter(ArrayList<Dulle_Data> list) {
        mData = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.dulle_item, parent,false);
        Dulle2Adapter.ViewHolder vh = new Dulle2Adapter.ViewHolder(view);
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
        Log.e("aaaa",String.valueOf(mData.size()));
        return mData.size();

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
