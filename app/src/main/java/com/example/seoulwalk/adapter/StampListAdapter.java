package com.example.seoulwalk.adapter;

import android.content.Context;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
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
//
//    //화살표 클릭 시 코스 보이기/안보이기
//    public interface OnExpandClickListener{
//        void onExpandClick(View v, int position);
//    }
//
//    private StampListAdapter.OnExpandClickListener mListener = null ;
//    public void setOnExpandClickListener(StampListAdapter.OnExpandClickListener listener) {
//        this.mListener = listener ;
//    }

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
        holder.course_title.setText(stampList_data.getCourse_title());


        ArrayList<Stamp_Data> stamp_list = new ArrayList<>(); //8코스 안에 세부로 나뉘는 스탬프 지점

        if(stampList_data.getStampTitle().equals("수락·불암산")){
            Stamp_Data stamp_data1 = new Stamp_Data(R.drawable.seoul_1,"코스1",false);
            stamp_list.add(stamp_data1);
            Stamp_Data stamp_data2 = new Stamp_Data(R.drawable.seoul_2,"코스2",true);
            stamp_list.add(stamp_data2);
            Stamp_Data stamp_data3 = new Stamp_Data(R.drawable.seoul_3,"코스3",false);
            stamp_list.add(stamp_data3);
        }else if(stampList_data.getStampTitle().equals("용마·아차산")){
            Stamp_Data stamp_data1 = new Stamp_Data(R.drawable.seoul_4,"코스1",true);
            stamp_list.add(stamp_data1);
            Stamp_Data stamp_data2 = new Stamp_Data(R.drawable.seoul_5,"코스2",false);
            stamp_list.add(stamp_data2);
            Stamp_Data stamp_data3 = new Stamp_Data(R.drawable.seoul_6,"코스3",false);
            stamp_list.add(stamp_data3);

        }else if(stampList_data.getStampTitle().equals("고덕·일자산")){
            Stamp_Data stamp_data1 = new Stamp_Data(R.drawable.seoul_7,"코스1",false);
            stamp_list.add(stamp_data1);
            Stamp_Data stamp_data2 = new Stamp_Data(R.drawable.seoul_8,"코스2",true);
            stamp_list.add(stamp_data2);
            Stamp_Data stamp_data3 = new Stamp_Data(R.drawable.seoul_9,"코스3",true);
            stamp_list.add(stamp_data3);
            Stamp_Data stamp_data4 = new Stamp_Data(R.drawable.seoul_10,"코스4",false);
            stamp_list.add(stamp_data4);

        }else if(stampList_data.getStampTitle().equals("대모·우면산")){
            Stamp_Data stamp_data1 = new Stamp_Data(R.drawable.seoul_11,"코스1",true);
            stamp_list.add(stamp_data1);
            Stamp_Data stamp_data2 = new Stamp_Data(R.drawable.seoul_12,"코스2",true);
            stamp_list.add(stamp_data2);
            Stamp_Data stamp_data3 = new Stamp_Data(R.drawable.seoul_13,"코스3",false);
            stamp_list.add(stamp_data3);

        }else if(stampList_data.getStampTitle().equals("관악산")){
            Stamp_Data stamp_data1 = new Stamp_Data(R.drawable.seoul_14,"코스1",true);
            stamp_list.add(stamp_data1);
            Stamp_Data stamp_data2 = new Stamp_Data(R.drawable.seoul_15,"코스2",true);
            stamp_list.add(stamp_data2);
            Stamp_Data stamp_data3 = new Stamp_Data(R.drawable.seoul_16,"코스3",false);
            stamp_list.add(stamp_data3);

        }else if(stampList_data.getStampTitle().equals("안양천")){
            Stamp_Data stamp_data1 = new Stamp_Data(R.drawable.seoul_17,"코스1",true);
            stamp_list.add(stamp_data1);
            Stamp_Data stamp_data2 = new Stamp_Data(R.drawable.seoul_18,"코스2",true);
            stamp_list.add(stamp_data2);
            Stamp_Data stamp_data3 = new Stamp_Data(R.drawable.seoul_19,"코스3",false);
            stamp_list.add(stamp_data3);

        }else if(stampList_data.getStampTitle().equals("봉산·앵봉산")){
            Stamp_Data stamp_data1 = new Stamp_Data(R.drawable.seoul_20,"코스1",true);
            stamp_list.add(stamp_data1);
            Stamp_Data stamp_data2 = new Stamp_Data(R.drawable.seoul_21,"코스2",true);
            stamp_list.add(stamp_data2);
            Stamp_Data stamp_data3 = new Stamp_Data(R.drawable.seoul_22,"코스3",false);
            stamp_list.add(stamp_data3);

        }else{ //북한산
            Stamp_Data stamp_data1 = new Stamp_Data(R.drawable.seoul_23,"코스1",false);
            stamp_list.add(stamp_data1);
            Stamp_Data stamp_data2 = new Stamp_Data(R.drawable.seoul_24,"코스2",true);
            stamp_list.add(stamp_data2);
            Stamp_Data stamp_data3 = new Stamp_Data(R.drawable.seoul_25,"코스3",false);
            stamp_list.add(stamp_data3);
            Stamp_Data stamp_data4 = new Stamp_Data(R.drawable.seoul_26,"코스4",true);
            stamp_list.add(stamp_data4);
            Stamp_Data stamp_data5 = new Stamp_Data(R.drawable.seoul_27,"코스5",true);
            stamp_list.add(stamp_data5);
            Stamp_Data stamp_data6 = new Stamp_Data(R.drawable.seoul_28,"코스6",false);
            stamp_list.add(stamp_data6);

        }
        holder.course_item_list.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false));

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

        TextView dullegil_title,course_title;
        RecyclerView course_item_list;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            dullegil_title = itemView.findViewById(R.id.dullegil_title);
            course_item_list = itemView.findViewById(R.id.course_item_list);
            course_title = itemView.findViewById(R.id.course_title);

        }
    }
}
