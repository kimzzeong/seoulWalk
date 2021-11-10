package com.example.seoulwalk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.seoulwalk.R;
import com.example.seoulwalk.data.StampList_Data;
import com.example.seoulwalk.data.Stamp_Data;

import java.util.ArrayList;

public class StampListAdapter extends RecyclerView.Adapter<StampListAdapter.ViewHolder> {

    private ArrayList<StampList_Data> mData = null;
    Context context;
    StampAdapter stampAdapter;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.stamp_list_item, parent,false);
        StampListAdapter.ViewHolder vh = new StampListAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        StampList_Data stampList_data = mData.get(position);

        holder.dullegil_title.setText(stampList_data.getStampTitle());


        ArrayList<Stamp_Data> stamp_list = new ArrayList<>(); //8코스 안에 세부로 나뉘는 스탬프 지점

        Stamp_Data stamp_data1 = new Stamp_Data(R.drawable.seoul_1,"코스1",true);
        stamp_list.add(stamp_data1);
        Stamp_Data stamp_data2 = new Stamp_Data(R.drawable.seoul_2,"코스2",true);
        stamp_list.add(stamp_data2);
        Stamp_Data stamp_data3 = new Stamp_Data(R.drawable.seoul_3,"코스3",false);
        stamp_list.add(stamp_data3);
//        Stamp_Data stamp_data4 = new Stamp_Data(R.drawable.seoul_4,"코스1",true);
//        stamp_list.add(stamp_data4);
//        Stamp_Data stamp_data5 = new Stamp_Data(R.drawable.seoul_5,"코스1",true);
//        stamp_list.add(stamp_data5);
//        Stamp_Data stamp_data6 = new Stamp_Data(R.drawable.seoul_6,"코스1",true);
//        stamp_list.add(stamp_data6);
//        Stamp_Data stamp_data7 = new Stamp_Data(R.drawable.seoul_7,"코스1",true);
//        stamp_list.add(stamp_data7);
//        Stamp_Data stamp_data8 = new Stamp_Data(R.drawable.seoul_8,"코스1",true);
//        stamp_list.add(stamp_data8);
//        Stamp_Data stamp_data9 = new Stamp_Data(R.drawable.seoul_9,"코스1",true);
//        stamp_list.add(stamp_data9);
//        Stamp_Data stamp_data10 = new Stamp_Data(R.drawable.seoul_10,"코스1",true);
//        stamp_list.add(stamp_data10);
//        Stamp_Data stamp_data11 = new Stamp_Data(R.drawable.seoul_11,"코스1",true);
//        stamp_list.add(stamp_data11);
//        Stamp_Data stamp_data12 = new Stamp_Data(R.drawable.seoul_12,"코스1",true);
//        stamp_list.add(stamp_data12);
//        Stamp_Data stamp_data13 = new Stamp_Data(R.drawable.seoul_13,"코스1",true);
//        stamp_list.add(stamp_data13);
//        Stamp_Data stamp_data14 = new Stamp_Data(R.drawable.seoul_14,"코스1",true);
//        stamp_list.add(stamp_data14);


        holder.course_item_list.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false));
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
//        holder.course_item_list.setLayoutManager(gridLayoutManager);

        stampAdapter = new StampAdapter(stamp_list,context);
        holder.course_item_list.setAdapter(stampAdapter);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public StampListAdapter(ArrayList<StampList_Data> list){
        this.mData = list;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView dullegil_title;
        RecyclerView course_item_list;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            dullegil_title = itemView.findViewById(R.id.dullegil_title);
            course_item_list = itemView.findViewById(R.id.course_item_list);
        }
    }
}
