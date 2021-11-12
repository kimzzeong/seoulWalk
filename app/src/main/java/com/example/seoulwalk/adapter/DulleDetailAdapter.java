package com.example.seoulwalk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.seoulwalk.R;
import com.example.seoulwalk.data.DulleDetail_Data;
import com.example.seoulwalk.data.Dulle_Data;

import java.util.ArrayList;

public class DulleDetailAdapter extends RecyclerView.Adapter<DulleDetailAdapter.ViewHolder> {

    private ArrayList<DulleDetail_Data> mData = null;

    private DulleDetailAdapter.OnItemClickListener mListener = null ;


    public interface OnItemClickListener{
        void onItemClick(View v, int position);
    }

    public void setOnItemClickListener(DulleDetailAdapter.OnItemClickListener listener) {
        this.mListener = listener ;
    }

    public DulleDetailAdapter(ArrayList<DulleDetail_Data> list) {
        mData = list;
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.dulle_item, parent,false);
        DulleDetailAdapter.ViewHolder vh = new DulleDetailAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        DulleDetail_Data dulle_data = mData.get(position);
        holder.textView.setText("시작점 :"+dulle_data.getDulle_name_start());
        holder.textView_end.setText("도착점 :"+dulle_data.getDulle_name_end());
        holder.textView_time.setText(dulle_data.getDulle_time());
        Glide.with(holder.img_item)
                .load("http://49.247.196.22/img/"+dulle_data.getImg_item())
                .into(holder.img_item);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView,textView_end,textView_time ;
        ImageView img_item;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.text_start);
            textView_end = itemView.findViewById(R.id.text_end);
            textView_time = itemView.findViewById(R.id.text_time);
            img_item = itemView.findViewById(R.id.img_item);
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
