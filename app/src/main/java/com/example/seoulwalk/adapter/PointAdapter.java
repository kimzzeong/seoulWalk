package com.example.seoulwalk.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.seoulwalk.R;
import com.example.seoulwalk.data.Point_Data;
import com.example.seoulwalk.data.Review_Data;

import java.util.ArrayList;

public class PointAdapter extends RecyclerView.Adapter<PointAdapter.ViewHolder> {


    public interface OnItemClickListener{
        void onItemClick(View v, int position);
    }

    private PointAdapter.OnItemClickListener mListener = null ;
    public void setOnItemClickListener(PointAdapter.OnItemClickListener listener) {
        this.mListener = listener ;
    }

    private ArrayList<Point_Data> mData = null;
    private Context context;

    @NonNull
    @Override
    public PointAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);



        View view = inflater.inflate(R.layout.mypoint_item, parent,false);
        PointAdapter.ViewHolder vh = new PointAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull PointAdapter.ViewHolder holder, int position) {

        Point_Data point_data = mData.get(position);

        holder.point_title.setText(point_data.getPoint_title());
        holder.point.setText(point_data.getPoint());
        holder.date.setText(point_data.getDate());

    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public PointAdapter(ArrayList<Point_Data> list, Context context) {
        mData = list;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView date, point, point_title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.date);
            point = itemView.findViewById(R.id.point);
            point_title = itemView.findViewById(R.id.point_title);


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
