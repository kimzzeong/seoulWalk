package com.example.seoulwalk.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.seoulwalk.R;
import com.example.seoulwalk.data.History_Data;
import com.example.seoulwalk.data.Stamp_Data;

import java.util.ArrayList;

import jp.wasabeef.glide.transformations.GrayscaleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class StampAdapter extends RecyclerView.Adapter<StampAdapter.ViewHolder> {

    private ArrayList<Stamp_Data> mData = null;
    Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.stamp_item, parent,false);
        StampAdapter.ViewHolder vh = new StampAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Stamp_Data stamp_data = mData.get(position);

        if(stamp_data.isStamp_it()){ // true면 컬러스탬프
            Glide.with(context)
                    .load(stamp_data.getStmap_img())
                    .into(holder.stamp_image);
        }else{ // false면 흑백스탬프
            Glide.with(context)
                    .load(stamp_data.getStmap_img())
                    .apply(RequestOptions.bitmapTransform(new GrayscaleTransformation())) //흑백으로 만들어줌;; glide 대박
                    .into(holder.stamp_image);
        }



        holder.stamp_title.setText(stamp_data.getStamp_name());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public StampAdapter(ArrayList<Stamp_Data> list, Context context) {
        this.context = context;
        mData = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView stamp_image;
        TextView stamp_title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            stamp_image = itemView.findViewById(R.id.stamp_image);
            stamp_title = itemView.findViewById(R.id.stamp_title);
        }
    }
}
