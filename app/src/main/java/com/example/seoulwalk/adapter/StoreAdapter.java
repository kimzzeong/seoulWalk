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
import com.example.seoulwalk.data.Point_Data;
import com.example.seoulwalk.data.Store_Data;
import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.ViewHolder> {


    public interface OnItemClickListener{
        void onItemClick(View v, int position);
    }

    private StoreAdapter.OnItemClickListener mListener = null ;
    public void setOnItemClickListener(StoreAdapter.OnItemClickListener listener) {
        this.mListener = listener ;
    }

    private ArrayList<Store_Data> mData = null;
    private Context context;

    @NonNull
    @Override
    public StoreAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);



        View view = inflater.inflate(R.layout.store_item, parent,false);
        StoreAdapter.ViewHolder vh = new StoreAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull StoreAdapter.ViewHolder holder, int position) {

        Store_Data point_data = mData.get(position);

        holder.point.setText(point_data.getStore_title());
        holder.num.setText(point_data.getNum());
        holder.store_title.setText(point_data.getStore_title());

        Glide.with(context).load(point_data.getImg()).into(holder.store_photo);

    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public StoreAdapter(ArrayList<Store_Data> list, Context context) {
        mData = list;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView store_title, num, point;
        ImageView store_photo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            store_photo = itemView.findViewById(R.id.store_photo);
            store_title = itemView.findViewById(R.id.store_title);
            num = itemView.findViewById(R.id.num);
            point = itemView.findViewById(R.id.point);


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
