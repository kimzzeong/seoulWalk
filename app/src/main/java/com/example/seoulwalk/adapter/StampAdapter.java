package com.example.seoulwalk.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.seoulwalk.Activity.ActivityStampCollection;
import com.example.seoulwalk.R;
import com.example.seoulwalk.data.History_Data;
import com.example.seoulwalk.data.Stamp_Data;

import java.util.ArrayList;

import jp.wasabeef.glide.transformations.GrayscaleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

import static android.content.Context.MODE_PRIVATE;

public class StampAdapter extends RecyclerView.Adapter<StampAdapter.ViewHolder> {

    private ArrayList<Stamp_Data> mData = null;
    Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private static final String SHARED_PREF_NAME = "mypref";
    ActivityStampCollection stampCollection = new ActivityStampCollection();



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        sharedPreferences =context.getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        editor = sharedPreferences.edit();
        View view = inflater.inflate(R.layout.stamp_item, parent,false);
        StampAdapter.ViewHolder vh = new StampAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Stamp_Data stamp_data = mData.get(position);

        Glide.with(context)
                .load(stamp_data.getStmap_img())
                .into(holder.stamp_image);


        holder.stamp_title.setText(stamp_data.getStamp_name());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context) // TestActivity 부분에는 현재 Activity의 이름 입력.
                        .setMessage("정말 프로필 사진으로 변경하시겠습니까?")     // 제목 부분 (직접 작성)
                        .setCancelable(false)
                        .setPositiveButton("네", new DialogInterface.OnClickListener() {      // 버튼1 (직접 작성)
                            public void onClick(DialogInterface dialog, int which){

                                editor.putString("user_profile","5-1");
                                editor.apply();

                                Toast.makeText(context,"프로필 사진이 변경되었습니다.",Toast.LENGTH_SHORT).show();
                                stampCollection.finish();

                            }
                        })
                        .setNegativeButton("아니오", new DialogInterface.OnClickListener() {     // 버튼2 (직접 작성)
                            public void onClick(DialogInterface dialog, int which){
                                return;
                            }
                        })
                        .show();
            }
        });

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
